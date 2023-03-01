package org.ddd.generic.example21;

public class Zoo2<T> {
	public T create(Class<T> clazz) throws Exception 
	{
		return clazz.newInstance();
	}
}
