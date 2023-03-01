package org.ddd.db;

import com.mysql.jdbc.MysqlDefs;

import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.ddd.section8.example8_12.MySqlDAO;

public class ParameterMetaDataTest {
    public static void main(String[] args) throws Exception {
        Connection con = null;
        try {
            con = MySqlDAO.getConnection();
            String sql = "select * from user where id=? and sname=?";
            PreparedStatement prepareStatement = con.prepareStatement(sql);
            ParameterMetaData parameterMetaData = prepareStatement.getParameterMetaData();
            //��ȡ��������
            int count = parameterMetaData.getParameterCount();
            System.out.println("ռλ������Ϊ: " + count);
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
