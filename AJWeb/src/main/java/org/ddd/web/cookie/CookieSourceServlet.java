package org.ddd.web.cookie;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/web/cookieSource")
public class CookieSourceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CookieSourceServlet() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Cookie cookie = new Cookie("id", "001");
		cookie.setMaxAge(1000);
		response.addCookie(cookie);
		
		StringBuilder builder = new StringBuilder();
		builder.append("<html>\n").append("<head> <meta charset=\"utf-8\" /> </head>\n").append("<body>\n")
				.append("<h1>添加名为id的Cookie，值为 001</h1>\n").append("</body>\n").append("</html>\n");
		
		response.setContentType("text/html; charset=UTF-8");
		response.setStatus(200);
		response.getWriter().append(builder.toString());
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
