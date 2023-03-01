package org.ddd.app.annotation;

import java.lang.reflect.Field;

public class EntityInfoHelper {

	public static EntityInfo getEntityInfo(Class clazz)
	{
		EntityInfo entityInfo = new EntityInfo();
		entityInfo.setClazz(clazz);
		entityInfo.setEntity((Entity)clazz.getAnnotation(Entity.class));
		
		for(Field field:clazz.getDeclaredFields())
		{
			Column column = field.getAnnotation(Column.class);
			if(column == null) continue;
			field.setAccessible(true);
			ColumnInfo columnInfo = new ColumnInfo();
			columnInfo.setField(field);
			columnInfo.setColumn(column);
			entityInfo.getColumnInfos().put(column.value(), columnInfo);
			entityInfo.getFieldColumnInfos().put(field.getName(), columnInfo);
			ID id  = field.getAnnotation(ID.class);
			if(id != null)
			{
				entityInfo.setIdColumnInfo(columnInfo);
			}
		}
		return entityInfo;
	}
	public static Integer  getEntityId(Object entity)
	{
		EntityInfo entityInfo = getEntityInfo(entity.getClass());
		try {
			Integer id = (Integer) entityInfo.getIdColumnInfo().getField().get(entity);
			return id;
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return -1;
		
	}
	public static Object getEntityFieldValue(Field field,Object entity)
	{
		try {
			Object value = field.get(entity);
			return value;
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static Class getClass(String clazzName)
	{
		try {
			return Class.forName(clazzName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
