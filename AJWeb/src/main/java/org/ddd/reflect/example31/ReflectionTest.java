/**
* @Title: ReflectionTest.java
* @Package org.ddd.section2.example2_29
* @Description: TODO(用一句话描述该文件做什么)
* @author matao@cqrainbowsoft.com
* @date 2018年5月31日 上午9:18:58
* @version V1.0
*/
package org.ddd.reflect.example31;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;

public class ReflectionTest
{
	public static void main(String[] args) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchFieldException
	{
		List ddd ;
		Class teacherClass = Teacher.class;
		System.out.println(teacherClass.getName());
//		for(Field field:teacherClass.getFields())
//		{
//			System.out.println(field.getName());
//		}
		
//		for(Method method:teacherClass.getMethods())
//		{
//			System.out.println(method.getName());
//		}
		
//		Teacher teacher = new Teacher();
//		Method speakMethod = teacherClass.getMethod("speak", java.lang.String.class);
//		invoke(teacher,speakMethod, "all are extremely excellent student");

		Teacher teacher = new Teacher();
		 
		Field field = teacherClass.getDeclaredField("salary");
		field.setAccessible(true);
		System.out.println(Modifier.toString(field.getModifiers()));

		Object value= field.get(teacher);
		System.out.println(value);

	}
	private static void invoke(Object object,Method method,Object...args)
	{
		try
		{
			method.invoke(object, args);
		} catch (IllegalArgumentException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
