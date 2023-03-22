package org.ddd.reflect.example39;

import java.lang.reflect.Proxy;
public class Bootsrap {
	public static void main(String[] args){
		Person person = new Person();
		Speakable speakable = (Speakable)Proxy.newProxyInstance(
					Speakable.class.getClassLoader(),
					new Class[]{Speakable.class}, new LogHandler(person));
		speakable.speak("Lesson one!");
		System.out.println(speakable.getClass().toGenericString());
	}
}


