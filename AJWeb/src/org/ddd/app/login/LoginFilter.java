package org.ddd.app.login;

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

import org.ddd.app.student.factory.DaoFactory;

import io.netty.handler.codec.http.HttpRequest;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/*")
public class LoginFilter extends HttpFilter implements Filter {
    /**
     * @see HttpFilter#HttpFilter()
     */
    public LoginFilter() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}
	/**
	 * @see Filter#doFilter(ServSletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("��ʼ��");
		System.out.println(request.getRequestURI());
		
		if(! request.getRequestURI().startsWith("/app")) 
		{
			chain.doFilter(request, response);
			return;
		}
		
		String name = (String) request.getSession().getAttribute("name");

		if(name == null)
		{
			if(	request.getRequestURI().toLowerCase().endsWith(".css") ||
					request.getRequestURI().toLowerCase().endsWith(".js") ||
					request.getRequestURI().toLowerCase().endsWith(".html") ||
					request.getRequestURI().toLowerCase().endsWith(".CSS") ||
					request.getRequestURI().toLowerCase().endsWith(".JS") ||		
					request.getRequestURI().toLowerCase().endsWith(".HTML") ||	
					request.getRequestURI().toLowerCase().toLowerCase().endsWith(".css") ||
					request.getRequestURI().toLowerCase().toLowerCase().endsWith(".js") ||
					request.getRequestURI().toLowerCase().toLowerCase().endsWith(".html") ||
					request.getRequestURI().endsWith("/hello") ||
					request.getRequestURI().endsWith("/Login") ||
					request.getRequestURI().endsWith("/loginForm.jsp") )
			{
				chain.doFilter(request, response);
			}
			else
			{
				request.getRequestDispatcher("./loginForm.jsp").forward(request, response);
			}
		}
		else
		{
			chain.doFilter(request, response);
		}
	}
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		super.init(fConfig);
		String dao = super.getServletContext().getInitParameter("dao");
		DaoFactory.SetDaoType(dao);
	}
}
