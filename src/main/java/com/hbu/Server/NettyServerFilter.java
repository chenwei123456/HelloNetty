package com.hbu.Server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
/**
 * NettyServerFilter
 * @Author chenwei
 * @Date 2018/5/29
 */
public class NettyServerFilter extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline ph = ch.pipeline();
        // Decoder that ends with ("\n").
        ph.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
        // Decoding and encoding should be consistent with the client.
        ph.addLast("decoder", new StringDecoder());
        ph.addLast("encoder", new StringEncoder());
        /**
         *  Service-side business logic.
         */
        ph.addLast("handler", new NettyServerHandler());
    }
}