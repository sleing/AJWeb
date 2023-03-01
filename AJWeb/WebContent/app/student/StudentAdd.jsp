<%--
  Created by IntelliJ IDEA.
  User: 12269
  Date: 2021/3/11
  Time: 20:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>StudentAddForm</title>
</head>
<body>
	<form action="../StudentSave1" method="post">
		<table>
			<tr>
				<td>
					<h1>new student</h1>
				</td>
			</tr>
			<tr>
				<td>name:</td>
				<td><input type="text" name="name" /></td>
			</tr>
			<tr>
				<td>age:</td>
				<td><input type="number" name="age" /></td>
			</tr>
			<tr>
				<td>gender:</td>
				<td><input type="text" name="gender" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="save" /></td>
			</tr>
		</table>
	</form>
</body>
</html>
