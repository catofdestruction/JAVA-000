package org.xy.gateway.outbound.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import lombok.extern.slf4j.Slf4j;
import org.xy.gateway.utils.NamedThreadFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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

    private static final long KEEP_ALIVE_TIME = 1000;
    private static final int QUEUE_SIZE = 2048;

    public ThreadPoolHttpOutboundHandler(String proxyServer) {
        this.backendUrl = proxyServer.endsWith("/") ? proxyServer.substring(0, proxyServer.length() - 1) : proxyServer;
        int cores = Runtime.getRuntime().availableProcessors() * 2;
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

    /**
     * handle
     *
     * @param request request
     * @param ctx     ctx
     * @param url     url
     */
    protected abstract void handle(final FullHttpRequest request, final ChannelHandlerContext ctx, final String url);
}
