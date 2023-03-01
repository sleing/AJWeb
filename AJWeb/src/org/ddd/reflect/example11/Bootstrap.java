package org.ddd.reflect.example11;

public class Bootstrap {
	public static void main(String[] args) {
		Class<Person> clazz = Person.class;
		System.out.println(clazz.getCanonicalName());
	}
}



