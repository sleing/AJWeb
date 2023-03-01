package org.ddd.app.student.factory;

import org.ddd.app.generic.dao.GenericDao;
import org.ddd.app.student.dao.StudentDao;
import org.ddd.app.student.dao.StudentMemoryDao;


public class DaoMemoryFactory implements DaoFactoryInterface {

	//����ģʽ
	private static DaoFactoryInterface daoFactory;
	public static DaoFactoryInterface getInstance()
	{
		if(daoFactory == null)
		{
			daoFactory = new DaoMemoryFactory();
		}
		return daoFactory;
	}
	private DaoMemoryFactory()
	{
		super();
	}
	
	@Override
	public StudentDao getStudentDao()
	{
		return new StudentMemoryDao();
	}
//	public TeachertDao getTeacherDao()
//	{
//		return new TeacherMemoryDao();
//	}
	@Override
	public GenericDao getGenericDao() {
		// TODO Auto-generated method stub
		return null;
	}
}
