<!-- web/forward/studentView.jsp -->
<%@page import="org.ddd.web.forward.Student"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>学生信息展示</title>
</head>
<%
 Student student = (Student)request.getAttribute("student");
%>
<body>
<h1>新增的学生信息</h1>
<table>
<tr><td>姓名：</td><td><%=student.getName() %></td></tr>
<tr><td>年龄：</td><td><%=student.getAge() %></td></tr>
<tr><td>性别：</td><td><%=student.getGender()%></td></tr>
<tr><td>学院：</td><td><%=student.getSchool() %></td></tr>
<tr><td>爱好：</td><td><%=student.getHobbies() %></td></tr>
<tr><td>简历：</td><td><%=student.getResume() %></td></tr>
</table>
</body>
</html>