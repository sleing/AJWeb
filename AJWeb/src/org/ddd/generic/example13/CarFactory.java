package org.ddd.generic.example13;

public class CarFactory<T extends Car>{

	public T create(Class<T> clazz) throws Exception {
		System.out.println("装载发动机！");
		System.out.println("装载座椅！");
		System.out.println("装载轮子！");
		return clazz.newInstance();
	}
}
