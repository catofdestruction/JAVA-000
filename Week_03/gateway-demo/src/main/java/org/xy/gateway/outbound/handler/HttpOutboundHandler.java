package org.xy.gateway.outbound.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * http outbound handler
 *
 * @author wangxinyu
 * @date 2020/11/4
 */
@Slf4j
public class HttpOutboundHandler extends ThreadPoolHttpOutboundHandler {

    public HttpOutboundHandler(String proxyServer) {
        super(proxyServer);
    }

    @Override
    protected void handle(FullHttpRequest request, ChannelHandlerContext ctx, String url) {

    }
}
