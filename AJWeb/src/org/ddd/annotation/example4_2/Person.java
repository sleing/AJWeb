package org.ddd.annotation.example4_2;

public class Person {
	@Deprecated
	public void speak(String message){}
	
	@Override
	public String toString(){
		return "This is a person!";
	}
}
