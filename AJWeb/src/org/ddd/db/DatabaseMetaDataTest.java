package org.ddd.db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;

public class DatabaseMetaDataTest {
    public static void main(String[] args) throws Exception {
        Connection con = MySqlDAO.getConnection();
        DatabaseMetaData metaData = con.getMetaData();
        System.out.println("��ȡ���ݿ�Ĳ�Ʒ����: " + metaData.getDatabaseProductName());
        System.out.println("��ȡ���ݿ�İ汾��: " + metaData.getDatabaseProductVersion());
        System.out.println("��ȡ���ݿ��URL: " + metaData.getURL());
        con.close();
    }

}

