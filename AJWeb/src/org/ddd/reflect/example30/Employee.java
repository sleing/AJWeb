package org.ddd.reflect.example30;

public abstract class Employee {
	protected int salary = 0;
	public void addSalary(int amount){
		this.salary += amount;
	}
	public abstract String toString();
}
