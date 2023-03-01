package org.ddd.generic.example21;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class TypeReference<T> {
	private Type type ;
	protected TypeReference() {
		// 获取当前对象的直接超类的 Type
		Type superClass = getClass().getGenericSuperclass();
		this.type = ((ParameterizedType) superClass).getActualTypeArguments()[0];
	}
	public Type getType()
	{
		return this.type;
	}
	
}