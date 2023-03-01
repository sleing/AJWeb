package org.ddd.web.session;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebFilter("/web/session/*")
public class SecurityFilter extends HttpFilter implements Filter {
	private static final long serialVersionUID = 1L;

	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String username = (String) request.getSession().getAttribute("username");

		if (username == null) {
			if (request.getRequestURI().endsWith("/login") || request.getRequestURI().endsWith("/loginForm.jsp")) {
				chain.doFilter(request, response);
			} else {
				request.getRequestDispatcher("./loginForm.jsp").forward(request, response);
			}
		} else {
			chain.doFilter(request, response);
		}
	}
}
