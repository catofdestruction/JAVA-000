package org.xy.gateway.inbound;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.ssl.SslContext;

/**
 * http inbound initializer
 *
 * @author wangxinyu
 * @date 2020/11/3
 */
public class HttpInboundInitializer extends ChannelInitializer<SocketChannel> {

    private String proxyServer = null;
    private SslContext sslContext = null;

    public HttpInboundInitializer(String proxyServer, SslContext sslContext) {
        this.proxyServer = proxyServer;
        this.sslContext = sslContext;
    }

    @Override
    protected void initChannel(SocketChannel ch) {
        ChannelPipeline pipeline = ch.pipeline();
        // using addLast to add ChannelHandler to channel pipeline
        // such as SslHandler, HttpServerCodec, HttpObjectAggregator, HttpHandler ....
		if (sslContext != null) {
			pipeline.addLast(sslContext.newHandler(ch.alloc()));
		}
		// add a http codec to channel pipeline
        pipeline.addLast(new HttpServerCodec());
//        pipeline.addLast(new HttpServerExpectContinueHandler());
        pipeline.addLast(new HttpObjectAggregator(1024 * 1024));
        // custom ChannelHandler named HttpHandler
        pipeline.addLast(new HttpInboundHandler(proxyServer, HttpInboundHandler.ClientMode.OKHTTP_CLIENT));
    }
}