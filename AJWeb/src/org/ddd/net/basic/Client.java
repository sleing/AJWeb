package org.ddd.net.basic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) throws Exception{
		Socket socket = new Socket("127.0.0.1",888);
		
		InputStreamReader reader = new InputStreamReader(socket.getInputStream());
		BufferedReader buffer_reader = new BufferedReader(reader);
		//socket.getInputStream()
		//socket.getOutputStream();
		PrintWriter writer = new PrintWriter(socket.getOutputStream());
		Scanner scanner = new Scanner(System.in);
		while(true)
		{
			String readline = scanner.nextLine();
			
			writer.println(readline);
			writer.flush();
			//packet
			String response = buffer_reader.readLine();
			System.out.println("Server say:"+ response);
			if("quit".equals(readline)) break; 
		}
		writer.close(); 
		buffer_reader.close(); 
		socket.close(); 
	}
}
