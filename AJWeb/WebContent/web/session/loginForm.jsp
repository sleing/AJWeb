<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录</title>
<%
	String error =(String)request.getAttribute("error");
%>
</head>
<body>
<form action="./login" method="post">
	<table>
		<tr><td colspan=2><h3>登录</h3></td></tr>
		<%if(error != null) {%>
			<tr><td colspan=2><h3 style="color:red"><%=error %></h3></td></tr>
		<%} %>
		<tr><td>用户名：</td><td><input type="text" name="username" /> </td></tr>
		<tr><td>密   码：</td><td><input type="password" name="password" /> </td></tr>
		<tr><td><input type="submit" value="登录" /> </td></tr>
	</table>
</form>
</body>
</html>