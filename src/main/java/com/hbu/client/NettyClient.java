package com.hbu.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.IOException;

/**
 * NettyClient
 *
 * @author chenwei
 *
 * @date 2008/5/29
 *
 */
public class NettyClient {

    public static String host = "127.0.0.1";
    public static int port = 6789;
    /**
     *   Receiving and processing connections through NIO.
     */
    private static EventLoopGroup group = new NioEventLoopGroup();
    private static Bootstrap b = new Bootstrap();
    private static Channel ch;

    /**
     * Netty is all created by AbstractBootstrap
     * The client is Bootstrap and the server is ServerBootstrap.
     * @param args
     * @throws InterruptedException
     * @throws IOException
     */
    public static void main(String[] args) throws InterruptedException, IOException {
        System.out.println("Client started successfully...");
        b.group(group);
        b.channel(NioSocketChannel.class);
        b.handler(new NettyClientFilter());
        //Client connects to server
        ch = b.connect(host, port).sync().channel();
        star();
    }

    public static void star() throws IOException{
        String str="Hello Netty,I am ChenWei.";
        ch.writeAndFlush(str+ "\r\n");
        System.out.println(":"+str);
    }

}
