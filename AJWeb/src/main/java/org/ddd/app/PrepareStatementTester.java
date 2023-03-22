package org.ddd.app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class PrepareStatementTester {
	public static void main(String[] args) throws Exception{
		Connection con = MySqlDAO.getConnection();
		String sql = "select id,name,age from student where no >= ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, 1001 );
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			System.out.print("学号" + rs.getInt("no"));
			System.out.println("	姓名" + rs.getString(2));
		}
		ps.close();
		con.close();
	}	
}
