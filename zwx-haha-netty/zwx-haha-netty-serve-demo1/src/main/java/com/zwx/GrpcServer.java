package com.zwx;

import com.zwx.grpc.ProxyServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author: haha
 * @date : 2020-11-14 23:02
 */
public class GrpcServer {
    private final int port;

    private final EventLoopGroup group;
    private Channel channel;

    public GrpcServer(int port) {
        this.port = port;
        group = new NioEventLoopGroup();
    }

    public void start() {
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.option(ChannelOption.SO_BACKLOG, 1024);
            b.group(group)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ProxyServerInitializer());
            channel = b.bind(port).sync().channel();
            System.out.println("Proxy server start success");
            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void awaitTermination() {
    }

    public void stop() {
        group.shutdownGracefully();
    }

    public static void main(String[] args) {
        GrpcServer grpcServer = new GrpcServer(8001);
        grpcServer.start();
    }
}
