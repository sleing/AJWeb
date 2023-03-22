package org.ddd.generic.example21;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class GenericTypeRetain {
	public static void main(String[] args) {
		ArrayList<HashMap<String,Bird>> birds = new ArrayList<HashMap<String,Bird>>() {};
		ParameterizedType type = getSuperclassTypeParameter(birds.getClass());
	 
		System.out.println("birds的类型是：" + birds.getClass().toString());
		System.out.println("birds的参数类型是：" + type.toString());
		ParameterizedType type1  = (ParameterizedType)type.getActualTypeArguments()[0];
		System.out.println("birds的类型参数的参数类型是：" + type1);
		Type type21  = type1.getActualTypeArguments()[0];
		Type type22  = type1.getActualTypeArguments()[1];
		System.out.println("birds的类型参数的参数类型是：" +type21.toString() );
		System.out.println("birds的类型参数的参数类型是：" + type22.toString());

	}
	 static ParameterizedType getSuperclassTypeParameter(Class<?> subclass) {
		    Type superclass = subclass.getGenericSuperclass();
		    if (superclass instanceof ParameterizedType) {
			    ParameterizedType parameterized = (ParameterizedType) superclass;
			    return  parameterized;
		    }
		    else
		    {
		    	throw new RuntimeException("不是参数化的类型");
		    }
		  }
}
