package org.ddd.web.session;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/web/session/login")
public class LoginServlet  extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("username");
		String password = request.getParameter("password");
		
		if("xcy".equals(name) && "ddd".equals(password))
		{
			request.getSession().setAttribute("username", name);
			request.getRequestDispatcher("./main.jsp").forward(request, response);
		}
		else
		{
			request.setAttribute("error", "用户名、密码不正确，请检查");
			request.getRequestDispatcher("./loginForm.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
