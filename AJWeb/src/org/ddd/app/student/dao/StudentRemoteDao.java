package org.ddd.app.student.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.ddd.app.student.entity.Student;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class StudentRemoteDao implements StudentDao {

	@Override
	public List<Student> findAll() {
		// TODO Auto-generated method stub

		return null;
	}

	@Override
	public List<Student> findStudentsByPage(Integer pageIndex, Integer pageSize) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("command", "findStudentsByPage");
		params.put("pageIndex", pageIndex);
		params.put("pageSize", pageSize);

		JSONObject jSONObject = new JSONObject();
		jSONObject = jSONObject.fromObject(params);
		String json = jSONObject.toString();
	 
		Socket socket;
		try {
			socket = new Socket("127.0.0.1", 888);

			InputStreamReader reader = new InputStreamReader(socket.getInputStream());
			BufferedReader buffer_reader = new BufferedReader(reader);
			PrintWriter writer = new PrintWriter(socket.getOutputStream());

			writer.println(json);
			writer.flush();

			String response = buffer_reader.readLine();
			System.out.println("Server say:" + response);

			JSONArray ja = JSONArray.fromObject(response);
			
			@SuppressWarnings("unchecked")
			List<Student> students = JSONArray.toList(ja, new Student(),new JsonConfig());

			writer.close();
			buffer_reader.close();
			socket.close();
			
			return students;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Integer getStudentsCount() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("command", "getStudentsCount");

		JSONObject jSONObject = new JSONObject();
		jSONObject = jSONObject.fromObject(params);
		String json = jSONObject.toString();

		Socket socket;
		try {
			socket = new Socket("127.0.0.1", 888);

			InputStreamReader reader = new InputStreamReader(socket.getInputStream());
			BufferedReader buffer_reader = new BufferedReader(reader);
			PrintWriter writer = new PrintWriter(socket.getOutputStream());

			writer.println(json);
			writer.flush();

			String response = buffer_reader.readLine();
			System.out.println("Server say:" + response);

			Integer studentsCount = Integer.parseInt(response);

			writer.close();
			buffer_reader.close();
			socket.close();
			
			return studentsCount; //this.add(new Student());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Student findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(Student student) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("command", "add");
		params.put("student", student);

		JSONObject jSONObject = new JSONObject();
		jSONObject = jSONObject.fromObject(params);
		String json = jSONObject.toString();
	 
		Socket socket;
		try {
			socket = new Socket("127.0.0.1", 888);

			InputStreamReader reader = new InputStreamReader(socket.getInputStream());
			BufferedReader buffer_reader = new BufferedReader(reader);
			PrintWriter writer = new PrintWriter(socket.getOutputStream());

			writer.println(json);
			writer.flush();

			String response = buffer_reader.readLine();
			System.out.println("Server say:" + response);
			 
			writer.close();
			buffer_reader.close();
			socket.close();
			
			return ;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ;
	}

	@Override
	public void update(Student student) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Student student) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub

	}

}
