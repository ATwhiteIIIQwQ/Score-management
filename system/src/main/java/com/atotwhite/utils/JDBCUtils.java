package com.atotwhite.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class JDBCUtils {
    static InputStream is = null;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
        }
    }

    //获取连接(自己决定，封装到方法中)
    //static静态，直接使用类名.方法名更方便
    public static Connection getconnection() throws Exception {
        String url = "jdbc:mysql://localhost:3306/ry-vue";
        String user = "root";
        String password = "root";
        Connection conn = DriverManager.getConnection(url, user, password);
        return conn;
    }

    //释放资源
    public static void release(ResultSet rs, PreparedStatement pstm, Connection
            conn) throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (pstm != null) {
            pstm.close();
        }
        if (conn != null) {
            conn.close();
        }
    }
}