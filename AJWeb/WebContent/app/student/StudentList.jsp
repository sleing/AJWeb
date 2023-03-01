<%@page import="org.ddd.app.student.entity.Student"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
	List<Student> students = (List<Student>)request.getAttribute("students");
	Integer studentCount = (Integer)request.getAttribute("studentCount");
	Integer pageIndex = (Integer)request.getAttribute("pageIndex");
	Integer pageSize = 10;
	Long pageCount = Math.round(Math.ceil(studentCount*1.0f/pageSize));
%>   
<body>
<a href="StudentAdd"> add </a>
<table border=1> 
	<tr><td></td><td>id</td><td>name</td><td>age</td><td></td></tr>
	<% for(Student student:students) { %>
	<tr>
		<td><input type="checkbox" name="studentIds" value="<%=student.getId() %>"> </td>
		<td><%=student.getId() %></td>
		<td><%=student.getName() %></td>
		<td><%=student.getAge() %></td>
		<td> <a href="./StudentUpdateForm?id=<%=student.getId() %>"> edit </a>
		<a href="./StudentDelete?id=<%=student.getId() %>"> delete </a>
		<a href="./StudentView?id=<%=student.getId() %>"> view </a> </td>
	</tr>
	<%} %>
</table>
<a href="./StudentList?pageIndex=0">首页</a>  
<% if(pageIndex >0) { %>
 <a href="./StudentList?pageIndex=<%=pageIndex-1 %>">上一页</a>  
<%} %>
<% for(int i=0; i<pageCount;i++) {%>
<a href="./StudentList?pageIndex=<%=i %>"><%=i %></a>  

<%} %>
<% if(pageIndex < pageCount-1) { %>
 <a href="./StudentList?pageIndex=<%=pageIndex+1 %>"> 下一页</a>  
<%} %>
 
</body>
</html>