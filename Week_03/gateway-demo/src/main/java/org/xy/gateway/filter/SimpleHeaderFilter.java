package org.xy.gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * simple header filter
 *
 * @author wangxinyu
 * @date 2020/11/4
 */
public class SimpleHeaderFilter implements HttpRequestFilter {

    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
    }
}
