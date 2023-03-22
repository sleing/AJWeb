package org.ddd.app.generic.entity;
import org.ddd.app.annotation.Column;
import org.ddd.app.annotation.Entity;
import org.ddd.app.annotation.ID;

@Entity(value="student")
public class Student  {

	@ID
	@Column(value="id",nullable=false,label = "id")
	private Integer id;
	@Column(value="name",nullable=false,maxLength=10,label = "name")
	private String name ;
	@Column(value="age",label = "age")
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
 


 
