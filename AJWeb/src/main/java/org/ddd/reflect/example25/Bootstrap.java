package org.ddd.reflect.example25;

import java.lang.reflect.Field;

public class Bootstrap {
	public static void main(String[] args) throws NoSuchFieldException {
		Class clazz = Person.class;
		Field[] fields = clazz.getFields();
		for(Field field : fields){
			System.out.println(field.toString());
		}
	}
}


