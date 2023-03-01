package org.ddd.app.reflect;

import java.util.Date;
import java.util.List;

import org.ddd.app.reflect.Person;

public class Student extends Person {

	private String no;
	private double height;
	private Date birthDay;
	private List courses;
	
	
	public Student() {
		super();
	}


	public Student(String no, double height, Date birthDay, List courses) {
		super();
		this.no = no;
		this.height = height;
		this.birthDay = birthDay;
		this.courses = courses;
	}


	public String getNo() {
		return no;
	}


	public void setNo(String no) {
		this.no = no;
	}


	public double getHeight() {
		return height;
	}


	public void setHeight(double height) {
		this.height = height;
	}


	public Date getBirthDay() {
		return birthDay;
	}


	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}


	public List getCourses() {
		return courses;
	}


	public void setCourses(List courses) {
		this.courses = courses;
	}


	@Override
	public String toString() {
		return "Student [no=" + no + ", height=" + height + ", birthDay=" + birthDay + ", courses=" + courses + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((birthDay == null) ? 0 : birthDay.hashCode());
		result = prime * result + ((courses == null) ? 0 : courses.hashCode());
		long temp;
		temp = Double.doubleToLongBits(height);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((no == null) ? 0 : no.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (birthDay == null) {
			if (other.birthDay != null)
				return false;
		} else if (!birthDay.equals(other.birthDay))
			return false;
		if (courses == null) {
			if (other.courses != null)
				return false;
		} else if (!courses.equals(other.courses))
			return false;
		if (Double.doubleToLongBits(height) != Double.doubleToLongBits(other.height))
			return false;
		if (no == null) {
			if (other.no != null)
				return false;
		} else if (!no.equals(other.no))
			return false;
		return true;
	}
	
	
}
