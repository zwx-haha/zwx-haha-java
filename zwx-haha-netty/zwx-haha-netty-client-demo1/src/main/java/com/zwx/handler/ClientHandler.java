package com.zwx.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.AttributeKey;
import io.netty.util.ReferenceCountUtil;

/**
 * 读取服务器返回的响应信息
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {

        try {
            ByteBuf bb = (ByteBuf) msg;
            byte[] respByte = new byte[bb.readableBytes()];
            bb.readBytes(respByte);
            AttributeKey<byte[]> key = AttributeKey.valueOf("ServerData");
            ctx.channel().attr(key).set(respByte);
            //把客户端的通道关闭
            ctx.channel().close();
        } finally {
            // 必须释放msg数据
            ReferenceCountUtil.release(msg);
        }
    }


    // 数据读取完毕的处理
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        System.out.println("客户端读取数据完毕");
    }

    // 出现异常的处理
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        System.err.println("client 读取数据出现异常");
        ctx.close();
    }

}
