package org.ddd.generic.example8;

public class Factory {
	public <T> T generator(Class<T> t) throws Exception{
		return t.newInstance();
	}
}
