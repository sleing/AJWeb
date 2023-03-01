package org.ddd.app.firstweb;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Guess
 */
@WebServlet("/guess")
public class Guess extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
//	private static int num ; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Guess() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String cmd = request.getParameter("cmd");
		
		if("start".equals(cmd))
		{
			int num = (int)(Math.random()*100);
			request.getSession().setAttribute("number", num);
		}
	
		
		response.getWriter().println("<!DOCTYPE html>");
		response.getWriter().println("<html>");
		response.getWriter().println("<head>");
		response.getWriter().println("<meta charset=\"UTF-8\">");
		response.getWriter().println("<title>Guess Game</title>");
		response.getWriter().println("</head>");
		response.getWriter().println("<body>");
		response.getWriter().println("<form action=\"./guess?cmd=start\" method=\"post\">");
		response.getWriter().println("<input type=\"submit\" value=\"start\"/>	 ");
		response.getWriter().println("</form>");
		response.getWriter().println("<form action=\"./guess?cmd=guess\" method=\"post\">");
		response.getWriter().println("	<table>");
		response.getWriter().println("		<tr><td><h1>Guess Game</h1></td></tr>");
		response.getWriter().println("		<tr><td>number:</td><td><input type=\"number\" name=\"number\"/></td></tr>");
		response.getWriter().println("		<tr><td></td></tr><tr><td><input type=\"submit\" value=\"guess\"/></td></tr>	");		
		response.getWriter().println("	</table>");
		if("start".equals(cmd))
		{
			response.getWriter().println("<h1>game begin</h1>");
			
		}
		else if("guess".equals(cmd))
		{
			int guessNum = Integer.parseInt(request.getParameter("number"));
			int num = (int)request.getSession().getAttribute("number");
			if(guessNum == num)
			{
				response.getWriter().println("<h1>it's right,you are so clever!</h1>");
			}
			else if (guessNum<num)
			{
				response.getWriter().println("<h1>too small!</h1>");
			}
			else if (guessNum>num)
			{
				response.getWriter().println("<h1>too large!</h1>");
			}
		}
		
		response.getWriter().println("</form>");
		response.getWriter().println("</body>");
		response.getWriter().println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
