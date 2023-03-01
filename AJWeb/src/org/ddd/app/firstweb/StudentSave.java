package org.ddd.app.firstweb;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class StudentSave
 */
@WebServlet("/StudentSave")
public class StudentSave extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentSave() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		String gender = request.getParameter("gender");
		
		System.out.println("name:"+name+";age:"+age+";gender:"+gender);
		
		StringBuilder builder = new StringBuilder();
		builder.append("<html>")
			.append("<bod>")
			.append("<table border=1>")
			
			.append("<tr>")
			.append("<td>")
			.append("name:")
			.append("</td>")
			.append("<td>")
			.append(name)
			.append("</td>")
			.append("</tr>")

			.append("<tr>")
			.append("<td>")
			.append("age:")
			.append("</td>")
			.append("<td>")
			.append(age)
			.append("</td>")
			.append("</tr>")
			
			.append("<tr>")
			.append("<td>")
			.append("gender:")
			.append("</td>")
			.append("<td>")
			.append(gender)
			.append("</td>")
			.append("</tr>")
			
			.append("</table>")
			
			.append("</body>")
			.append("</html>");
		
		response.getWriter().append(builder.toString());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
