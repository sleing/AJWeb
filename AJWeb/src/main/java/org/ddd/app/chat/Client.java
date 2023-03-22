package org.ddd.app.chat;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
//�û���
public class Client extends JFrame{
	Socket s = null;
	DataOutputStream dos = null;
	DataInputStream dis = null;
	boolean bConnected = false;
	Thread t = new Thread(new RecToServer());
	
    TextArea taContent = new TextArea();
	JTextArea tfTxt = new JTextArea(10,5);
	JScrollPane sp=new JScrollPane(tfTxt);
	JButton send = new JButton("����");
	JButton connect = new JButton("����");
	JButton clear = new JButton("���");
	JPanel p2 = new JPanel();
public void launchFrame() {
		p2.add(send);
		p2.add(connect);
		p2.add(clear);
	    	
		Container con = this.getContentPane();
		con.add(taContent, "North");
		con.add(sp,"Center");
		con.add(p2, "South");
		
		
		this.setSize(300, 400);
		this.setLocation(400, 400);
		this.setTitle("����");
		tfTxt.setLineWrap(true);
		taContent.setEditable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		connect.addActionListener(new Connect());
		send.addActionListener(new SendMsg());
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				taContent.setText("");
			}
		});
		}
public static void main(String[] args) {
	Client tc = new Client();
		tc.launchFrame();
}

public void connectToServer() {
	try {	
		s = new Socket("localhost", 8885);
		dos = new DataOutputStream(s.getOutputStream());
		dis = new DataInputStream(s.getInputStream());
		bConnected = true;	
	} catch (BindException e) {
		System.out.println("û�����ӵ�������");
	} catch (UnknownHostException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
}

public void disConnect() {
	try {
		if (s != null) {
			s.close();
		}	
		if (dos != null) {
			dos.close();
		}
		if (dis != null) {
			dis.close();
		}
		System.exit(0);
	} catch (IOException e) {
		e.printStackTrace();
	}
}

private class Connect implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "����") {
			connectToServer();
			JOptionPane.showMessageDialog(Client.this,
					"���ӵ�������", "�ɹ���ʾ", 1);
			try {
				t.start();
			} catch (IllegalThreadStateException ex) {
			
			}
			connect.setText("�˳�");} 
		else if (e.getActionCommand() == "�˳�"){
			disConnect();
		}	
	}
}
private class SendMsg implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		if (connect.getActionCommand() == "����") {
			JOptionPane.showMessageDialog(Client.this,
					"û�����ӵ�������", "������ʾ", 1);
		} else {
			String str = tfTxt.getText();
			tfTxt.setText("");	
			try {
				dos.writeUTF(str);
				dos.flush();//��ջ�����
			} catch (SocketException ex) {
				JOptionPane.showMessageDialog(Client.this,
						"û�����ӵ�������", "������ʾ", 1);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}}}
private class RecToServer implements Runnable {
	public void run() {
		try {
			while (bConnected) {
				String str = dis.readUTF();
				taContent.append(str + "\n");
			}
		} catch (SocketException e) {
	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
}
		
	
	

