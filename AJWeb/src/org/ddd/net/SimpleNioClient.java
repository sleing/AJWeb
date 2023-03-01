package org.ddd.net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import io.netty.util.CharsetUtil;

public class SimpleNioClient {
	private static final int BUF_SIZE=1024;
    private static final int PORT = 8080;
    private static final int TIMEOUT = 3000;
	public static void main(String[] args) {
		client();
	}
	public static void client(){
	        ByteBuffer buffer = ByteBuffer.allocate(BUF_SIZE);
	        SocketChannel socketChannel = null;
	        try
	        {
	        	Selector selector = Selector.open();
	        	
	            socketChannel = SocketChannel.open();
	            socketChannel.configureBlocking(false);
	            if(socketChannel.connect(new InetSocketAddress("localhost",PORT)))
	            {
	            	socketChannel.register(selector, SelectionKey.OP_READ);
	            	doWrite(socketChannel);
	            }
	            else
	            {
	            	socketChannel.register(selector, SelectionKey.OP_CONNECT);
	            	
	            }
	            
	            while(true)
	            {
	            	try
	            	{
	                    if(selector.select(TIMEOUT) == 0){
	                        System.out.println("client ==");
	                        continue;
	                    }
	            		Set<SelectionKey> selectionKeys = selector.selectedKeys();
	            		Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
	            		while(keyIterator.hasNext())
	            		{
	            			SelectionKey selectionKey = keyIterator.next();
	            			keyIterator.remove();
	            			try
	            			{
	                            if(selectionKey.isConnectable()){
	                                handleConnection(selectionKey);
	                                doWrite(socketChannel);
	                            }
	                            if(selectionKey.isReadable()){
	                                handleRead(selectionKey);
	                                Thread.sleep(3000);
	                                doWrite(socketChannel);
	                            }
	            			}
	            			catch (Exception e) {
								System.err.println(e.toString());
								break;
							}
	            		}
	            	}
	            	catch (Exception e) {
						// TODO: handle exception
					}
	            }
	 
	        }
	        catch (IOException   e)
	        {
	            e.printStackTrace();
	        }
	        finally{
	            try{
	                if(socketChannel!=null){
	                    socketChannel.close();
	                }
	            }catch(IOException e){
	                e.printStackTrace();
	            }
	        }
	    }
	private static void handleConnection(SelectionKey selectionKey) throws ClosedChannelException, IOException {
		SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
		if(socketChannel.finishConnect())
		{
			socketChannel.register(selectionKey.selector(), SelectionKey.OP_READ);
		}
	}
    public static void handleRead(SelectionKey selectionKey) throws IOException{
        SocketChannel sc = (SocketChannel)selectionKey.channel();
        ByteBuffer buf = ByteBuffer.allocate(BUF_SIZE);
        long bytesRead = sc.read(buf);
        System.out.print("from Server:");
        while(bytesRead>0){
            buf.flip();
            while(buf.hasRemaining()){
            	byte[] bytes = new byte[buf.remaining()];
            	buf.get(bytes);
            	String msg = new String(bytes,CharsetUtil.UTF_8);
                System.out.print(msg);
            }
            System.out.println();
            buf.clear();
            bytesRead = sc.read(buf);
        }
        if(bytesRead == -1){
        	selectionKey.cancel();
            sc.close();
        }
    }	
	private static int messageIndex=0;
	
	private static void doWrite(SocketChannel socketChannel) throws IOException {
		byte[] msg = ("msg_"+messageIndex++).getBytes(CharsetUtil.UTF_8);
		ByteBuffer byteBuffer = ByteBuffer.allocate(msg.length);
		byteBuffer.put(msg);
		byteBuffer.flip();
		socketChannel.write(byteBuffer);
	}
	
}

