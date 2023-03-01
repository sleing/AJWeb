package org.ddd.app.student.factory;

import org.ddd.app.generic.dao.GenericDao;
import org.ddd.app.student.dao.StudentDBDao;
import org.ddd.app.student.dao.StudentDao;
import org.ddd.app.student.dao.StudentRemoteDao;


public class DaoRemoteFactory implements DaoFactoryInterface {

	//����ģʽ
	private static DaoRemoteFactory daoFactory;
	public static DaoRemoteFactory getInstance()
	{
		if(daoFactory == null)
		{
			daoFactory = new DaoRemoteFactory();
		}
		return daoFactory;
	}
	private DaoRemoteFactory()
	{
		super();
	}
	
	public StudentDao getStudentDao()
	{
		return new StudentRemoteDao();
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
