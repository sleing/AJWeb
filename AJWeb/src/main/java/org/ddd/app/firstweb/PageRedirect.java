package org.ddd.app.firstweb;
import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PageRedirect
 */
@WebServlet("/PageRedirect")
public class PageRedirect extends HttpServlet{
    
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
            throws ServletException, IOException
  {
	  request.getSession();
      // 设置响应内容类型
      response.setContentType("text/html;charset=UTF-8");

      // 要重定向的新位置
//      response.sendRedirect("http://www.cqut.edu.cn");
      //response.sendRedirect("PageRedirectTo");
      
      
//      String site = new String("http://www.cqut.edu.cn");
//
//      response.setStatus(response.SC_MOVED_TEMPORARILY);
//      response.setHeader("Location", site);    
      
      
      request.getRequestDispatcher("PageRedirectTo").forward(request, response);
    } 
} 
