package org.ddd.reflect.example5;
public class JavaPTester {
	public static void main(String[] args) {
		int a = 2;
		int b = 3;
		int c = a+b;
		if(a>b)
		{
			c = c*2;
		}
		else
		{
			c=c/2;
		}
		System.out.println("c="+c);
	}
}
