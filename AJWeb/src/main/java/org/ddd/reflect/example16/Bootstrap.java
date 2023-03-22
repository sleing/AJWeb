package org.ddd.reflect.example16;

import java.lang.reflect.Constructor;

public class Bootstrap {
	public static void main(String[] args) throws SecurityException, NoSuchMethodException {
		Class clazz = Person.class;
		Constructor[] constructors = clazz.getConstructors();
		for(Constructor constructor : constructors){
			System.out.println(constructor.toString());
		}
	}
}


