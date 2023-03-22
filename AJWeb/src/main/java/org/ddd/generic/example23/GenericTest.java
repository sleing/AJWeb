package org.ddd.generic.example23;



public class GenericTest {
	public static void main(String[] args) {
		Bird bird = new Bird();
		Animal<String> animal = bird;
		animal.get();
	}
}

