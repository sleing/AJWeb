package org.ddd.app.student.entity;

public class Student {

	private Integer id;
	private String name ;
	private Integer age;
	
 
	
	public Student() {
		super();
	}

	public Student(Integer id, String name, Integer age) {
		super();
		this.id = null;
		
		this.id = id;
		this.name = name;
		this.age = age;
	}

	public Integer getId() {
		return id;
	}
   
	public void setId(Integer id) {
//		if(id == null)
//		{
//			throw new Exception("id can't be null");
//		}
		this.id = id;

	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	} 
	
	
}
