package org.ddd.generic.example20;

public class Zoo<T extends Speakable&Flyable> {
	private T t;
	public Zoo(T t){
		this.t = t;
	}
	public T pop(){
		return this.t;
	}
}
