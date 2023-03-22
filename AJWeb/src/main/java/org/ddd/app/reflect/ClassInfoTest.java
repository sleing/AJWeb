package org.ddd.app.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

public class ClassInfoTest {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		//Class clazz = Person.class;
		Class clazz = Student.class;
		System.out.println(clazz.getName());
		System.out.println(clazz.getSimpleName());
		
		Method method = clazz.getMethod("setName", String.class);
		System.out.println(method.getName());
		System.out.println(method.getModifiers());
		System.out.println(method.getParameterTypes());
		
		for(Method method1 : clazz.getDeclaredMethods())
		{
			System.out.println(method1);
		}
		
		for(Field field:clazz.getDeclaredFields())
		{
			System.out.println(field.getModifiers() + " "
					+field.getType() +" "
					+ field.getName());
		}
		
		for(Constructor constructor:clazz.getConstructors())
		{
			System.out.println(constructor);
		}
		//select name,age from Student 
		StringBuilder SQLBuilder = new StringBuilder();
		SQLBuilder.append("select ");
		for(Field field:clazz.getDeclaredFields())
		{
			SQLBuilder.append(field.getName()).append(",");
		}
		SQLBuilder.deleteCharAt(SQLBuilder.length()-1);
		SQLBuilder.append(" from ");
		SQLBuilder.append(clazz.getSimpleName());
		System.out.println("SQL:  "+SQLBuilder.toString());
		
		Object obj = clazz.newInstance();
		System.out.println(obj);
		
		Constructor constructor =clazz.getConstructor(String.class, double.class, Date.class, List.class);
		//String no, double height, Date birthDay, List courses
		obj = constructor.newInstance("001",1.58,new Date(),null);
		System.out.println(obj);
		
		Field noField =  obj.getClass().getDeclaredField("no");
		noField.setAccessible(true);
		noField.set(obj, "002");
		System.out.println(obj);
		System.out.println(noField.get(obj));
		
		
		Method noSetMethod = obj.getClass().getMethod("setNo", String.class);
		noSetMethod.invoke(obj, "003");
		System.out.println(obj);
		
		
		
		
	}
	
	

}
