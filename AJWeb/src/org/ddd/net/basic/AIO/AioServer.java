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
        System.out.println("��������8001�˿ڵȺ�");
        //��ʼ�ȴ��ͻ������ӣ�һ�������Ӿ�ִ��12������
        server.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
        //����Ļص�ָ�������ӱ����������պ󣬼�ͨ��������֮�����ʲô
            @Override
            public void completed(AsynchronousSocketChannel channel, Object attachment) {
                //��ǰ�Ŀͻ��������Ѿ��������ٳ������տͻ��˵�����
                server.accept(null, this);
               //׼����ȡ�ռ�
                ByteBuffer buffer = ByteBuffer.allocate(BUF_SIZE);
                 //��ʼ��ȡ�ͻ������ݣ� ��ȡ��������17������
                channel.read(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {
                    //����Ļص�ָ���ǵ���ȡ���ͨ���е�����֮�����ʲô
                    @Override
                    public void completed(Integer result, ByteBuffer attachment) {
                       //��ת��buffer
                        attachment.flip();
                        CharBuffer charBuffer = CharBuffer.allocate(BUF_SIZE);
                        CharsetDecoder decoder = Charset.defaultCharset().newDecoder();
                        decoder.decode(attachment, charBuffer, false);
                        charBuffer.flip();
                        String data = new String(charBuffer.array(), 0, charBuffer.limit());
                        System.out.println("client said:" + data);
                        //���ؽ�����ͻ���
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
