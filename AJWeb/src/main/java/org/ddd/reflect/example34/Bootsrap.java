package org.ddd.reflect.example34;

import java.lang.reflect.Constructor;

import org.ddd.reflect.example31.Person;

public class Bootsrap {
	public static String className = "org.ddd.reflect.example31.Teacher";
	public static void main(String[] args){
		try {
			Class clazz = Class.forName(className);
			Constructor constructor = clazz.getConstructor();
			Person person = (Person) constructor.newInstance();
			System.out.println(person.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
