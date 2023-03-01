package org.ddd.app.student.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ddd.app.student.dao.StudentDBDao;
import org.ddd.app.student.entity.Student;
import org.ddd.app.student.factory.ServiceFactory;

/**
 * Servlet implementation class StudentList
 */
@WebServlet("/app/StudentList")
public class StudentList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pageIndex1 = request.getParameter("pageIndex");
		Integer pageIndex = 0;
		if(pageIndex1!=null){
			pageIndex = Integer.parseInt(pageIndex1);
		}
		Integer pageSize = 10;
		List<Student> students = ServiceFactory.getInstance().getStudentService().findStudentsByPage(pageIndex, pageSize);
		Integer studentCount = ServiceFactory.getInstance().getStudentService().getStudentsCount();
		request.setAttribute("students", students);
		request.setAttribute("studentCount", studentCount);
		request.setAttribute("pageIndex", pageIndex);
		request.getRequestDispatcher("./student/StudentList.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
