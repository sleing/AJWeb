package org.ddd.annotation.orm1;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.ddd.app.generic.entity.Student;

public class AnnotationTest {

	public static void main(String[] args) throws NoSuchFieldException, SecurityException {

		Class studentClazz  = Student.class;
		
		Annotation annotation = studentClazz.getAnnotation(Entity.class);
		Entity entity = (Entity) annotation;

		System.out.println(entity.description());

//		Field field = studentClazz.getDeclaredField("id");
//		ID id = field.getAnnotation(ID.class);
//		System.out.println(id.value());
		
		EntityInfo entityInfo = EntityInfoHelper.getEntityInfo(studentClazz);
		
		System.out.println(entityInfo);
	}

}
