package org.ddd.app.student.dao;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.ddd.app.student.util.DBConnection;

public class DBReflectUtil {
	public static final String idFieldName = "id";
	public static String generateSelectAllSQL(Class clazz)
	{
		StringBuilder SQLBuilder = new StringBuilder();
		SQLBuilder.append("select ");
		for(Field field:clazz.getDeclaredFields())
		{
			SQLBuilder.append(field.getName()).append(",");
		}
		SQLBuilder.deleteCharAt(SQLBuilder.length()-1);
		SQLBuilder.append(" from ");
		SQLBuilder.append(clazz.getSimpleName());
		SQLBuilder.append(" order by id");
		System.out.println("SQL:  "+SQLBuilder.toString());	
		return SQLBuilder.toString();
	}
	public static  List<Object> findAll(Class clazz) {

		String sql = DBReflectUtil.generateSelectAllSQL(clazz);
		Connection connection = DBConnection.getConnection();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			
			List<Object> entities = new ArrayList<Object>();
			while(resultSet.next())
			{
				Object obj = clazz.newInstance();
				for(int i =1; i <= resultSet.getMetaData().getColumnCount();i++)
				{
					String columnName = resultSet.getMetaData().getColumnName(i);
					Field field =  obj.getClass().getDeclaredField(columnName);
					field.setAccessible(true);
					field.set(obj, resultSet.getObject(columnName));
//					Method noSetMethod = obj.getClass().getMethod("setNo", String.class);
//					noSetMethod.invoke(obj, "003");
//					System.out.println(obj);
				}
				entities.add(obj);
			}
			statement.close();
			connection.close();
			return (List)entities;
			
		} catch (SQLException | NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException | InstantiationException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	/**
	 * 方法的参数一定只能是Object,不能传入 Teacher,因为这个工具类似所有的实体都必须适用
	 */
	public static String generateInsertAllSQL(Object obj) throws IllegalArgumentException, IllegalAccessException
	{
		Class clazz = obj.getClass();
		StringBuilder SQLBuilder = new StringBuilder();
		SQLBuilder.append("insert  into ");
		SQLBuilder.append(clazz.getSimpleName());
		SQLBuilder.append(" (");
		for(Field field:clazz.getDeclaredFields())
		{
			SQLBuilder.append(field.getName()).append(",");
		}
		SQLBuilder.deleteCharAt(SQLBuilder.length()-1);
		SQLBuilder.append(" ) values ( ");
		for(Field field:clazz.getDeclaredFields())
		{
			field.setAccessible(true);
			Object value = field.get(obj);
			if( value instanceof String) //这里判读数据类型，字符串，日期等特殊类型需要处理
			{
				SQLBuilder.append("'") .append(value).append("',");
			}
			else
			{
				SQLBuilder.append(value).append(",");
			}
		}
		SQLBuilder.deleteCharAt(SQLBuilder.length()-1);
		SQLBuilder.append(" ) ");
		return SQLBuilder.toString();
	}
	public static Object findById(Class clazz,Integer id) 
	{
		//String sql = "select * from teacher where id="+ id;
		String sql = "select * from "+clazz.getSimpleName() +" where id="+ id;
		
		Connection connection = DBConnection.getConnection();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			Object obj = null;
			if(resultSet.next())
			{
				obj = clazz.newInstance();	
				for(int i =1; i <= resultSet.getMetaData().getColumnCount();i++)
				{
					String columnName = resultSet.getMetaData().getColumnName(i);
					Field field =  obj.getClass().getDeclaredField(columnName);
					field.setAccessible(true);
					field.set(obj, resultSet.getObject(columnName));
//					Method noSetMethod = obj.getClass().getMethod("setNo", String.class);
//					noSetMethod.invoke(obj, "003");
//					System.out.println(obj);
				}
			}
			statement.close();
			connection.close();
			return obj;
		} catch (SQLException | NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException | InstantiationException e) {
			e.printStackTrace();
		}	
		return null;
	}
	public static void add(Object obj) {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into "+obj.getClass().getSimpleName()+"(") ;
		//id,name,age) values(?,?,?)";
		for(Field field:obj.getClass().getDeclaredFields())
		{
			sql.append(field.getName()).append(",");
		}
		sql.deleteCharAt(sql.length()-1);
		sql.append(") values(");
		for(Field field:obj.getClass().getDeclaredFields())
		{
			field.setAccessible(true);
			try {
				if(field.get(obj) instanceof String)
				{
					sql.append("'").append(field.get(obj) ).append("'").append(",");
				}
				else
				{
					sql.append(field.get(obj) ).append(",");
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		sql.deleteCharAt(sql.length()-1);
		sql.append(") ");
		System.out.println(sql.toString());
		
	
		
		Connection connection = DBConnection.getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.execute(sql.toString());
					
			statement.close();
			connection.close();
			return ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
	}
	public static void prepareAdd(Object obj) {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into "+obj.getClass().getSimpleName()+"(") ;
		//id,name,age) values(?,?,?)";
		for(Field field:obj.getClass().getDeclaredFields())
		{
			sql.append(field.getName()).append(",");
		}
		sql.deleteCharAt(sql.length()-1);
		sql.append(") values(");
		for(Field field:obj.getClass().getDeclaredFields())
		{
			sql.append("?");
		}
		sql.deleteCharAt(sql.length()-1);
		sql.append(") ");
		System.out.println(sql.toString());
	
		Connection connection = DBConnection.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(sql.toString());
			
			int fieldIndex = 0;
			for(Field field:obj.getClass().getDeclaredFields())
			{
				field.setAccessible(true);
				Object value = field.get(obj);
				if(value instanceof String)
				{
					statement.setString(fieldIndex+1,(String)value);
				}
				else  if(value instanceof Integer)
				{
					statement.setInt(fieldIndex+1,(Integer)value);
				}
				else
				{
					//double ,boolean,long
					throw new RuntimeException("type is error");
				}
				fieldIndex++;
			}
			
			statement.execute();
			
			statement.close();
			connection.close();
			return ;
		} catch (SQLException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	 
	}
	public static void update(Object obj) {
		StringBuilder sql = new StringBuilder();
		sql.append("update "+obj.getClass().getSimpleName()+" set ");
		//String sql ="update "+obj.getClass().getSimpleName()+" set ";
		
		// name=?,age=? where id=?";
		
		for(Field field:obj.getClass().getDeclaredFields())
		{
			sql.append(field.getName()).append("=?,");
		}
		sql.deleteCharAt(sql.length()-1);		
		sql.append(" where id=?");
		
		Connection connection = DBConnection.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(sql.toString());
			
			int fieldIndex = 1;
			for(Field field:obj.getClass().getDeclaredFields())
			{
				field.setAccessible(true);
				Object value = field.get(obj);
				if(value instanceof String)
				{
					statement.setString(fieldIndex,(String)value);
				}
				else  if(value instanceof Integer)
				{
					statement.setInt(fieldIndex,(Integer)value);
				}
				else
				{
					//double ,boolean,long
					throw new RuntimeException("type is error");
				}
				fieldIndex++;
			}
			
			Field idField = obj.getClass().getDeclaredField(idFieldName);
			idField.setAccessible(true);
			Object idValue = idField.get(obj);
			statement.setInt(fieldIndex,(Integer) idValue);
			
			statement.execute();
			statement.close();
			connection.close();
		 
			return ;
		} catch (SQLException | NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			 
	}	

	public static void delete(Class clazz,Integer id) {
		String sql ="delete from  "+clazz.getSimpleName()+" where id=?";
		Connection connection = DBConnection.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setInt(1, id);
			statement.execute();
			
			statement.close();
			connection.close();
			return ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	public static void delete(Object obj) {
		Field idField;
		try {
			idField = obj.getClass().getDeclaredField(idFieldName);
			Object idValue = idField.get(obj);

			delete(obj.getClass(),(Integer)idValue);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}	
	public static List<Object> findTeachersByPage(Class clazz,Integer pageIndex, Integer pageSize) {

		String sql = DBReflectUtil.generateSelectAllSQL(clazz);
		sql +=  " limit "+pageIndex*pageSize+","+pageSize;
		
		Connection connection = DBConnection.getConnection();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			
			List<Object> entities = new ArrayList<Object>();
			while(resultSet.next())
			{
				Object obj = clazz.newInstance();
				for(int i =1; i <= resultSet.getMetaData().getColumnCount();i++)
				{
					String columnName = resultSet.getMetaData().getColumnName(i);
					Field field =  obj.getClass().getDeclaredField(columnName);
					field.setAccessible(true);
					field.set(obj, resultSet.getObject(columnName));
//					Method noSetMethod = obj.getClass().getMethod("setNo", String.class);
//					noSetMethod.invoke(obj, "003");
//					System.out.println(obj);
				}
				entities.add(obj);
			}
			statement.close();
			connection.close();
			return (List)entities;
			
		} catch (SQLException | NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException | InstantiationException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public static Integer getCount(Class clazz) {
		String sql = "select count(*)  from  "+clazz.getSimpleName();
		Connection connection = DBConnection.getConnection();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			resultSet.next();
			
			Integer count = resultSet.getInt(1);
			 
			statement.close();
			connection.close();
			return count;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
}
