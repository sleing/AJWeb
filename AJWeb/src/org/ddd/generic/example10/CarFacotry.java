package org.ddd.generic.example10;

public class CarFacotry implements Factory<Car> {

	@Override
	public Car create() {
		System.out.println("装载发动机！");
		System.out.println("装载座椅！");
		System.out.println("装载轮子！");
		return new Car();
	}

}
