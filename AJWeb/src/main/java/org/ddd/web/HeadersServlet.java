package org.ddd.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/web/headers")
public class HeadersServlet extends HttpServlet {
public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
{
StringBuilder builder = new StringBuilder();

builder.append("<html>\n").append("<head> <meta charset=\"utf-8\" /> </head>\n").append("<body>\n");
builder.append( "<h2 align=\"center\">HTTP请求头</h2>\n").append("<table width=\"100%\" border=\"1\" align=\"center\">\n" );
builder.append("<tr bgcolor=\"#DDDDDD\">\n <th>Header名称</th><th>Header值</th>\n</tr>\n");

Enumeration headerNames = request.getHeaderNames();

while(headerNames.hasMoreElements()) {
    String headerName = (String)headerNames.nextElement();
    builder.append("<tr><td>" + headerName + "</td>\n");
    String headerValue = request.getHeader(headerName);
    builder.append("<td> " + headerValue + "</td></tr>\n");
}
builder.append("</table>\n</body></html>");

response.setContentType("text/html;charset=UTF-8");
response.getWriter().println(builder.toString());

}
public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
}
}