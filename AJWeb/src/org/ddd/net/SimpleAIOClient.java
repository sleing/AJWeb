package org.ddd.net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import io.netty.util.CharsetUtil;

public class SimpleAIOClient {
	private static final int BUF_SIZE = 1024;
	private static final int PORT = 8080;
	private static final int TIMEOUT = 3000;

	public static void main(String[] args) {
		try {
			// 打开一个SocketChannel通道并获取AsynchronousSocketChannel实例
			AsynchronousSocketChannel client = AsynchronousSocketChannel.open();
			// 连接到服务器并处理连接结果
			client.connect(new InetSocketAddress("127.0.0.1", 5555), client, new ConnectionCompletionHandler());
			
			System.out.println("client is running");
			TimeUnit.MINUTES.sleep(Integer.MAX_VALUE);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static class ConnectionCompletionHandler implements CompletionHandler<Void, AsynchronousSocketChannel> {
		@Override
		public void completed(final Void result, final AsynchronousSocketChannel asynchronousSocketChannel) {
			System.out.println("成功连接到服务器!");
			try {
				// 给服务器发送信息并等待发送完成
				doWrite(asynchronousSocketChannel);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void failed(Throwable exc, AsynchronousSocketChannel attachment) {
			// TODO Auto-generated method stub

		}
	}

	private static int messageIndex = 0;

	private static void doWrite(AsynchronousSocketChannel asynchronousSocketChannel) throws IOException {
		byte[] msg = ("msg_" + messageIndex++).getBytes(CharsetUtil.UTF_8);
		ByteBuffer byteBuffer = ByteBuffer.allocate(msg.length);
		byteBuffer.put(msg);
		byteBuffer.flip();
		asynchronousSocketChannel.write(byteBuffer, byteBuffer,new WriteCompletionHandler(asynchronousSocketChannel));
	}
	private static class  WriteCompletionHandler implements CompletionHandler<Integer, Object> {
		private AsynchronousSocketChannel asynchronousSocketChannel;
		
		
		public WriteCompletionHandler(AsynchronousSocketChannel asynchronousSocketChannel) {
			super();
			this.asynchronousSocketChannel = asynchronousSocketChannel;
		}

		@Override
		public void completed(Integer result, Object attachment) {
			System.out.println("successful writing ");
			ByteBuffer readBuffer = ByteBuffer.allocate(BUF_SIZE);
			// 等待服务端接收数据
			asynchronousSocketChannel.read(readBuffer, readBuffer,
					new ReadCompletionHandler(asynchronousSocketChannel));
			System.out.println("waiting message from server");
		}

		@Override
		public void failed(Throwable exc, Object attachment) {
			System.err.println("writing failed");
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
				if (result_num > 0) {
					String message = handleRead(attachment);
					if (message.length() > 0) {
						System.out.println("from server" + this.asynchronousSocketChannel + " :" + message);
						try {
							Thread.sleep(TIMEOUT);
						} catch (InterruptedException e) {
						}
						doWrite(this.asynchronousSocketChannel);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void failed(final Throwable exc, final ByteBuffer attachment) {
			System.out.println("出错了：" + exc.getMessage());
		}

		public String handleRead(ByteBuffer readByteBuffer) throws IOException {
			StringBuilder messageBuilder = new StringBuilder();

			readByteBuffer.flip();

			while (readByteBuffer.hasRemaining()) {
				byte[] bytes = new byte[readByteBuffer.remaining()];
				readByteBuffer.get(bytes);
				String msg = new String(bytes, CharsetUtil.UTF_8);
				messageBuilder.append(msg);
			}
			readByteBuffer.clear();

			return messageBuilder.toString();

//	        if(bytesRead == -1){
//	        	selectionkey.cancel();
//	            socketChannel.close();
//	        }
		}
	}
}