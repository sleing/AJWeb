package org.ddd.generic.example6;

import java.util.Date;

public class GenericTest<T> {
	public static void  main(String[] args){
		
		Person<Integer> person = new Person<Integer>(5);
		System.out.println("person======\n"+person.toString());
		
		//实际的类型也可以不指定，编译器能自动推断出实际的类型
		Person<String> person1 = new Person<>("字符串");
		System.out.println("person1======\n"+person1);
		
		Teacher<String, Date> teacher = new Teacher<>("字符串");
		teacher.set("xcy",new Date());
		System.out.println("teacher======\n"+teacher.toString());
		
		//person = person1; //报类型不兼容错误
	}
}
