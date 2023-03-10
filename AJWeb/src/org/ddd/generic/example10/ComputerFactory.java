package org.ddd.generic.example10;

public class ComputerFactory implements Factory<Computer> {

	@Override
	public Computer create() {
		System.out.println("装载主板！");
		System.out.println("装载CPU!");
		System.out.println("装载内存");
		return new Computer();
	}

}
