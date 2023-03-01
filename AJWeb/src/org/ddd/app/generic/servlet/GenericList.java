package org.ddd.app.generic.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ddd.app.annotation.EntityInfo;
import org.ddd.app.annotation.EntityInfoHelper;
import org.ddd.app.student.entity.Student;
import org.ddd.app.student.factory.ServiceFactory;

/**
 * Servlet implementation class GenericList
 */
@WebServlet("/GenericList")
public class GenericList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenericList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
		Integer pageSize = 10;
		String clazzName = request.getParameter("clazz");
		
		Class clazz = EntityInfoHelper.getClass(clazzName);
		List<Object> entities = ServiceFactory.getInstance().getGenericService().findEntitiesByPage(clazz, pageIndex, pageSize) ;
		Integer entitiesCount = ServiceFactory.getInstance().getGenericService().getEntitiesCount(clazz);
		EntityInfo entityInfo = EntityInfoHelper.getEntityInfo(clazz);
		
		request.setAttribute("entities", entities);
		request.setAttribute("entitiesCount", entitiesCount);
		request.setAttribute("clazzName", clazzName);
		request.setAttribute("pageIndex", pageIndex);
		request.setAttribute("entityInfo", entityInfo);
		request.getRequestDispatcher("./generic/GenericList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
