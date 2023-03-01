package org.ddd.net.basic.AIO;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.UUID;

public class AioClient {
	 private static final int BUF_SIZE=1024;
	    public static void main(String[] args) {
	        try{
	            AsynchronousSocketChannel channel = AsynchronousSocketChannel.open();
	          //与服务器连接成功后，自动执行第11行的任务
	            channel.connect(new InetSocketAddress("localhost", 8001), null, new CompletionHandler<Void, Void>() {
	                @Override
	                public void completed(Void result, Void attachment) {
	                    String str = UUID.randomUUID().toString();
	                    //向服务器写数据成功后，自动执行第17行任务
	                    channel.write(ByteBuffer.wrap(str.getBytes()), null, new CompletionHandler<Integer, Object>() {
	                        @Override
	                        public void completed(Integer result, Object attachment) {
	                            try{
	                                System.out.println("write " + str + ", and wait response");
	                              //开始等待服务器响应
		                         //准备读取空间
	                                ByteBuffer buffer = ByteBuffer.allocate(BUF_SIZE);
	                                //开始读服务器反馈内容， 一旦读取结束，自动执行31行任务
	                                channel.read(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {
	                                    @Override
	                                    public void completed(Integer result, ByteBuffer attachment) {
	                                        //反转此buffer
	                                        attachment.flip();
	                                        CharBuffer charBuffer = CharBuffer.allocate(BUF_SIZE);
	                                        CharsetDecoder decoder = Charset.defaultCharset().newDecoder();
	                                        decoder.decode(attachment, charBuffer, false);
	                                        charBuffer.flip();
	                                        String data = new String(charBuffer.array(), 0, charBuffer.limit());
	                                        System.out.println("server said:" + data);
	                                        try{
	                                            channel.close();
	                                        }catch (Exception e){
	                                            e.printStackTrace();
	                                        }
	                                    }
	                                    @Override
	                                    public void failed(Throwable exc, ByteBuffer attachment) {
	                                        System.out.println("read error " + exc.getMessage());
	                                    }
	                                });
	                                channel.close();
	                            }catch (Exception e){
	                                e.printStackTrace();
	                            }
	                        }

	                        @Override
	                        public void failed(Throwable exc, Object attachment){
	                            System.out.println("write error " + exc.getMessage());
	                        }
	                    });
	                }
	                @Override
	                public void failed(Throwable exc, Void attachment) {
	                    System.out.println("connected failed " + exc.getMessage());
	                }
	            });
	            Thread.sleep(10000);
	        }catch (Exception e){
	            e.printStackTrace();
	        }
	    }
}
