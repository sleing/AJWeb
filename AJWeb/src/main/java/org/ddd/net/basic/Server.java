package org.ddd.net.basic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;

public class Server {
	public static void main(String[] args) throws Exception{
		ServerSocket server = new ServerSocket(888);
		Socket socket = server.accept();
		//Socket socket2 = server.accept();
		//Socket socketCurrent = server.accept();
 		
		InputStreamReader reader = new InputStreamReader(socket.getInputStream());
		BufferedReader buffer_reader=new BufferedReader(reader);
		PrintWriter writer=new PrintWriter(socket.getOutputStream());
		
		Scanner scanner = new Scanner(System.in);
		while(true)
		{
			String request = buffer_reader.readLine();
			
			System.out.println("Client say:" + request);
			
			String line= scanner.nextLine();
			writer.println(line);
			writer.flush();
			if("quit".equals(request)) break;
		}
		
		writer.close();
		buffer_reader.close();
		socket.close();
		server.close();
	}
}
