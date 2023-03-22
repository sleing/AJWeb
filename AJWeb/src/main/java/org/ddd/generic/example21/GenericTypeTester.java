package org.ddd.generic.example21;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class GenericTypeTester {

	public static void main(String[] args) {

		Zoo<ArrayList<HashMap<String,Bird>>> birdsZoo = new Zoo<ArrayList<HashMap<String,Bird>>>() {};
		
		TypeVariable[] birdsTypes = birdsZoo.getClass().getSuperclass().getTypeParameters();
		
		ParameterizedType type = (ParameterizedType)getSuperclassTypeParameter(birdsZoo.getClass());
	 
		System.out.println("birdsZoo的参数类型是：" + type.toString());
		ParameterizedType type1  = (ParameterizedType)type.getActualTypeArguments()[0];
		System.out.println("birdsZoo的参数类型是：" + type1);
		Type type21  = type1.getActualTypeArguments()[0];
		Type type22  = type1.getActualTypeArguments()[1];
		System.out.println("birdsZoo的参数类型是：" +type21.toString() );
		System.out.println("birdsZoo的参数类型是：" + type22.toString());
		Number a = 2;
		Number b = 3; 
		if(a>b)
		{
			String aa;
		}
		if(a instanceof Comparable)
		{
			System.out.println("OK");
		}
//		List<Integer> listA;
//		Collections.sort(listA);
	}
	 static Type getSuperclassTypeParameter(Class<?> subclass) {
		    Type superclass = subclass.getGenericSuperclass();
		    if (superclass instanceof Class) {
		      throw new RuntimeException("Missing type parameter.");
		    }
		    ParameterizedType parameterized = (ParameterizedType) superclass;
		    return  parameterized.getActualTypeArguments()[0];
		  }
}
