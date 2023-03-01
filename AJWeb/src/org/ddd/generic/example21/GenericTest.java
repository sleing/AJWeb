package org.ddd.generic.example21;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import java.util.List;


public class GenericTest {
	public static void  main(String[] args) throws Exception{
		Zoo<Fish> fishZoo = new Zoo<Fish>();
		Zoo<Bird > birdZoo = new Zoo<Bird>();
		Zoo<? extends Animal> animalZoo = new Zoo<Animal>();
		Person person = new Person();
		
		TypeVariable[] fishTypes = fishZoo.getClass().getTypeParameters();
		TypeVariable[] birdTypes = fishZoo.getClass().getTypeParameters();
		TypeVariable[] animalTypes = animalZoo.getClass().getTypeParameters();
		TypeVariable[] personTypes = person.getClass().getTypeParameters();
	
		System.out.println("fishZoo的参数类型是：" + Arrays.toString(fishTypes)+ ", 上界为："+	fishTypes[0].getBounds()[0]);
		System.out.println("birdZoo的参数类型是：" + Arrays.toString(birdTypes)+ ", 上界为："+	birdTypes[0].getBounds()[0]);
		System.out.println("animalZoo的参数类型是：" + Arrays.toString(animalTypes)+ ", 上界为："+	animalTypes[0].getBounds()[0]);
		System.out.println("person的参数类型是：" + Arrays.toString(personTypes));
	}
}
