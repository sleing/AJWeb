<%@page import="org.ddd.app.student.entity.Student"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%
	Student student = (Student)request.getAttribute("student");
%>
<body>
<form action="./StudentUpdate" method="post">
	<table>
		<tr><td>id</td><td><input type="text" name="id" readonly="readonly"  value="<%=student.getId() %>" /> </td></tr>
		<tr><td>name</td><td><input type="text" name="name"  value="<%=student.getName() %>" /> </td></tr>
		<tr><td>age</td><td><input type="number" name="age"  value="<%=student.getAge() %>" /> </td></tr>
		<tr><td><input type="submit" value="save" /> </td></tr>
	</table>
 
</form>
</body>
</html>