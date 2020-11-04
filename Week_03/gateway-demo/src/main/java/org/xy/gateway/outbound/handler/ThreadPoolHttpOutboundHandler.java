package org.xy.gateway.outbound.handler;


import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.xy.gateway.utils.NamedThreadFactory;

import java.util.Optional;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static io.netty.handler.codec.http.HttpHeaderNames.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaderValues.KEEP_ALIVE;
import static org.xy.gateway.utils.ResponseUtils.commonResponse;
import static org.xy.gateway.utils.ResponseUtils.emptyResponse;
import static org.xy.gateway.utils.ResponseUtils.logHttpHeaders;

/**
 * thread pool http outbound handler
 *
 * @author wangxinyu
 * @date 2020/11/4
 */
@Slf4j
public abstract class ThreadPoolHttpOutboundHandler implements GatewayHttpOutboundHandler {

    private final String backendUrl;

    private final ExecutorService proxyService;

    protected int cores;

    private static final long KEEP_ALIVE_TIME = 1000;
    private static final int QUEUE_SIZE = 2048;

    public ThreadPoolHttpOutboundHandler(String proxyServer) {
        this.backendUrl = proxyServer.endsWith("/") ? proxyServer.substring(0, proxyServer.length() - 1) : proxyServer;
        cores = Runtime.getRuntime().availableProcessors() * 2;
        // new ThreadPoolExecutor.DiscardPolicy()
        RejectedExecutionHandler handler = new ThreadPoolExecutor.CallerRunsPolicy();
        proxyService = new ThreadPoolExecutor(cores, cores, KEEP_ALIVE_TIME, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(QUEUE_SIZE),
                new NamedThreadFactory(String.format("\"proxyService\"-%s", this.getClass().getSimpleName())), handler);
    }

    @Override
    public void handle(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx) {
        final String url = this.backendUrl + fullRequest.uri();
        log.info("handle inbound request for: {} with {}", url, this.getClass().getSimpleName());
        proxyService.submit(() -> handle(fullRequest, ctx, url));
    }

    public static void handleResponse(final FullHttpRequest fullRequest,
                                      final ChannelHandlerContext ctx,
                                      final HttpResponse resp) throws Exception {
        handleResponse(fullRequest, ctx, resp, false);
    }

    public static void handleResponse(final FullHttpRequest fullRequest,
                                      final ChannelHandlerContext ctx,
                                      final HttpResponse resp,
                                      final boolean mock) throws Exception {
        FullHttpResponse response = null;
        try {
            response = commonResponse(resp, mock);
            logHttpHeaders(resp);
        } catch (Exception e) {
            response = emptyResponse();
            e.printStackTrace();
            ctx.close();
        } finally {
            if (fullRequest != null) {
                if (!HttpUtil.isKeepAlive(fullRequest)) {
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                } else {
                    Optional.ofNullable(response).ifPresent(r -> r.headers().set(CONNECTION, KEEP_ALIVE));
                    ctx.write(response);
                }
            }
            ctx.flush();
            //ctx.close();
        }
    }


    /**
     * handle
     *
     * @param request request
     * @param ctx     ctx
     * @param url     url
     */
    protected abstract void handle(final FullHttpRequest request, final ChannelHandlerContext ctx, final String url);
}
