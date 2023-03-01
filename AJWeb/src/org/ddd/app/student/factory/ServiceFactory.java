package org.ddd.app.student.factory;

import org.ddd.app.generic.service.GenericService;
import org.ddd.app.generic.service.GenericServiceImpl;
import org.ddd.app.student.dao.StudentDao;
import org.ddd.app.student.dao.StudentMemoryDao;
import org.ddd.app.student.service.StudentService;
import org.ddd.app.student.service.StudentServiceImpl;


public class ServiceFactory {
	private static ServiceFactory serviceFactory;
	public static ServiceFactory getInstance()
	{
		if(serviceFactory == null)
		{
			serviceFactory = new ServiceFactory();
		}
		return serviceFactory;
	}
	private ServiceFactory()
	{
		super();
	}
	
	public StudentService getStudentService()
	{
		return new StudentServiceImpl();
	}
	public GenericService getGenericService()
	{

		return new GenericServiceImpl();
	}
}
