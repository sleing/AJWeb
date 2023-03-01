package org.ddd.app.generic.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.ddd.app.annotation.ID;

@Entity(name="teacher")
public class Teacher  {
	
	@ID
	@Column(name="id",nullable=false)
	private Integer id;
	@Column(name="name",nullable=false)
	private String name ;
	@Column(name="age")
	private Integer age;
	@Column(name="salary")
	private double salary;
	
	public Teacher() {
		super();
	}

	public Teacher(Integer id, String name, Integer age) {
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
	
	public double getSalary() {
		return salary;
	}

	public void setSalary(Float salary) {
		this.salary = salary;
	} 	
}
 


 
