package org.xy.gateway.outbound.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * netty outbound handler
 *
 * @author wangxinyu
 * @date 2020/11/4
 */
@Slf4j
@Getter
public class NettyOutboundHandler implements GatewayHttpOutboundHandler {

    private final String proxyServer;

    public NettyOutboundHandler(String proxyServer) {
        this.proxyServer = proxyServer;
    }

    @Override
    public void handle(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        log.info("handle inbound request with {}", this.getClass().getSimpleName());
    }
}
