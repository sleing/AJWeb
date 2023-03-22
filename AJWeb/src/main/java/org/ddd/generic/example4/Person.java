package org.ddd.generic.example4;

public class Person<T> {
	protected T t;
	public Person(T t){
		this.t = t;
	}
	public String toString(){
		return "变量t的类型是：" + t.getClass().getCanonicalName();
	}
} 
