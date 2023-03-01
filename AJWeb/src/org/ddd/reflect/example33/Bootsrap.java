package org.ddd.reflect.example33;

import org.ddd.reflect.example31.Person;

public class Bootsrap {
	public static String className = "org.ddd.reflect.example31.Teacher";
	public static void main(String[] args){
		try {
			Class clazz = Class.forName(className);
			Person person = (Person) clazz.newInstance();
			System.out.println(person.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
