package org.ddd.app.reflect;

//this is a test .
public class Test1 {

	private static String info = "this is a test. ";
	private static String name ="xu";
	public static void main(String[] args) {
		int a = 1;
		int b = 2;
		int c = a+b;
		int xu = c+1;
		System.out.println(info+c+name);
		System.out.println(Test1.class.getClassLoader());
		System.out.println(Test1.class.getClassLoader().getParent());
		System.out.println(Test1.class.getClassLoader().getParent().getParent());
	}

}
