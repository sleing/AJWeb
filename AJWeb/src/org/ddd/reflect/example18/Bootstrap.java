package org.ddd.reflect.example18;

import java.lang.reflect.Constructor;

public class Bootstrap {
	public static void main(String[] args) throws SecurityException, NoSuchMethodException {
		Class clazz = Person.class;
		Constructor[] constructors = clazz.getDeclaredConstructors();
		for(Constructor constructor : constructors){
			System.out.println(constructor.toString());
		}
	}
}




