package org.ddd.db;

import java.sql.Connection;
import java.sql.Statement;


public class InsertTester {
	public static void main(String[] args) throws Exception{
		Connection con = MySqlDAO.getConnection();
		Statement stmt = con.createStatement();
		String sql = "insert into student(id,name,age) values(1007,'小亮',28)";
		stmt.executeUpdate(sql);
	    int count = stmt.executeUpdate(sql);
	    System.out.println("成功插入了"+count+" 条数据");		
		stmt.close();
		con.close();
	}	
}

