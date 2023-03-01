package org.ddd.reflect.example8;

public class FinalTester {

	public static void main(String[] args) {
		FinalTester finalTester = new FinalTester();
		long start = System.currentTimeMillis();
		for(long i=0;i<10000000000L;i++)
		{
			finalTester.test1(i);
		}
		System.out.println(System.currentTimeMillis()-start);
		start = System.currentTimeMillis();
		for(long i=0;i<10000000000L;i++)
		{
			finalTester.test2(i);
		}
		System.out.println(System.currentTimeMillis()-start);
	}
	
	public  final long test1(long i)
	{
		return i;
	}
	public   long test2(long i)
	{
		return i;
	}
}
