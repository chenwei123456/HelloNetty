package com.hbu.Server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * NettyServer
 *
 * @Author chenwei
 * @Date 2018/5/29
 */
public class NettyServer {

    /**
     * Set the server port
     */
    private static final int port = 6789;
    /**
     * Receive and handle connections through NIO
     */
    private static EventLoopGroup group = new NioEventLoopGroup();
    private static  ServerBootstrap b = new ServerBootstrap();

    /**
     * Netty is all created by AbstractBootstrap
     * The client is Bootstrap and the server is ServerBootstrap.
     * @param args
     * @throws InterruptedException
     */

    public static void main(String[] args) throws InterruptedException{

        try {
            b.group(group);
            b.channel(NioServerSocketChannel.class);
            //Set up a Filter
            b.childHandler(new NettyServerFilter());
            //Server binding listening ports
            ChannelFuture f = b.bind(port).sync();
            System.out.println("Server started successfully...");
            //Listening server off monitoring
            f.channel().closeFuture().sync();
        }finally {
            group.shutdownGracefully(); //Close the EventLoopGroup, freeing all resources, including the threads created.
        }
    }
}
