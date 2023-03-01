package org.ddd.net.basic.NIO;

import io.netty.buffer.ByteBuf;
import io.netty.channel.unix.Socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioServer {
    private static final int BUF_SIZE = 1024;
    public static void main(String[] args) {
        Selector selector = null;
        ServerSocketChannel serverChannel = null;
        //19��~27������˷�������channel��ʼ�����̣��ȴ��ſͻ�������������
        try{
            //����һ����·ѡ����
            selector = selector.open();
            //�����÷�����ͨ�����ȴ��ͻ�������
            serverChannel = ServerSocketChannel.open();
            //���óɷ�����ģʽ
            serverChannel.configureBlocking(false);
            //������channelפ���ڱ�����8001�˿�
            serverChannel.socket().bind(new InetSocketAddress(8001),BUF_SIZE);
            //��selector��channel���а�,��ôselector�Ϳ��Կ���serverChannel�������������channel
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("��������8001�˿ڵȺ�");
        }catch (IOException e){
            e.printStackTrace();
            System.exit(1);
        }
        while (true){
            try{
                //��ʼ��ѯ���е�channel������һ��channel�ж�����
                selector.select(1000);
                //��ȡ��������������Ӧ��channel��Ӧ��selectionKey����
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> it = selectedKeys.iterator();
                SelectionKey key = null;
                while (it.hasNext()){
                    key = it.next();
                    it.remove();
                    try{
                        //�������ݵ�ͨ�����д���
                        handleInput(selector, key);
                    }catch (Exception e){
                        if(key != null){
                            key.cancel();
                            if(key.channel() != null)
                                key.channel().close();
                        }
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public static void handleInput(Selector selector, SelectionKey key) throws IOException {
        if(key.isValid()) {
            //�����½����������Ϣ��������Ӹոս����ã�
            if (key.isAcceptable()) {
                //�����µ�����
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                SocketChannel sc = ssc.accept();
                sc.configureBlocking(false);
                //��������ע�ᵽselector��
                sc.register(selector, SelectionKey.OP_READ);
            }
            if(key.isWritable()) {
                ByteBuffer buf = (ByteBuffer)key.attachment();
                buf.flip();
                SocketChannel sc = (SocketChannel) key.channel();
                while(buf.hasRemaining()){
                    sc.write(buf);
                }
                buf.compact();
            }
            if (key.isReadable()) {
                //��ȡ���ݣ���������Ѿ��ǿɶ��ģ�
                SocketChannel sc = (SocketChannel) key.channel();
                ByteBuffer readBuffer = ByteBuffer.allocate(BUF_SIZE);
                int readBytes = sc.read(readBuffer);
                if (readBytes > 0) {
                    readBuffer.flip();
                    byte[] bytes = new byte[readBuffer.remaining()];
                    readBuffer.get(bytes);
                    String request = new String(bytes, "UTF-8");
                    System.out.println("client said: " + request);
                    String response = request + "666";
                    doWrite(sc, response);
                } else if (readBytes < 0) {
                    //�Զ���·�ر�
                    key.cancel();
                    sc.close();
                }
            }
            if(key.isConnectable()){
                System.out.println("isConnectable = true");
            }
        }
    }
    public static void doWrite(SocketChannel channel, String response)throws  IOException{
        if(response != null && response.trim().length() > 0){
            byte[] bytes = response.getBytes();
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            writeBuffer.put(bytes);
            writeBuffer.flip();
            channel.write(writeBuffer);
        }
    }
}

