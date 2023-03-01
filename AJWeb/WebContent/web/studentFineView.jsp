<!--studentFineView.jsp  -->
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
String name = "徐洋洋";
Integer age = 14;
String gender = "女";
String school = "外语学校";
List<String> hobbies = new ArrayList();
hobbies.add("篮球");
hobbies.add("兵乓球");
String resume = "她是一个好学生";
%>
<body>
	<h1>新增的学生信息</h1>
	<table>
		<tr><td>姓名：</td><td><%=name%></td></tr>
		<tr>
			<td>年龄：</td>
			<%
				if (age < 18) {
			%>
			<td><p style="color: red">
					未成年(<%=age%>岁)
				</p></td>
			<%
				} else {
			%>
			<td><%=age%></td>
			<%
				}
			%>
		</tr>
		<tr><td>性别：</td><td><%=gender%></td></tr>
		<tr><td>学院：</td><td><%=school%></td></tr>
		<tr>
			<td>爱好：</td>
			<td>
			<ul>
			<%
				for (int i = 0; i < hobbies.size(); i++) {
			%>
			<li><%=i%> : <%=hobbies.get(i)%></li>
			<%
				}
			%>
			</ul>
			</td>
		</tr>
		<tr>
			<td>简历：</td>
			<td>
				<%
				int fontSize = 3;
				while (fontSize <= 6) {
				%> 
				<font color="green" size="<%=fontSize%>">
					<%=resume%>
			   </font><br /> 
			   <%	fontSize++;		 %> 
			   <%}		 %>

			</td>
		</tr>
	</table>
</body>
</html>