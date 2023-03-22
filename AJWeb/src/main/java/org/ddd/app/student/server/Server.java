package org.ddd.app.student.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

import org.ddd.app.student.dao.StudentDao;
import org.ddd.app.student.entity.Student;
import org.ddd.app.student.factory.DaoDBFactory;
import org.ddd.app.student.factory.DaoFactoryInterface;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class Server {

	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(888);

		while(true)
		{
			System.out.println("wait for connect.....");
			Socket socket = server.accept();
	 		
			RequestHandler requestHandler = new RequestHandler(socket);
			Thread thread = new Thread(requestHandler);
			thread.start();
		}
		//server.close();
	}

}
