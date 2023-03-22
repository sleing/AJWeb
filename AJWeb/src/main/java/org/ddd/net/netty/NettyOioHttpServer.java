package org.ddd.net.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;

public class NettyOioHttpServer {
	public static void main(String[] args) {
	    EventLoopGroup bossGroup = new NioEventLoopGroup(1);
	    EventLoopGroup workerGroup = new NioEventLoopGroup();
	 
	    try {
	        ServerBootstrap boot = new ServerBootstrap();
	        boot.group(bossGroup, workerGroup)
	            .channel(NioServerSocketChannel.class)
	            .localAddress(8080)
	            .childHandler(new ChannelInitializer<SocketChannel>() {
	                @Override
	                protected void initChannel(SocketChannel ch) throws Exception {
	                    ch.pipeline()
	                            .addLast("decoder", new HttpRequestDecoder())
	                            .addLast("encoder", new HttpResponseEncoder())
	                            .addLast("aggregator", new HttpObjectAggregator(512 * 1024))
	                            .addLast("handler", new HttpHandler());
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
	 
	public static class HttpHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
	    @Override
	    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {
	        DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
	                HttpResponseStatus.OK,
	                Unpooled.wrappedBuffer("hello netty".getBytes()));
	 
	        HttpHeaders heads = response.headers();
	        heads.add(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.TEXT_PLAIN + "; charset=UTF-8");
	        heads.add(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes()); // 3
	        heads.add(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
	 
	        ctx.writeAndFlush(response);
	    }
	}
}
