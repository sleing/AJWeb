<%@page import="org.ddd.app.annotation.EntityInfoHelper"%>
<%@page import="org.ddd.app.annotation.ColumnInfo"%>
<%@page import="java.util.Iterator"%>
<%@page import="org.ddd.app.annotation.EntityInfo"%>
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
	List<Object> entities = (List<Object>)request.getAttribute("entities");
	EntityInfo entityInfo = (EntityInfo)request.getAttribute("entityInfo");
	String clazzName = (String)request.getAttribute("clazzName");
	
	Integer entitiesCount = (Integer)request.getAttribute("entitiesCount");
	Integer pageIndex = (Integer)request.getAttribute("pageIndex");
	Integer pageSize = 10;
	Long pageCount = Math.round(Math.ceil(entitiesCount*1.0f/pageSize));
	
	Iterator columnInfoIterator = entityInfo.getFieldColumnInfos().values().iterator();//迭代器
%>   
<body>
<form action="./DeleteMultipleServlet" method="post">
<a href="./GenericAddForm"> add </a>

<table border=1> 
	<tr><td></td> 
	<% while(columnInfoIterator.hasNext()) {
		ColumnInfo columnInfo = (ColumnInfo)columnInfoIterator.next();
	%>
	<td><%=columnInfo.getColumn().label() %></td>
	<% } %>

	<td></td>
	</tr>
	<% for(Object entity:entities) {
		Integer id = EntityInfoHelper.getEntityId(entity);
		columnInfoIterator = entityInfo.getFieldColumnInfos().values().iterator();
	%>
	<tr>
		<td><input type="checkbox" name="EntityIds" value="<%= id %>"> </td>
		
		<% while(columnInfoIterator.hasNext()) {
			ColumnInfo columnInfo = (ColumnInfo)columnInfoIterator.next();
		%>
		<td><%= EntityInfoHelper.getEntityFieldValue(columnInfo.getField(),entity) %></td>
		<% } %>		
		
		<td> <a href="./GenericUpdateForm?id=<%= id %>"> edit </a>
		<a href="./GenericDelete?id=<%=id %>"> delete </a>
		<a href="./GenericView"> view </a> </td>
	</tr>
	<%} %>
</table>
</form>
<a href="./GenericList?pageIndex=0&clazz=<%=clazzName %>">首页</a>  
<% if(pageIndex >0) { %>
 <a href="./GenericList?pageIndex=<%=pageIndex-1 %>&clazz=<%=clazzName %>">上一页</a>  
<%} %>
<% for(int i=0; i<pageCount;i++) {%>
<a href="./GenericList?pageIndex=<%=i %>&clazz=<%=clazzName %>"><%=i %></a>  

<%} %>
<% if(pageIndex < pageCount-1) { %>
 <a href="./GenericList?pageIndex=<%=pageIndex+1 %>&clazz=<%=clazzName %>"> 下一页</a>  
<%} %>
</body>
</html>