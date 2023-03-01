package org.ddd.net.netty;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.CharsetUtil;

public class NettyOioServer {
	public static void main(String[] args) {
	    EventLoopGroup bossGroup = new NioEventLoopGroup(1);
	    EventLoopGroup workerGroup = new NioEventLoopGroup();
	 
	    try {
	        ServerBootstrap boot = new ServerBootstrap();
	        boot.group(bossGroup, workerGroup)
	            .channel(NioServerSocketChannel.class)
	            .localAddress(8081)
	            .childHandler(new ChannelInitializer<SocketChannel>() {
	                @Override
	                protected void initChannel(SocketChannel ch) throws Exception {
	                    ch.pipeline().addLast(new EchoHandler());
	                }
	            });
	 
	        // start
	        ChannelFuture future = boot.bind().sync();
	        future.channel().closeFuture().sync();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        // shutdown
	        bossGroup.shutdownGracefully();
	        workerGroup.shutdownGracefully();
	    }
	}
	 
	public static class EchoHandler extends ChannelInboundHandlerAdapter {
	    @Override
	    public void channelRead(ChannelHandlerContext ctx, Object msg) {
	        ByteBuf in = (ByteBuf) msg;
	        System.out.println(in.toString(CharsetUtil.UTF_8));
	        ((ByteBuf) msg).writeCharSequence(" server ", CharsetUtil.UTF_8);
	        ctx.write(msg);
	  
	    }
	 
	    @Override
	    public void channelReadComplete(ChannelHandlerContext ctx) {
	        ctx.flush();
	    }
	 
	    @Override
	    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
	        cause.printStackTrace();
	        ctx.close();
	    }
	}
}