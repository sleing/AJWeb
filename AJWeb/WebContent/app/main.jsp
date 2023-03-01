<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>主页</title>
<%
	String name = (String)request.getSession().getAttribute("name");
%>
</head>
<body>
<a href="./student/StudentList?pageIndex=0">student management</a> <br>
<a href="./generic/GenericList?pageIndex=0&clazz=org.cqut.ddd.generic.entity.Student">student management by Generic method</a> <br>
<a href="./generic/GenericList?pageIndex=0&clazz=org.cqut.ddd.generic.entity.Teacher">teacher management by Generic method</a> <br>

<% if(name != null) { %>
	welcome <%=name %> <a href="./Logout"> 退出</a>
<% } else { %>
	<a href="loginForm.jsp"> 亲，请登录</a>
<% } %>

</body>
</html>