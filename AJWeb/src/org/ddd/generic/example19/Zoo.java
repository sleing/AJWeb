package org.ddd.generic.example19;

public class Zoo<T extends Animal> {
	private T t;
	public Zoo(T t){
		this.t = t;
	}
	public T pop(){
		return this.t;
	}
}
