package org.ddd.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ddd.app.student.dao.StudentDBDao;
import org.ddd.app.student.entity.Student;
import org.ddd.app.student.factory.ServiceFactory;

@WebServlet("/web/StudentSave")
public class StudentSave extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public StudentSave() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String name = request.getParameter("name");
		Integer age = Integer.parseInt(request.getParameter("age"));
		String gender = request.getParameter("gender");
		String school = request.getParameter("school");

		List<String> hobbies = new ArrayList<String>();
		if (request.getParameter("hobby_basketball") != null)
			hobbies.add("篮球");
		if (request.getParameter("hobby_badminton") != null)
			hobbies.add("羽毛球");
		if (request.getParameter("hobby_pingpong") != null)
			hobbies.add("兵乓球");

		String resume = request.getParameter("resume");

		StringBuilder builder = new StringBuilder();
		builder.append("<html>\n").append("<head> <meta charset=\"utf-8\" /> </head>\n").append("<body>\n")
				.append("<h1>新增的学生信息</h1>\n").append("<table>\n").append("<tr><td>姓名：</td><td>").append(name)
				.append("</td></tr>\n").append("<tr><td>年龄：</td><td>").append(age).append("</td></tr>\n")
				.append("<tr><td>性别：</td><td>").append(gender).append("</td></tr>\n").append("<tr><td>学院：</td><td>")
				.append(school).append("</td></tr>\n").append("<tr><td>爱好：</td><td>").append(hobbies)
				.append("</td></tr>\n").append("<tr><td>简历：</td><td>").append(resume).append("</td></tr>\n")
				.append("</table>\n").append("</body>\n").append("</html>\n");

		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().append(builder.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
