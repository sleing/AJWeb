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
<form>
    <table>
        <tr><td>id</td><td><input type="text" name="id" readonly="readonly"  value="<%=student.getId() %>" /> </td></tr>
        <tr><td>name</td><td><input type="text" name="name" readonly="readonly" value="<%=student.getName() %>" /> </td></tr>
        <tr><td>age</td><td><input type="number" name="age" readonly="readonly" value="<%=student.getAge() %>" /> </td></tr>
    </table>
</form>
</body>
</html>