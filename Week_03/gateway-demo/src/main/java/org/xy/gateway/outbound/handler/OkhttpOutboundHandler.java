package org.xy.gateway.outbound.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * okhttp outbound handler
 *
 * @author wangxinyu
 * @date 2020/11/4
 */
@Slf4j
public class OkhttpOutboundHandler extends ThreadPoolHttpOutboundHandler {

    public OkhttpOutboundHandler(String proxyServer) {
        super(proxyServer);
    }

    @Override
    protected void handle(final FullHttpRequest request, final ChannelHandlerContext ctx, final String url) {

    }
}
