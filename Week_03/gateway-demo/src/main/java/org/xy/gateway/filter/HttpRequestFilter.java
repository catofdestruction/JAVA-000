package org.xy.gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * http request filter
 *
 * @author wangxinyu
 * @date 2020/11/4
 */
public interface HttpRequestFilter {

    /**
     * filter
     *
     * @param fullRequest fullRequest
     * @param ctx         ctx
     */
    void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx);
}
