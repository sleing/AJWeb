package org.ddd.app.student.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.ddd.app.student.entity.Student;
import org.ddd.app.student.util.DBConnection;

public class StudentDBDao implements StudentDao {

	@Override
	public List<Student> findAll() {
	
		String sql = "select id,name,age from student";
		Connection connection = DBConnection.getConnection();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			
			List<Student> students = new ArrayList<Student>();
			while(resultSet.next())
			{
				Student student = new Student(resultSet.getInt("id"), 
						resultSet.getString("name"), 
						resultSet.getInt("age"));
				students.add(student);
			}
			statement.close();
			connection.close();
			return students;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Student findById(Integer id) {

		String sql = "select id,name,age from student where id="+id;
		System.out.println(sql);
		Connection connection = DBConnection.getConnection();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			Student student=null;
			while(resultSet.next())
			{
				student = new Student(resultSet.getInt("id"),
						resultSet.getString("name"),
						resultSet.getInt("age"));
			}
			statement.close();
			connection.close();
			return student;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void add(Student student) {
		String sql = "insert into student (name,age) values ('"+student.getName()+"',"+student.getAge()+")";
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
		String sql = "update student set name='"+student.getName()+"',age="+student.getAge()+" where id="+student.getId();
		System.out.println(sql);
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
	public void delete(Student student) {
		String sql = "delete from student where id=" +student.getId();
		//.....

	}

	@Override
	public void delete(String id) {
		String sql = "delete from student where id=" +id;
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
		String sql = "select id,name,age from student limit "+pageIndex*pageSize+","+pageSize;
		Connection connection = DBConnection.getConnection();
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			List<Student> students = new ArrayList<Student>();
			while(resultSet.next())
			{
				Student student = new Student(resultSet.getInt("id"), 
						resultSet.getString("name"), 
						resultSet.getInt("age"));
				students.add(student);
			}
			statement.close();
			connection.close();
			return students;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
