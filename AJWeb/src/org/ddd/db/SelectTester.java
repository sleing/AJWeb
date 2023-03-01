package org.ddd.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class SelectTester {
	public static void main(String[] args) throws Exception{
		Connection con = MySqlDAO.getConnection();
		Statement stmt = con.createStatement();
		String sql = "select id,name,age from student where no >= 1001";
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next()){
			System.out.print("学号：" + rs.getInt("no"));
			System.out.println("	姓名：" + rs.getString(2));
		}
		stmt.close();
		con.close();
	}	
}
