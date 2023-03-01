
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main</title>
<%
	String username = (String) request.getSession().getAttribute("username");
%>
</head>
<body>
	<%
		if (username != null) {
	%>
	欢迎
	<%=username%>
	<a href="./logout"> 退出</a>
	<%
		} else {
	%>
	<a href="loginForm.jsp"> 亲，请登录</a>
	<%
		}
	%>
</body>
</html>