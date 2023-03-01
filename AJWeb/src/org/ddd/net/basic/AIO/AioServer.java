package org.ddd.net.basic.AIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class AioServer {
    private static final int BUF_SIZE=1024;
    public static void main(String[] args) throws IOException {
        AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open();
        server.bind(new InetSocketAddress("localhost", 8001));
        System.out.println("服务器在8001端口等候");
        //开始等待客户端连接，一旦有连接就执行12行任务
        server.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
        //这里的回调指的是连接被服务器接收后，即通道建立好之后该做什么
            @Override
            public void completed(AsynchronousSocketChannel channel, Object attachment) {
                //当前的客户端请求已经进来，再持续接收客户端的请求
                server.accept(null, this);
               //准备读取空间
                ByteBuffer buffer = ByteBuffer.allocate(BUF_SIZE);
                 //开始读取客户端内容， 读取结束后做17行任务
                channel.read(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {
                    //这里的回调指的是当读取完毕通道中的数据之后该做什么
                    @Override
                    public void completed(Integer result, ByteBuffer attachment) {
                       //反转此buffer
                        attachment.flip();
                        CharBuffer charBuffer = CharBuffer.allocate(BUF_SIZE);
                        CharsetDecoder decoder = Charset.defaultCharset().newDecoder();
                        decoder.decode(attachment, charBuffer, false);
                        charBuffer.flip();
                        String data = new String(charBuffer.array(), 0, charBuffer.limit());
                        System.out.println("client said:" + data);
                        //返回结果给客户端
                        channel.write(ByteBuffer.wrap((data + "666").getBytes()));
                        try{
                            channel.close();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void failed(Throwable exc, ByteBuffer attachment){
                        System.out.println("read err" + exc.getMessage());
                    }
                });
            }
            @Override
            public void failed(Throwable exc, Object attachment) {
                System.out.println("connected failed" + exc.getMessage());
            }
        });
        while(true){
            try{
                Thread.sleep(5000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
