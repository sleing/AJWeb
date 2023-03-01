package org.ddd.app.student.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

import org.ddd.app.student.dao.StudentDao;
import org.ddd.app.student.entity.Student;
import org.ddd.app.student.factory.DaoDBFactory;
import org.ddd.app.student.factory.DaoFactoryInterface;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class RequestHandler implements Runnable {

	private Socket socket;

	public RequestHandler(Socket socket) {
		super();
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			InputStreamReader reader = new InputStreamReader(socket.getInputStream());
			BufferedReader buffer_reader = new BufferedReader(reader);
			PrintWriter writer = new PrintWriter(socket.getOutputStream());

			String request = buffer_reader.readLine();
			System.out.println("request is:" + request);

			JSONObject jSONObject = new JSONObject();
			jSONObject = jSONObject.fromObject(request);
			String command = jSONObject.getString("command");

			if ("findStudentsByPage".equalsIgnoreCase(command)) {

				int pageSize = (int) jSONObject.get("pageSize");
				int pageIndex = (int) jSONObject.get("pageIndex");

				DaoFactoryInterface daoFactoryInterface = DaoDBFactory.getInstance();
				StudentDao studentDao = daoFactoryInterface.getStudentDao();
				List<Student> students = studentDao.findStudentsByPage(pageIndex, pageSize);

				JSONArray jso = JSONArray.fromObject(students);

				String json = jso.toString();

				writer.println(json);
				writer.flush();
			} else if ("getStudentsCount".equalsIgnoreCase(command)) {

				DaoFactoryInterface daoFactoryInterface = DaoDBFactory.getInstance();
				StudentDao studentDao = daoFactoryInterface.getStudentDao();
				Integer studentsCount = studentDao.getStudentsCount();

				writer.println(studentsCount);
				writer.flush();
			} else {
				if ("add".equalsIgnoreCase(command)) {
					Student student = (Student) JSONObject.toBean((JSONObject) jSONObject.get("student"), new Student(),
							new JsonConfig());
					;
					// System.out.println(student);
					DaoFactoryInterface daoFactoryInterface = DaoDBFactory.getInstance();
					StudentDao studentDao = daoFactoryInterface.getStudentDao();
					studentDao.add(student);

					writer.println("OK");
					writer.flush();
				}
			}
			writer.close();
			buffer_reader.close();
			socket.close();
		} catch (Exception e) {
			System.out.println("处理请求出错，出错原因是："+e.getMessage());
			e.printStackTrace();
		}
	}

}
