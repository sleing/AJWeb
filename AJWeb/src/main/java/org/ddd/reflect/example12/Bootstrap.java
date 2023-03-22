package org.ddd.reflect.example12;

import java.util.Arrays;
import java.util.List;

public class Bootstrap {
	public static void main(String[] args) {
		List<Person> peoples = Arrays.asList(new Teacher(),new Student());
		for(Person people : peoples){
	if(people.getClass().equals(Teacher.class)){
				people.speak("I am a teacher!");
			}
		}
	}
}




