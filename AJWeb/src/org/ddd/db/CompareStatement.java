package org.ddd.db;

import javax.naming.SizeLimitExceededException;

import org.ddd.section8.example8_6.MySqlDAO;

import java.sql.*;

/**
 * 批量操作比较PrepareStatement和Statement区别
 */
public class CompareStatement {
    static final int SIZE = 5000;
    public static void main(String[] args) {
        Connection con = null;
        try {
            //Statement
            String sql = "INSERT student(id,name) VALUES(1,'test')";
            con = MySqlDAO.getConnection();
            System.out.println(con.toString());
            long startTime=System.currentTimeMillis();
            Statement stmt = con.createStatement();
            for (int j = 0; j < SIZE; j++) {
                stmt.execute(sql);
            }
            stmt.close();
            System.out.println("Statement运行时间： "+(System.currentTimeMillis() - startTime)+"ms");
            //PreparedStatement
            String psql =  "INSERT student(id,name) VALUES(?,?)";
            startTime=System.currentTimeMillis();
            PreparedStatement ps = con.prepareStatement(psql);
            for (int i=0; i < SIZE; i++) {
                ps.setInt(1, 1);
                ps.setString(2, "test");
                ps.execute();
            }
            ps.close();
            System.out.println("PreparedStatement运行时间： "+(System.currentTimeMillis() - startTime)+"ms");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
