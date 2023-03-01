package org.ddd.app.student.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.ddd.app.student.entity.Student;

public class StudentMemoryDao implements StudentDao {

	private static Map<Integer,Student> Students = new HashMap<Integer, Student>();
	
	static {
		Students.put(1, new Student(1, "xcy1", 18));
		Students.put(2, new Student(2, "xcy2", 19));
		Students.put(3, new Student(3, "xcy3", 20));
		Students.put(4, new Student(4, "xcy4", 21));
		Students.put(5, new Student(5, "xcy5", 22));
	}
	
	@Override
	public List<Student> findAll() {
		List<Student> students = new ArrayList<Student>();
		
		Iterator<Student> iterator = Students.values().iterator();
		while(iterator.hasNext())
		{
			students.add(iterator.next());
		}
		
		return students;
	}

	@Override
	public Student findById(Integer id) {
		return Students.get(id);
	}

	@Override
	public void add(Student student) {
		student.setId(Students.size()+1);
		Students.put(student.getId(), student);

	}

	@Override
	public void update(Student student) {
		Students.put(student.getId(), student);
	}

	@Override
	public void delete(Student student) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(String id) {
		Students.remove(Integer.parseInt(id));
	}

	@Override
	public List<Student> findStudentsByPage(Integer pageIndex, Integer pageSize) {
		List<Student> studentList = new ArrayList<>();
		for (Student student:Students.values()) {
			studentList.add(student);
		}
		List<Student> studentList1 = new ArrayList<>();
		for (int i = pageIndex*pageSize;((pageIndex+1)*pageSize<studentList.size())? i <(pageIndex+1)*pageSize:i<studentList.size() ; i++) {
			studentList1.add(studentList.get(i));
		}
		return studentList1;
	}

	@Override
	public Integer getStudentsCount() {
	
		return Students.size();
	}

}
