package org.ddd.app.generic.service;

import java.util.List;

import org.ddd.app.annotation.EntityInfoHelper;
import org.ddd.app.generic.dao.GenericDao;
import org.ddd.app.student.factory.DaoFactory;

public class GenericServiceImpl implements GenericService {

	@Override
	public List<Object> findAll(Class clazz) {
		GenericDao dao = DaoFactory.getInstance().getGenericDao();
		return dao.findAll(clazz);
	}

	@Override
	public Object findById(Class clazz,Integer id) {
		GenericDao dao = DaoFactory.getInstance().getGenericDao();
		return dao.findById(clazz,id);
	}

	@Override
	public void add(Object entity) {
		GenericDao dao = DaoFactory.getInstance().getGenericDao();
		Integer id = EntityInfoHelper.getEntityId(entity);
		Object entity2 = dao.findById(entity.getClass(),id);
		if(entity2 == null)
		{
			dao.add(entity);
		}
		else
		{
			throw new IllegalArgumentException("entity  has been existing");
		}
	}

	@Override
	public void update(Object entity) {
//		GenericDao dao = DaoFactory.getInstance().getGenericDao();
//		Entity entity2 = dao.findById(entity.getId());
//		if(entity2 == null)
//		{
//			throw new IllegalArgumentException("entity  hasn't been existing");
//		}
//		else if(! entity2.getId().equals(entity.getId()))
//		{
//			throw new IllegalArgumentException("entity's id can not be modified");
//		}			
//		else
//		{
//			dao.update(entity);
//		}
	}

	@Override
	public void delete(Object entity) {

	}

	@Override
	public void delete(Class clazz,String id) {
//		DaoFactory.getInstance().getGenericDao().delete(id); 
	}

	@Override
	public List<Object> findEntitiesByPage(Class clazz,Integer pageIndex, Integer pageSize) {
		return DaoFactory.getInstance().getGenericDao().findEntitiesByPage(clazz,pageIndex,pageSize);
	}

	@Override
	public Integer getEntitiesCount(Class clazz) {
		return DaoFactory.getInstance().getGenericDao().getEntitiesCount(clazz);
	}

}
