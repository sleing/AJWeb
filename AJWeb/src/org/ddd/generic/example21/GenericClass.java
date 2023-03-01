package org.ddd.generic.example21;

import java.lang.reflect.Constructor;

public class GenericClass {

	public static void main(String[] args) throws Exception, IllegalAccessException {

		Class<Bird> clazz = Bird.class;
		Bird bird = clazz.newInstance();
		
		Constructor<Bird> constructor = clazz.getConstructor();
		Bird  bird1 = constructor.newInstance();
		
	}

}
