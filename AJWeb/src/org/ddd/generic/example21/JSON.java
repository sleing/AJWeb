package org.ddd.generic.example21;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class JSON {
 
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException {

		//当new这个类的时候，实际上是创建了一个匿名内部类。TypeReference的子类，泛型参数限定为Object。 
		TypeReference<ArrayList<Bird>> typeReference = new TypeReference<ArrayList<Bird>>(){};
		
		Object object = parseObject("[{name:'翠鸟'},{name:'海鸥'},{name:'啄木鸟'}]", typeReference);
		
	}	 
	// 
	public static Object parseObject(String json, TypeReference<?> typeReference) throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException
	{
	   Type superclass = typeReference.getType();
 
	    ParameterizedType parameterizedType = (ParameterizedType) superclass;
	    
		Object object = null;
		Class type = (Class)parameterizedType.getRawType();
		 
		if(type == ArrayList.class)
		{
			ArrayList objects = (ArrayList)type.newInstance();
			Class type1 =  (Class)(parameterizedType.getActualTypeArguments()[0]);
			json = json.trim();
			json = json.substring(1, json.length()-2);
			String[] items = json.split(",");
			
			for(String item: items)
			{
				Object object2 = type1.newInstance();
				item = item.trim();
				item = item.substring(1, item.length()-2);
				String[] items1 = item.split(",");
				for(String item1:items1)
				{
					String[] nameValue = item1.split(":");
					Field field = type1.getDeclaredField(nameValue[0].trim());
					field.setAccessible(true);
					field.set(object2, nameValue[1]);
				}
				objects.add(object2);
			}
			
			object =objects; 
		}
		
		return object;
	}
}
