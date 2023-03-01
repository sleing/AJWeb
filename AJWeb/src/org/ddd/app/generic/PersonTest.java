package org.ddd.app.generic;

public class PersonTest {

	public static void main(String[] args) {

		
		@SuppressWarnings({ "unused", "rawtypes" })
		Person person1;
		
		Person<String> person = new Person<String>("xcy");
		System.out.println("start"+person.toString());
	}

}
