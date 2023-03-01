package org.ddd.generic.example15;

public class Factory<T extends Speakable&Flyable> {
	public T create(Class<T> t) throws Exception{
		return t.newInstance();
	} 
}
