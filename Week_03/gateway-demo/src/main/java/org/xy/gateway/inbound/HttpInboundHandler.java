package org.xy.gateway.inbound;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.util.ReferenceCountUtil;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.xy.gateway.outbound.handler.HttpOutboundHandler;
import org.xy.gateway.outbound.handler.NettyOutboundHandler;
import org.xy.gateway.outbound.handler.OkhttpOutboundHandler;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

import static io.netty.handler.codec.http.HttpHeaderNames.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaderValues.KEEP_ALIVE;
import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * http inbound handler
 *
 * @author wangxinyu
 * @date 2020/11/3
 */
@Slf4j
@Getter
public class HttpInboundHandler extends ChannelInboundHandlerAdapter {

    private NettyOutboundHandler nettyOutboundHandler;

    private HttpOutboundHandler httpOutboundHandler;

    private OkhttpOutboundHandler okhttpOutboundHandler;

    private final String proxyServer;

    private final ClientMode clientMode;

    private long tempTimeStamp;

    public enum ClientMode {
        /**
         * NETTY
         */
        NETTY,

        /**
         * HTTP_CLIENT
         */
        HTTP_CLIENT,

        /**
         * OKHTTP_CLIENT
         */
        OKHTTP_CLIENT
    }

    public HttpInboundHandler(String proxyServer, ClientMode clientMode) {
        this.proxyServer = proxyServer;
        this.clientMode = clientMode;
        switch (clientMode) {
            case NETTY:
                nettyOutboundHandler = new NettyOutboundHandler(proxyServer);
                break;
            case HTTP_CLIENT:
                httpOutboundHandler = new HttpOutboundHandler(proxyServer);
                break;
            case OKHTTP_CLIENT:
                okhttpOutboundHandler = new OkhttpOutboundHandler(proxyServer);
                break;
            default:
                throw new RuntimeException("unknown client mode" + clientMode);
        }
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        log.info("<<< channel registered >>>");
        super.channelRegistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("<<< channel active >>>");
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("<<< channel inactive >>>");
        super.channelInactive(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        log.info("<<< channel unregistered >>>");
        super.channelUnregistered(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        tempTimeStamp = System.currentTimeMillis();
        try {
            // get HttpRequest for HttpServerCodec config in ChannelPipeline
            FullHttpRequest fullRequest = (FullHttpRequest) msg;
            String uri = fullRequest.uri();
            log.warn("\n====== channelRead(uri: {}) at {} ======\n", uri, tempTimeStamp);
            // todo: add router or mapping logic
            switch (clientMode) {
                case NETTY:
                    nettyOutboundHandler.handle(fullRequest, ctx);
                    break;
                case HTTP_CLIENT:
                    httpOutboundHandler.handle(fullRequest, ctx);
                    break;
                case OKHTTP_CLIENT:
                    okhttpOutboundHandler.handle(fullRequest, ctx);
                    break;
                default:
                    handleWithMock(fullRequest, ctx);
                    throw new RuntimeException("unknown client mode" + clientMode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
        log.warn("\n====== channel read complete(cost: {} ms) ======\n", System.currentTimeMillis() - tempTimeStamp);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.error(cause.getMessage());
        cause.printStackTrace();
        ctx.close();
    }

    private void handleWithMock(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        FullHttpResponse response = null;
        try {
            String value = "hello, gateway";
            response = new DefaultFullHttpResponse(HTTP_1_1, OK,
                    Unpooled.wrappedBuffer(value.getBytes(StandardCharsets.UTF_8)));
            response.headers().set("Content-Type", "application/json");
            response.headers().setInt("Content-Length", response.content().readableBytes());

        } catch (Exception e) {
            log.error("fatal error: ", e);
            response = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
        } finally {
            if (fullRequest != null) {
                if (!HttpUtil.isKeepAlive(fullRequest)) {
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                } else {
                    Optional.ofNullable(response).ifPresent(resp -> resp.headers().set(CONNECTION, KEEP_ALIVE));
                    ctx.write(response);
                }
            }
        }
    }
}
