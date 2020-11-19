package com.zwx;

import com.zwx.handler.ClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;

import java.io.UnsupportedEncodingException;

/**
 * 客户端发送请求
 */
public class ClientNetty {

    // 要请求的服务器的ip地址
    private String ip;
    // 服务器的端口
    private int port;
    private static ChannelFuture cf;

    public ClientNetty() {
    }

    public ClientNetty(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    // 请求端主题
    private void action() throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        Bootstrap bs = new Bootstrap();
        bs.group(bossGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        // 处理来自服务端的响应信息
                        socketChannel.pipeline().addLast(new ClientHandler());
                    }
                });
        // 客户端开启
        cf = bs.connect(ip, port).sync();
    }

    public static byte[] send(String ip, int port, byte[] req) throws InterruptedException, UnsupportedEncodingException {
        if (cf == null) {
            ClientNetty clientNetty = new ClientNetty(ip, port);
            clientNetty.action();
        }
        // 发送客户端的请求
        cf.channel().writeAndFlush(Unpooled.copiedBuffer(req));
        // 等待直到连接中断
        cf.channel().closeFuture().sync();
        //接收服务端返回的数据
        AttributeKey<byte[]> key = AttributeKey.valueOf("ServerData");
        byte[] result = cf.channel().attr(key).get();
        System.out.println("服务端返回：" + new String(result, "utf-8"));
        return result;
    }

    public static void main(String[] args) throws InterruptedException, UnsupportedEncodingException {
        String req = "haha";
        ClientNetty.send("127.0.0.1", 8080, req.getBytes());
    }

}
