package org.ddd.app.student.factory;

import org.ddd.app.generic.dao.GenericDBDao;
import org.ddd.app.generic.dao.GenericDao;
import org.ddd.app.student.dao.StudentDBDao;
import org.ddd.app.student.dao.StudentDBReflectDao;
import org.ddd.app.student.dao.StudentDao;


public class DaoDBReflectFactory implements DaoFactoryInterface {

	//����ģʽ
	private static DaoDBReflectFactory daoFactory;
	public static DaoDBReflectFactory getInstance()
	{
		if(daoFactory == null)
		{
			daoFactory = new DaoDBReflectFactory();
		}
		return daoFactory;
	}
	private DaoDBReflectFactory()
	{
		super();
	}
	
	public StudentDao getStudentDao()
	{
		return new StudentDBReflectDao();
	}
	public GenericDao getGenericDao()
	{
		return new GenericDBDao();
	}
//	public TeachertDao getTeacherDao()
//	{
//		return new TeacherMemoryDao();
//	}
}
