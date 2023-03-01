package org.ddd.db;

import java.sql.Connection;
import java.sql.Statement;

public class CreateTableTester {
	public static void main(String[] args) throws Exception{
		Connection con = MySqlDAO.getConnection();
		Statement stmt = con.createStatement();
		String sql = "create table student (no int primary key, name varchar(50),age int";
		stmt.execute(sql);
		stmt.close();
		con.close();
	}
}
