package org.ddd.web;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = "/*",filterName = "logFilter")
public class LogFilter extends HttpFilter implements Filter  {
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException  {
		
		Long startMillis = System.currentTimeMillis();
		Date startDate = new Date();
		
		chain.doFilter(request, response);
		
		Long elapsedMillis = System.currentTimeMillis() - startMillis;
		System.out.println("请求["+startDate+","+new Date()+"](耗时："+elapsedMillis+"毫秒):地址："+request.getRequestURI());		
	}
}
