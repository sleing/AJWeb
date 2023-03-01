package org.ddd.generic.example4;

import java.util.Date;


public class GenericTest<T> {
	public static void  main(String[] args){
		Teacher<String,Date> teacher = new Teacher<String,Date>(5);
		System.out.println(teacher.toString());
		teacher.set("set", new Date());
		System.out.println(teacher.toString());
	}
}
 