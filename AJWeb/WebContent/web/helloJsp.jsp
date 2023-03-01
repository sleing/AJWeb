<!--helloJsp.jsp  -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hello JSP</title>
<%
	String name = request.getParameter("name");
%>
</head>
<body>
<h1>你好，欢迎 <%=name %> 学习JSP！</h1>
</body>
</html>