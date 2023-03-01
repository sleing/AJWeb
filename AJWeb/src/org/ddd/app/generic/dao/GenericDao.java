package org.ddd.app.generic.dao;


import java.util.List;

public interface GenericDao {

	public List<Object> findAll(Class clazz);
	
	public List<Object> findEntitiesByPage(Class clazz,Integer pageIndex,Integer pageSize);
	
	public Integer getEntitiesCount(Class clazz);
	
	public Object findById(Class clazz,Integer id);
	
	public void add(Object entity);
	
	public void update(Object entity);
	
	public void delete(Object entity);
	
	public void delete(Class clazz,String id);
	
}
