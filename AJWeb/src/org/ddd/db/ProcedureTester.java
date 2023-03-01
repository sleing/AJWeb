package org.ddd.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

public class ProcedureTester {
	public static void main(String[] args) throws Exception{
		Connection con = MySqlDAO.getConnection();
		String sql = "call insertNewStudent('coco',?);";
		CallableStatement stmt = con.prepareCall(sql);
		stmt.registerOutParameter(1, Types.INTEGER);
		stmt.execute();
		System.out.println("新生学号： " + stmt.getInt(1));
		stmt.close();
		con.close();
	}	
}
