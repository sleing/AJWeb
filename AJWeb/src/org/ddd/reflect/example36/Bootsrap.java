package org.ddd.reflect.example36;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class Bootsrap {
	public static String className = "org.ddd.reflect.example31.Teacher";
	public static void main(String[] args){
		try {
			Class clazz = Class.forName(className);
			Constructor constructor = clazz.getConstructor();
			Object teacher = constructor.newInstance();
			Field field = clazz.getField("position");
			System.out.println(teacher.toString());
			field.set(teacher, "Master");
			System.out.println(teacher.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
