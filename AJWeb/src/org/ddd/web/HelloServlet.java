package org.ddd.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public HelloServlet() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String name = request.getParameter("name");

		StringBuilder builder = new StringBuilder();
		builder.append("<html>\n").append("<head> <meta charset=\"utf-8\" /> </head>\n").append("<body>\n")
				.append("<h1>你好，" + name + "！欢迎学习Servlet</h1>\n").append("</body>\n").append("</html>\n");
		
		response.setContentType("text/html; charset=UTF-8");
		response.setStatus(200);
		response.getWriter().append(builder.toString());
		
//		response.getWriter().append("大家都是好学生");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
