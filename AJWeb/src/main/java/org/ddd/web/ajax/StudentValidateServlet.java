package org.ddd.web.ajax;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ddd.web.forward.Student;

import com.alibaba.fastjson.JSON;



@WebServlet("/web/ajax/StudentValidate")
public class StudentValidateServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
public StudentValidateServlet() {
    super();
}
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
request.setCharacterEncoding("UTF-8");

String studentJson = request.getReader().readLine();
Student student =JSON.parseObject(studentJson, Student.class);

Map<String,String> errors = new HashMap<String, String>();
if(student.getName() == null || student.getName().length() <=1)
{
	errors.put("nameError", "姓名必选大于等于2个字符");
}
if(student.getAge() != null && student.getAge() <=0)
{
	errors.put("ageError", "年龄必须大于1");
}

if(student.getHobbies() == null || student.getHobbies().size() ==0)
{
	errors.put("hobbiesError", "至少需要选择一个爱好");
}
response.setContentType("text/html; charset=UTF-8");
String errorJson = JSON.toJSONString(errors);
response.getWriter().append(errorJson);
}

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
}
}
