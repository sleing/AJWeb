package org.ddd.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.ddd.section8.example8_7.MySqlDAO;

public class AddBatchTester {
    static final int SIZE = 5000;
    public static void main(String[] args) {
        Connection con  = null;
        try {
            con = MySqlDAO.getConnection();
            con.setAutoCommit(false);
            Statement stmt = con.createStatement();
            for (int i = 0; i < SIZE; i++) {
                stmt.addBatch("INSERT student(no,name) VALUES(1,'test')");
            }
            stmt.executeBatch();
            con.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(con != null){
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
