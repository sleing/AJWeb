package org.ddd.generic.example14;

import java.util.Date;

import org.ddd.section3.example3_13.Car;

public class GenericTest<T> {
	public static void  main(String[] args) throws Exception{
		Sortor sortor = new Sortor();
		System.out.println("最大整数是： " + sortor.getMax(3, 5));
		System.out.println("最大小数是： " + sortor.getMax(3.5D, 5.5D));
		System.out.println("最大的车是： " + sortor.getMax("ABC", "AFC"));
		
		//下面的语句报语法错误，因为 类Car 没有实现Comparable接口
		//System.out.println("最大小字符串是： " + sortor.getMax(new Car(), new Car()));
	}
}
