package org.ddd.app.chat;

import java.io.*;
import java.net.*;
import java.util.*;
public class Server{
	private boolean bStart = false;
	private ServerSocket ss = null;
	List<Client> clients = new ArrayList<Client>();
	private int index = 0;
	public void tcpMonitor() {
		System.out.println("������������У�");
		try {
			ss = new ServerSocket(8885);
			bStart = true;
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			while (bStart) {
				index++;
				Socket s = ss.accept();
				Client c = new Client(s);//ÿ����һ���ͻ��˾�newһ���ͻ��˶���
				clients.add(c);
	            System.out.println("�ͻ������ӳɹ����������Ͽͻ�����Ϊ��" + index);
				new Thread(c).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				ss.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	private class Client implements Runnable {
		DataInputStream dis = null;
		DataOutputStream dos = null;
		Socket s = null;
		boolean bStart = false;
		
		Client(Socket s) {
			this.s = s;
			try {
				dis = new DataInputStream(s.getInputStream());
				dos = new DataOutputStream(s.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			bStart = true;
		}
		public void sendToEveryClient(String str) {
			try {
				dos.writeUTF(str);
				dos.flush();
			} catch (IOException e) {	
				clients.remove(this);	
				
			}
		}
	
		public void run() {
			try {
				while (bStart) {
					String str = dis.readUTF();
					for (int i = 0; i < clients.size(); i++) {
						Client c = clients.get(i);
						dos.writeUTF(str);
						dos.flush();
					}
				}
			} catch (EOFException e) {
				clients.remove(this);
				index--;
				System.out.println("�ͻ����ѶϿ����������Ͽͻ�����Ϊ��" + (index-1));
			} catch (SocketException e) {
				clients.remove(this);
				index--;
				System.out.println("�ͻ����ѶϿ����������Ͽͻ�����Ϊ��" + (index-1));
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (s != null)
						s.close();
					if (dis != null)
						dis.close();
					if (dos != null)
						dos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		
	}
	public static void main(String[] args) {
		Server ts = new Server();
		ts.tcpMonitor();
	}
}
	