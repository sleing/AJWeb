package org.ddd.app.firstweb;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * Servlet Filter implementation class LogFilter
 */
@WebFilter(
		urlPatterns = { "/*" }, 
		initParams = { 
				@WebInitParam(name = "Site", value = "高级Java程序设计")
		})
 

//实现 Filter 类
public class LogFilter extends HttpFilter  {
  public void  init(FilterConfig config) throws ServletException {
      // 获取初始化参数
      String site = config.getInitParameter("Site");

      // 输出初始化参数
      System.out.println("网站名称: " + site); 
  }
  public void  doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws java.io.IOException, ServletException {
 
      // 输出URL
      System.out.println("时间："+ (new Date()).toString()+"   ：" +request.getRequestURL());

      long start = System.currentTimeMillis();
      // 把请求传回过滤链
      chain.doFilter(request,response);
      
      System.out.println("milliseconds is "+(System.currentTimeMillis()-start));
      
  }
  public void destroy( ){
      /* 在 Filter 实例被 Web 容器从服务移除之前调用 */
  }
}
