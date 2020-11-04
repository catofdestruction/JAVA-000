package org.xy.gateway;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.xy.gateway.inbound.HttpInboundInitializer;

/**
 * netty http server
 * pipeline + filters(inbound requestFilter router outbound responseFilter ...)
 *
 * @author wangxinyu
 * @date 2020/11/3
 */
@Slf4j
@Getter
public class NettyGateway extends HttpServer {

    public final static String GATEWAY_NAME = "XYGateway";
    public final static String GATEWAY_VERSION = "v1.0.0";

    private SslContext sslContext = null;
    private boolean ssl = false;

    private NettyGateway() {
    }

    public NettyGateway(String proxyServer, int proxyPort) {
        super(proxyServer, proxyPort);
    }

    public NettyGateway(String proxyServer, int proxyPort, boolean ssl) {
        super(proxyServer, proxyPort);
        this.ssl = ssl;
    }

    @Override
    public void run() throws Exception {
        log.warn("{}({}) starting...", GATEWAY_NAME, GATEWAY_VERSION);

        if (ssl) {
            SelfSignedCertificate certificate = new SelfSignedCertificate();
            sslContext = SslContextBuilder.forServer(certificate.certificate(), certificate.privateKey()).build();
        }
        // change EventLoopGroup to switch bio
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup(16);
        try {
            // B
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            // tcp & http options
            serverBootstrap.option(ChannelOption.SO_BACKLOG, 128)
                           .option(ChannelOption.TCP_NODELAY, true)
                           .option(ChannelOption.SO_KEEPALIVE, true)
                           .option(ChannelOption.SO_REUSEADDR, true)
                           .option(ChannelOption.SO_RCVBUF, 32 * 1024)
                           .option(ChannelOption.SO_SNDBUF, 32 * 1024)
                           .option(EpollChannelOption.SO_REUSEPORT, true)
                           .childOption(ChannelOption.SO_KEEPALIVE, true)
                           .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
            // E
            // EventLoopGroup is subclass of java Executor
            serverBootstrap.group(bossGroup, workerGroup)
                           // change ServerSocketChannel.class to switch bio
                           .channel(NioServerSocketChannel.class)
                           .handler(new LoggingHandler(LogLevel.INFO))
                           // H
                           // add custom ChannelHandler
                           .childHandler(new HttpInboundInitializer(proxyServer, sslContext));
            // C
            // Channel is a interface (should sync() after bind channel)
            Channel channel = serverBootstrap.bind(proxyPort).sync().channel();
            log.warn("{}({}) started at http://localhost:{} for server: {} ssl: {}",
                    GATEWAY_NAME, GATEWAY_VERSION, proxyPort, proxyServer, ssl);
            // channel sync() will block the process (main thread)
            channel.closeFuture().sync();
        } finally {
            // shut down gracefully
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
