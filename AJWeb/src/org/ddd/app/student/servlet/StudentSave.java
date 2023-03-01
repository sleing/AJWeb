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


@WebServlet("/app/StudentSave")
public class StudentSave extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentSave() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("start");
        String name = request.getParameter("name");
        Integer age = Integer.parseInt(request.getParameter("age"));
        String gender = request.getParameter("gender");

        System.out.println("name:"+name+";age:"+age+";gender:"+gender);
        Student student = new Student(null,name,age);
        try {
            ServiceFactory.getInstance().getStudentService().add(student);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        response.sendRedirect("./StudentList");
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
