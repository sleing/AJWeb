package org.ddd.app.reflect;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClassLoader {

	static
	{
		System.out.println("this is static");
	}
	public static void main(String[] args) {
		System.out.println("this is main");
		ClassLoader classLoader = new ClassLoader();
		System.out.println(classLoader.getClass().getClassLoader());
		System.out.println(classLoader.getClass().getClassLoader().getParent());
		System.out.println(classLoader.getClass().getClassLoader().getParent().getParent());
//		String str = new String();
//		System.out.println(str.toString());

		
	}
}
