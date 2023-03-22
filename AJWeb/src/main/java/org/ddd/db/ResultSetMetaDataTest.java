package org.ddd.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class ResultSetMetaDataTest {
    public static void main(String[] args) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = MySqlDAO.getConnection();
            String sql = "select * from user";
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            System.out.println("��ȡ�����������: " + metaData.getColumnCount());
            System.out.println("��ȡָ���е�����: " + metaData.getColumnName(1));
            System.out.println("��ȡָ���е�SQL���Ͷ�Ӧ��java.sql.Types����ֶ�: " + metaData.getColumnType(2));
            System.out.println("��ȡָ���е�SQL����: " + metaData.getColumnTypeName(1));
            con.close();
            stmt.close();
            rs.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
