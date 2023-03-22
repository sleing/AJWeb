package org.ddd.net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import io.netty.util.CharsetUtil;

public class SimpleAIOServer {
	private static final int BUF_SIZE=1024;
    private static final int PORT = 8080;
    private static final int TIMEOUT = 3000;
	public static void main(String[] args) {
		try {
			final int port = 5555;
			// 首先打开一个ServerSocket通道并获取AsynchronousServerSocketChannel实例：
			AsynchronousServerSocketChannel serverSocketChannel = AsynchronousServerSocketChannel.open();
			// 绑定需要监听的端口到serverSocketChannel:
			serverSocketChannel.bind(new InetSocketAddress(port));
			// 实现一个CompletionHandler回调接口handler，
			// 之后需要在handler的实现中处理连接请求和监听下一个连接、数据收发，以及通信异常。
			CompletionHandler<AsynchronousSocketChannel, Object> handler = new AcceptionCompletionHandler(serverSocketChannel);
			serverSocketChannel.accept(null, handler);
			// 由于serverSocketChannel.accept(null, handler);是一个异步方法，调用会直接返回，
			// 为了让子线程能够有时间处理监听客户端的连接会话，
			// 这里通过让主线程休眠一段时间(当然实际开发一般不会这么做)以确保应用程序不会立即退出。
			
			System.out.println("server is running");
			TimeUnit.MINUTES.sleep(Integer.MAX_VALUE);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static class AcceptionCompletionHandler implements CompletionHandler<AsynchronousSocketChannel, Object> {
		
		private AsynchronousServerSocketChannel asynchronousServerSocketChannel;
		
		public AcceptionCompletionHandler(AsynchronousServerSocketChannel asynchronousServerSocketChannel) {
			super();
			this.asynchronousServerSocketChannel = asynchronousServerSocketChannel;
		}

		public void completed(final AsynchronousSocketChannel asynchronousSocketChannel, final Object attachment) {
			// 继续监听下一个连接请求
			asynchronousServerSocketChannel.accept(attachment, this);
			try {
				System.out.println("接受了一个连接：" + asynchronousSocketChannel.getRemoteAddress().toString());
				ByteBuffer readBuffer = ByteBuffer.allocate(BUF_SIZE);
				// 等待客户端接收数据
				asynchronousSocketChannel.read(readBuffer,readBuffer, new ReadCompletionHandler(asynchronousSocketChannel));

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void failed(final Throwable exc, final Object attachment) {
			System.out.println("出错了：" + exc.getMessage());
		}
	}
	private static class ReadCompletionHandler implements CompletionHandler<Integer, ByteBuffer> {
		private AsynchronousSocketChannel asynchronousSocketChannel;
		
		public ReadCompletionHandler(AsynchronousSocketChannel asynchronousSocketChannel) {
			super();
			this.asynchronousSocketChannel = asynchronousSocketChannel;
		}

		public void completed(final Integer result_num, final ByteBuffer attachment) {
			// 继续监听下一个连接请求
			try {
				if(result_num >0)
				{
					String message = handleRead(attachment);
		            if(message.length() > 0) {
			        	System.out.println("from client ["+Thread.currentThread().getName()+"]"+this.asynchronousSocketChannel+" :"+message);
			        	doWrite(this.asynchronousSocketChannel,message+" response");
			        }
				}
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void failed(final Throwable exc, final ByteBuffer attachment) {
			System.out.println("出错了：" + exc.getMessage());
		}
	    public String handleRead(ByteBuffer readByteBuffer) throws IOException{
	    	StringBuilder messageBuilder = new StringBuilder();

	    	readByteBuffer.flip();
	            
            while(readByteBuffer.hasRemaining()){
            	byte[] bytes = new byte[readByteBuffer.remaining()];
            	readByteBuffer.get(bytes);
            	String msg = new String(bytes,CharsetUtil.UTF_8);
            	messageBuilder.append(msg);
            }
            readByteBuffer.clear();
	            
            return messageBuilder.toString();

//	        if(bytesRead == -1){
//	        	selectionkey.cancel();
//	            socketChannel.close();
//	        }
	    }
		private static void doWrite(AsynchronousSocketChannel asynchronousSocketChannel,String message) throws IOException {
			byte[] msg = message.getBytes(CharsetUtil.UTF_8);
			ByteBuffer byteBuffer = ByteBuffer.allocate(msg.length);
			byteBuffer.put(msg);
			byteBuffer.flip();
			asynchronousSocketChannel.write(byteBuffer,byteBuffer, new CompletionHandler<Integer, Object>() {

				@Override
				public void completed(Integer result, Object attachment) {
					System.out.println("successful writing,waiting new message ");
					ByteBuffer readBuffer = ByteBuffer.allocate(BUF_SIZE);
					// 等待客户端接收数据
					asynchronousSocketChannel.read(readBuffer,readBuffer, new ReadCompletionHandler(asynchronousSocketChannel));

				}

				@Override
				public void failed(Throwable exc, Object attachment) {
					System.err.println("writing failed");
				}
				
			});
		}  	    
	}
}