package org.ddd.reflect.example6;

public class Bootstrap {
	static{
		System.out.println("Bootstrap prepare!");
	}
	public static void main(String[] args) {
		ClassLoader loader = Bootstrap.class.getClassLoader();
		System.out.println(loader);
		System.out.println(loader.getParent());
		System.out.println(loader.getParent().getParent());
	}
}

