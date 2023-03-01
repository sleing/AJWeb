package org.ddd.app.student.servlet.ajax;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ddd.app.student.entity.Student;
import org.ddd.app.student.factory.ServiceFactory;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * Servlet implementation class StudentGetter
 */
@WebServlet("/app/StudentSetter")
public class StudentSetter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentSetter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String studentJson = request.getParameter("student");
		JSONObject jSONObject = new JSONObject();
		jSONObject = jSONObject.fromObject(studentJson);
		Student student = (Student) JSONObject.toBean(jSONObject, new Student(), new JsonConfig());;
	 
		ServiceFactory.getInstance().getStudentService().update(student);
		
		
		response.setContentType("text/json,charset=utf-8");
		response.getWriter().write("OK");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
