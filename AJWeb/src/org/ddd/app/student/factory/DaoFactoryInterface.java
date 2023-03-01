package org.ddd.app.student.factory;

import org.ddd.app.generic.dao.GenericDao;
import org.ddd.app.student.dao.StudentDao;

public interface DaoFactoryInterface {

	public StudentDao getStudentDao();
	//	public TeachertDao getTeacherDao()
	//	{
	//		return new TeacherMemoryDao();
	//	}
	public GenericDao getGenericDao();
}