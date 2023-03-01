package org.ddd.db;

import java.sql.*;

public class TransactionTester {
		   public static void main(String[] args){
		      Connection con  = null;
		      try{
		         con = MySqlDAO.getConnection();
		         con.setAutoCommit(false);
		         Statement stmt = con.createStatement();
		         String sql1 = "select max(no) from student";
		         ResultSet rs = stmt.executeQuery(sql1);
		         int no = 0;
		         while(rs.next()){
		            no = rs.getInt(1) + 1;
		         }
		         String sql2 = "insert into student values(" + no + ",'wahaha')";
		         stmt.execute(sql2);
		         con.commit();
		         stmt.close();
		         con.close();
		      }catch(Exception e){
		         try {
		            con.rollback();
		            con.close();
		         } catch (SQLException e1) {
		            e1.printStackTrace();
		         }
		      }
		   }  
		}