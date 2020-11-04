package org.xy.gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import org.apache.http.HttpResponse;

/**
 * http response filter
 *
 * @author wangxinyu
 * @date 2020/11/4
 */
public interface HttpResponseFilter {

    /**
     * filter
     *
     * @param fullRequest fullRequest
     * @param ctx         ctx
     * @param resp        resp
     */
    void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx, HttpResponse resp);
}
