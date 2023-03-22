package org.ddd.app.reflect;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import org.ddd.app.student.entity.Student;

public class ClassReflector{

	public static void main(String[] args) {
		Class studentClazz = Student.class;
		String java = toJava(studentClazz);
		writeTXT("C:\\IDE\\eclipse-workspaceTeach\\AJWeb\\src\\org\\cqut\\ddd\\reflect\\out\\",
				"Student.java",java);
		System.out.println("生成成功");
	}
	private static String toJava(Class clazz)
	{
		StringBuilder out = new StringBuilder();
		//请在下面增加生成java文件的代码
		//out.append("this is test");
		return out.toString();
	}
	
	private static void writeTXT(String path,String title,String content)
	{
	    try {
	        File writename = new File(path);// 相对路径，如果没有则要建立一个新的output。txt文件
	        if(!writename.exists()){
	            writename.mkdirs();
	        }
	        writename = new File(path+"\\"+title+".txt");// 相对路径，如果没有则要建立一个新的output。txt文件
	        writename.createNewFile(); // 创建新文件
	        BufferedWriter out = new BufferedWriter(new FileWriter(writename));
	        out.write(content); // \r\n即为换行
	        out.flush(); // 把缓存区内容压入文件
	        out.close(); // 最后记得关闭文件

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}
