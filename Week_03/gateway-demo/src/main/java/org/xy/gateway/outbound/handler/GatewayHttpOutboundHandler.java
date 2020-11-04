package org.xy.gateway.outbound.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * gateway http outbound handler
 *
 * @author wangxinyu
 * @date 2020/11/4
 */
public interface GatewayHttpOutboundHandler {

    /**
     * handle
     *
     * @param fullRequest fullRequest
     * @param ctx         ctx
     */
    void handle(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx);
}
