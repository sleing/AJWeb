package org.ddd.app.student.service;

import java.util.List;

import org.ddd.app.student.entity.Student;

public interface StudentService {

	public List<Student> findAll();
	
	public List<Student> findStudentsByPage(Integer pageIndex,Integer pageSize);
	
	public Integer getStudentsCount();
	
	public Student findById(Integer id);
	
	public void add(Student student) throws IllegalAccessException;
	
	public void update(Student student);
	
	public void delete(Student student);
	
	public void delete(String id);	
}
