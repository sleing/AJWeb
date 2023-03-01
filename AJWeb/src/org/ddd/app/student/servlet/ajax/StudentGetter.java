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

/**
 * Servlet implementation class StudentGetter
 */
@WebServlet("/app/StudentGetter")
public class StudentGetter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentGetter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		Student student = ServiceFactory.getInstance().getStudentService().findById(Integer.parseInt(id));
		JSONObject jSONObject = new JSONObject();
		jSONObject = jSONObject.fromObject(student);
		String studnetJson= jSONObject.toString();
		response.setContentType("text/json,charset=utf-8");
		response.getWriter().write(studnetJson);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
