package org.ddd.web.forward;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ddd.app.student.dao.StudentDBDao;
import org.ddd.app.student.factory.ServiceFactory;


@WebServlet("/web/forward/StudentSave")
public class StudentSave extends HttpServlet {
private static final long serialVersionUID = 1L;
public StudentSave() {
    super();
}
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
request.setCharacterEncoding("UTF-8");

Student student = new Student();
student.setName(request.getParameter("name"));
student.setAge(Integer.parseInt(request.getParameter("age")));
student.setGender(request.getParameter("gender"));
student.setSchool(request.getParameter("school"));
student.setResume(request.getParameter("resume"));;

List<String> hobbies = new ArrayList<String>();
if(request.getParameter("hobby_basketball") != null) hobbies.add("篮球");
if(request.getParameter("hobby_badminton") != null) hobbies.add("羽毛球");
if(request.getParameter("hobby_pingpong") != null) hobbies.add("兵乓球");
student.setHobbies(hobbies);

request.setAttribute("student", student);
request.getRequestDispatcher("./studentView.jsp").forward(request, response);
 
}

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
}
}
