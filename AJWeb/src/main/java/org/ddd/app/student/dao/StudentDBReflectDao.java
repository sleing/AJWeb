package org.ddd.app.student.dao;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.ddd.app.student.entity.Student;
import org.ddd.app.student.util.DBConnection;

public class StudentDBReflectDao implements StudentDao {

	@Override
	public List<Student> findAll() {
		//Class clazz = Student.class;
		Class clazz =null;
		try {
			clazz = Class.forName("org.cqut.ddd.student.entity.Student");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		List<Object> entities = DBReflectUtil.findAll(clazz);
		return (List)entities;
	}

	@Override
	public Student findById(Integer id) {
		return (Student) DBReflectUtil.findById(Student.class,id);
	}

	@Override
	public void add(Student student) throws IllegalAccessException {
		String sql = DBReflectUtil.generateInsertAllSQL(student);
		Connection connection = DBConnection.getConnection();
		try{
			Statement statement = connection.createStatement();
			statement.executeUpdate(sql);
			statement.close();
			connection.close();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

	@Override
	public void update(Student student) {
		DBReflectUtil.update(student);
	}

	@Override
	public void delete(Student student) {
		String sql = "delete from student where id=" +student.getId();
		//.....

	}

	@Override
	public void delete(String id) {
		Class clazz = null;
		try {
			clazz = Class.forName("org.cqut.ddd.student.entity.Student");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String sql = "delete from "+clazz.getSimpleName()+" where id = "+id;
		Connection connection = DBConnection.getConnection();
		try{
			Statement statement = connection.createStatement();
			statement.executeUpdate(sql);
			statement.close();
			connection.close();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

	}

	@Override
	public List<Student> findStudentsByPage(Integer pageIndex, Integer pageSize) {
		Class clazz = null;
		try {
			clazz = Class.forName("org.cqut.ddd.student.entity.Student");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String sql = DBReflectUtil.generateSelectAllSQL(clazz);
		sql +=  " limit "+pageIndex*pageSize+","+pageSize;
		Connection connection = DBConnection.getConnection();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);

			List<Object> entities = new ArrayList<Object>();
			while(resultSet.next())
			{
				Object obj = clazz.getDeclaredConstructor().newInstance();
				for(int i =1; i <= resultSet.getMetaData().getColumnCount();i++)
				{
					String columnName = resultSet.getMetaData().getColumnName(i);
					Field field =  obj.getClass().getDeclaredField(columnName);
					field.setAccessible(true);
					field.set(obj, resultSet.getObject(columnName));
				}
				entities.add(obj);
			}
			statement.close();
			connection.close();
			return (List)entities;

		} catch (SQLException | NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Integer getStudentsCount() {
		String sql = "select count(*)  from student ";
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
