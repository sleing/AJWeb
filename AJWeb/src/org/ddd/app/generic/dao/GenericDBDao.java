package org.ddd.app.generic.dao;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.ddd.app.student.dao.DBReflectUtil;
import org.ddd.app.student.entity.Student;
import org.ddd.app.student.util.DBConnection;

public class GenericDBDao implements GenericDao {

	@Override
	public List<Object> findAll(Class clazz) {

		List<Object> entities = DBReflectUtil.findAll(clazz);
		
		return (List)entities;
	}

	@Override
	public Object findById(Class clazz,Integer id) {
		return DBReflectUtil.findById(clazz, id);
	}

	@Override
	public void add(Object entity) {
		//sql = "insert into student"
	}

	@Override
	public void update(Object entity) {
		//sql ="update student set id=''"
		// connection.prepareStatement();
	}

	@Override
	public void delete(Object entity) {
		//String sql = "delete from student where id=" +student.getId();
		//.....

	}

	@Override
	public void delete(Class clazz,String id) {
		String sql = "delete from student where id=" +id;
		//.....

	}

	@Override
	public List<Object> findEntitiesByPage(Class clazz,Integer pageIndex, Integer pageSize) {
		 return DBReflectUtil.findTeachersByPage(clazz, pageIndex, pageSize);
	}

	@Override
	public Integer getEntitiesCount(Class clazz) {
		return DBReflectUtil.getCount(clazz);
	}

}
