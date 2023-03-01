package org.ddd.app.student.factory;

import org.ddd.app.generic.dao.GenericDao;
import org.ddd.app.student.dao.StudentDBDao;
import org.ddd.app.student.dao.StudentDao;


public class DaoDBFactory implements DaoFactoryInterface {

	//����ģʽ
	private static DaoDBFactory daoFactory;
	public static DaoDBFactory getInstance()
	{
		if(daoFactory == null)
		{
			daoFactory = new DaoDBFactory();
		}
		return daoFactory;
	}
	private DaoDBFactory()
	{
		super();
	}
	
	public StudentDao getStudentDao()
	{
		return new StudentDBDao();
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
