package org.ddd.net.basic.NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

public class NioClient {
    private static final int BUF_SIZE = 1024;
    public static void main(String[] args) {
        Selector selector = null;
        SocketChannel socketChannel = null;
        try{
            selector = Selector.open();
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            //���ֱ�����ӳɹ�����ע�ᵽselector�ϣ�����������Ϣ����Ӧ��
            if(socketChannel.connect(new InetSocketAddress("localhost", 8001))){
                socketChannel.register(selector, SelectionKey.OP_READ);
                doWrite(socketChannel);//һ�����ӳɹ�����д��һ�����ݡ�Ȼ��͵ȴ�����˷���һ�㶫��
            }else{
                socketChannel.register(selector, SelectionKey.OP_CONNECT);
            }
        }catch (IOException e){
            e.printStackTrace();
            System.exit(1);
        }
        while (true){
            try{
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
    /** ������ַ����ŵ��������У�Ȼ��ѻ������ŵ�ͨ����ȥ�������ͨ��д���ݵĹ��̣� */
    public static void doWrite(SocketChannel sc)throws IOException{
        byte[] str = UUID.randomUUID().toString().getBytes();
        ByteBuffer writeBuffer = ByteBuffer.allocate(str.length);
        writeBuffer.put(str);
        writeBuffer.flip();
        sc.write(writeBuffer);
    }
    /**  */
    public static void handleInput(Selector selector, SelectionKey key) throws Exception {
        if(key.isValid()){
            //�ж��Ƿ����ӳɹ�
            SocketChannel sc = (SocketChannel) key.channel();
            if(key.isConnectable()){
                if(sc.finishConnect())
                    sc.register(selector, SelectionKey.OP_READ);
            }
            if(key.isReadable()){
                ByteBuffer readBuffer = ByteBuffer.allocate(BUF_SIZE);
                int readBytes = sc.read(readBuffer);
                if(readBytes > 0){
                    readBuffer.flip();
                    byte[] bytes = new byte[readBuffer.remaining()];
                    readBuffer.get(bytes);
                    String body = new String(bytes, "UTF-8");
                    System.out.println("Server said: " + body);
                }else if(readBytes < 0){
                    //�Զ���·�ر�
                    key.cancel();
                    sc.close();
                }
            }
            Thread.sleep(3000);
            doWrite(sc);
        }
    }
}
