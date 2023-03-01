package org.ddd.app.generic;

public class Person<T> {
	private Object t;
	public Person(T t){
		this.t = t;
	}
	
	@Override
	public String toString(){
		return "参数的类型是：" + t.getClass().getCanonicalName();
	}
}