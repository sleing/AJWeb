package org.ddd.net.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

public class NettyOioClient {
	public static void main(String[] args) {
		 
	    EventLoopGroup group = new NioEventLoopGroup();
	    try {
	        Bootstrap b = new Bootstrap();
	        b.group(group)
	        .channel(NioSocketChannel.class)
	        .option(ChannelOption.TCP_NODELAY, true)
	        .handler(new ChannelInitializer<SocketChannel>() {
	            @Override
	            public void initChannel(SocketChannel ch) throws Exception {
	                ChannelPipeline p = ch.pipeline();
	                //p.addLast(new LoggingHandler(LogLevel.INFO));
	                p.addLast(new EchoClientHandler());
	            }
	        });
	 
	        // Start the client.
	        ChannelFuture f = b.connect("localhost", 8081).sync();
	        f.channel().closeFuture().sync();
	    } catch (Exception e) {
	      e.printStackTrace();
	    } finally {
	        group.shutdownGracefully();
	    }
	}
	 
	public static class EchoClientHandler extends ChannelInboundHandlerAdapter {
	 
	    private final ByteBuf message;
	 
	    public EchoClientHandler() {
	        message = Unpooled.buffer(256);
	        message.writeBytes("hello netty".getBytes(CharsetUtil.UTF_8));
	    }
	 
	    @Override
	    public void channelActive(ChannelHandlerContext ctx) {
	        ctx.writeAndFlush(message);
	    }
	 
	    @Override
	    public void channelRead(ChannelHandlerContext ctx, Object msg) {
	        System.out.println(((ByteBuf) msg).toString(CharsetUtil.UTF_8));
	        ((ByteBuf) msg).writeCharSequence(" client ", CharsetUtil.UTF_8);
	        ctx.write(msg);
	         
	        try {
	            Thread.sleep(5000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }
	 
	    @Override
	    public void channelReadComplete(ChannelHandlerContext ctx) {
	        ctx.flush();
	    }
	 
	    @Override
	    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
	        // Close the connection when an exception is raised.
	        cause.printStackTrace();
	        ctx.close();
	    }
	}	 
}