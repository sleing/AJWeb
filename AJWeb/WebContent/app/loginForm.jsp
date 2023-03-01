<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link rel="icon" href="data:image/ico;base64,aWNv">
</head>
<body>
<%
	String msg = request.getAttribute("msg")==null?"":request.getAttribute("msg").toString();

%>
<form action="./Login" method="post">
	<table>
		<tr><td>name：</td><td><input type="text" name="name" /> </td></tr>
		<tr><td>password：</td><td><input type="password" name="password" /> </td></tr>
		<tr><td><input type="submit" value="login" /> </td></tr>
	</table>
	<h2><%= msg %></h2>
</form>
</body>
</html>