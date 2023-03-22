package org.ddd.generic.example18;


public class GenericTest {
	public static void  main(String[] args) throws Exception{
		
		Zoo<Fish> fishZoo = new Zoo<Fish>(new Fish());
		Zoo<Bird> birdZoo = new Zoo<Bird>(new Bird());
		
		System.out.println("Zoo<Fish>的类型为："+fishZoo.getClass());
		System.out.println("Zoo<Bird>的类型为："+birdZoo.getClass());
		
		boolean isSampleClass = fishZoo.getClass().equals(birdZoo.getClass());
		System.out.println("两者的类型相同：" + isSampleClass);
	}
} 
