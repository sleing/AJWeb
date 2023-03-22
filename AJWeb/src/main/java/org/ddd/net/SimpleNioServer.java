package org.ddd.net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import io.netty.util.CharsetUtil;

 

public class SimpleNioServer {
	private static final int BUF_SIZE=1024;
    private static final int PORT = 8080;
    private static final int TIMEOUT = 3000;
    public static void main(String[] args)
    {
        selector();
    }
    public static void handleAccept(SelectionKey key) throws IOException{
        ServerSocketChannel ssChannel = (ServerSocketChannel)key.channel();
        SocketChannel sc = ssChannel.accept();
        sc.configureBlocking(false);
        sc.register(key.selector(), SelectionKey.OP_READ,ByteBuffer.allocateDirect(BUF_SIZE));
    }
    public static void handleRead(SelectionKey selectionkey) throws IOException{
        SocketChannel socketChannel = (SocketChannel)selectionkey.channel();
        ByteBuffer buf = (ByteBuffer)selectionkey.attachment();
        long bytesRead = socketChannel.read(buf);
        StringBuilder messageBuilder = new StringBuilder();
        while(bytesRead>0){
            buf.flip();
            while(buf.hasRemaining()){
            	byte[] bytes = new byte[buf.remaining()];
            	buf.get(bytes);
            	String msg = new String(bytes,CharsetUtil.UTF_8);
            	messageBuilder.append(msg);
            }
            buf.clear();
            bytesRead = socketChannel.read(buf);
        }

        if(messageBuilder.length() > 0) {
        	System.out.println("from client ["+Thread.currentThread().getName()+"]"+socketChannel+" :"+messageBuilder.toString());
    		try {
    			Thread.sleep(4000);
    		} catch (InterruptedException e) {
    		}
        	doWrite(socketChannel,messageBuilder.toString()+" response");
        }
        
        if(bytesRead == -1){
        	selectionkey.cancel();
            socketChannel.close();
        }
    }
	private static void doWrite(SocketChannel socketChannel,String message) throws IOException {
		byte[] msg = message.getBytes(CharsetUtil.UTF_8);
		ByteBuffer byteBuffer = ByteBuffer.allocate(msg.length);
		byteBuffer.put(msg);
		byteBuffer.flip();
		socketChannel.write(byteBuffer);
	}    
    public static void handleWrite(SelectionKey key) throws IOException{
    	System.out.println("handleWrite");
        ByteBuffer buf = (ByteBuffer)key.attachment();
        buf.flip();
        SocketChannel sc = (SocketChannel) key.channel();
        while(buf.hasRemaining()){
            sc.write(buf);
        }
        buf.compact();
         
    }
    public static void selector() {
        Selector selector = null;
        ServerSocketChannel ssc = null;
        try{
            selector = Selector.open();
            ssc= ServerSocketChannel.open();
            ssc.socket().bind(new InetSocketAddress(PORT));
            ssc.configureBlocking(false);
            ssc.register(selector, SelectionKey.OP_ACCEPT);
            while(true){
                if(selector.select(TIMEOUT) == 0){
                    System.out.println("server ==");
                    continue;
                }
                Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
                while(iter.hasNext()){
                    SelectionKey key = iter.next();
                    if(key.isAcceptable()){
                        handleAccept(key);
                    }
                    if(key.isReadable()){
                        handleRead(key);
                    }
                    if(key.isWritable() && key.isValid()){
                        handleWrite(key);
                    }
                    if(key.isConnectable()){
                        System.out.println("isConnectable = true");
                    }
                    iter.remove();
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try{
                if(selector!=null){
                    selector.close();
                }
                if(ssc!=null){
                    ssc.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }


}