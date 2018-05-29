package com.hbu.Server;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.net.InetAddress;
import java.util.Date;

/**
 * Service-side business logic.
 *
 * @Author chenwei
 * @Date 2018/5/29
 */
public class NettyServerHandler extends SimpleChannelInboundHandler<String> {

    /**
     *  When a message is received, the message is returned.
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

        //If the server receives the message, it will print directly.
        System.out.println("The message received by the server is:"+ msg);
        if("quit".equals(msg)){
            ctx.close();
        }
        Date date = new Date();
        //Message returned to the client.
        ctx.writeAndFlush(date+"\n");
    }
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception{
        System.out.println("The address of the client connected to the server is:" + ctx.channel().remoteAddress());
        ctx.writeAndFlush("Client"+ InetAddress.getLocalHost().getHostName() + "successfully establishing a connection with the server！ \n");
        super.channelActive(ctx);
    }
}
