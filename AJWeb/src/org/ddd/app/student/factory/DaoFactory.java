package org.ddd.app.student.factory;

import org.ddd.app.generic.dao.GenericDao;
import org.ddd.app.student.dao.StudentDao;
import org.ddd.app.student.dao.StudentMemoryDao;


public class DaoFactory  implements DaoFactoryInterface  {

	public static final String DAOTYPE_memory = "memory";
	public static final String DAOTYPE_db = "db";
	public static final String DAOTYPE_dbReflect = "dbReflect";
	public static final String DAOTYPE_remote = "remote";
	//����ģʽ
	private static DaoFactory daoFactory;
	private static String daoType = null; //memory,db，dbReflect
	
	private static DaoFactoryInterface  daoFactoryInterface;
	public static DaoFactory getInstance()
	{
		if(daoFactory == null)
		{
			daoFactory = new DaoFactory();
			
		}
		return daoFactory;
	}
	private DaoFactory()
	{
		super();
	}
	public static void SetDaoType(String daoType)
	{
		DaoFactory.daoType = daoType;
		if(DAOTYPE_db.equalsIgnoreCase(DaoFactory.daoType))
		{
			daoFactoryInterface = DaoDBFactory.getInstance();
		}
		else if(DAOTYPE_dbReflect.equalsIgnoreCase(DaoFactory.daoType))
		{
			daoFactoryInterface = DaoDBReflectFactory.getInstance();
		}
		else if(DAOTYPE_memory.equalsIgnoreCase(DaoFactory.daoType))
		{
			daoFactoryInterface = DaoMemoryFactory.getInstance();
		}
		else if(DAOTYPE_remote.equalsIgnoreCase(DaoFactory.daoType))
		{
			daoFactoryInterface = DaoRemoteFactory.getInstance();
		}
		else
		{
			throw new IllegalArgumentException("��������ݴ洢����");
		}
	}
	public StudentDao getStudentDao()
	{
		return daoFactoryInterface.getStudentDao();
	}
//	public TeachertDao getTeacherDao()
//	{
//		return new TeacherMemoryDao();
//	}
	@Override
	public GenericDao getGenericDao() {
		return daoFactoryInterface.getGenericDao();
	}
}
