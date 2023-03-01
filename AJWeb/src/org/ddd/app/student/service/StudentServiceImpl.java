package org.ddd.app.student.service;

import java.util.List;

import org.ddd.app.student.dao.StudentDao;
import org.ddd.app.student.dao.StudentMemoryDao;
import org.ddd.app.student.entity.Student;
import org.ddd.app.student.factory.DaoFactory;

public class StudentServiceImpl implements StudentService {

	@Override
	public List<Student> findAll() {
		StudentDao dao = DaoFactory.getInstance().getStudentDao();
		return dao.findAll();
	}

	@Override
	public Student findById(Integer id) {
		StudentDao dao = DaoFactory.getInstance().getStudentDao();
		return dao.findById(id);
	}

	@Override
	public void add(Student student) throws IllegalAccessException {
		StudentDao dao = DaoFactory.getInstance().getStudentDao();
		Student student2 = dao.findById(student.getId());
		if(student2 == null)
		{
			dao.add(student);
		}
		else
		{
			throw new IllegalArgumentException("student  has been existing");
		}
	}

	@Override
	public void update(Student student) {
		StudentDao dao = DaoFactory.getInstance().getStudentDao();
		Student student2 = dao.findById(student.getId());
		if(student2 == null)
		{
			throw new IllegalArgumentException("student  hasn't been existing");
		}
		else if(! student2.getId().equals(student.getId()))
		{
			throw new IllegalArgumentException("student's id can not be modified");
		}			
		else
		{
			dao.update(student);
		}
	}

	@Override
	public void delete(Student student) {

	}

	@Override
	public void delete(String id) {
		DaoFactory.getInstance().getStudentDao().delete(id); 
	}

	@Override
	public List<Student> findStudentsByPage(Integer pageIndex, Integer pageSize) {
		return DaoFactory.getInstance().getStudentDao().findStudentsByPage(pageIndex,pageSize);
	}

	@Override
	public Integer getStudentsCount() {
		return DaoFactory.getInstance().getStudentDao().getStudentsCount();
	}

}
