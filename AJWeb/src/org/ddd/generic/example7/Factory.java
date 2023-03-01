package org.ddd.generic.example7;

public class Factory {
	public <T> T generator(Class<T> t) throws Exception{
		return t.newInstance();
	}
}
