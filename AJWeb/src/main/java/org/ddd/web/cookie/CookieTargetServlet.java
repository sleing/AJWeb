package org.ddd.web.cookie;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/web/cookieTarget")
public class CookieTargetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CookieTargetServlet() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Cookie[] cookies = request.getCookies();
		String cookieValue = null;
		for (Cookie cookie : cookies) 
		{
			if("id".equals(cookie.getName()))
			{
				cookieValue = cookie.getValue();
				break;
			}
		}
		
		StringBuilder builder = new StringBuilder();
		builder.append("<html>\n").append("<head> <meta charset=\"utf-8\" /> </head>\n")
		.append("<body>\n")
		.append("<h1>取得名为id的Cookie，值为:"+cookieValue+"</h1>\n")
		.append("</body>\n").append("</html>\n");
		
		response.setContentType("text/html; charset=UTF-8");
		response.setStatus(200);
		response.getWriter().append(builder.toString());
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
