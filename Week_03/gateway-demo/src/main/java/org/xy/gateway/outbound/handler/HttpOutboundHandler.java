package org.xy.gateway.outbound.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.protocol.HTTP;

/**
 * http outbound handler
 *
 * @author wangxinyu
 * @date 2020/11/4
 */
@Slf4j
public class HttpOutboundHandler extends ThreadPoolHttpOutboundHandler {

    private final CloseableHttpAsyncClient httpclient;

    public HttpOutboundHandler(String proxyServer) {
        super(proxyServer);

        IOReactorConfig ioConfig = IOReactorConfig.custom()
                                                  .setConnectTimeout(1000)
                                                  .setSoTimeout(1000)
                                                  .setIoThreadCount(cores)
                                                  .setRcvBufSize(32 * 1024)
                                                  .build();

        httpclient = HttpAsyncClients.custom()
                                     .setMaxConnTotal(40)
                                     .setMaxConnPerRoute(8)
                                     .setDefaultIOReactorConfig(ioConfig)
                                     .setKeepAliveStrategy((response, context) -> 6000)
                                     .build();
        httpclient.start();
    }

    @Override
    protected void handle(final FullHttpRequest request, final ChannelHandlerContext ctx, final String url) {
        final HttpGet httpGet = new HttpGet(url);
        // httpGet.setHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_CLOSE)
        httpGet.setHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_KEEP_ALIVE);
        httpclient.execute(httpGet, new FutureCallback<HttpResponse>() {
            @Override
            public void completed(final HttpResponse endpointResponse) {
                try {
                    handleResponse(request, ctx, endpointResponse);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(final Exception ex) {
                httpGet.abort();
                ex.printStackTrace();
            }

            @Override
            public void cancelled() {
                httpGet.abort();
            }
        });
    }
}
