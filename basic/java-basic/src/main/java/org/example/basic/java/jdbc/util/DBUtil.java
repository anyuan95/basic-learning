package org.example.basic.java.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author anyuan
 * @since 2021-07-21 14:11
 */
public class DBUtil {

    public static final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://139.198.28.158:13306/sakila";
    public static final String DB_USERNAME = "root";
    public static final String DB_PASSWORD = "Mxl951213+";

    public static Connection buildConnection() throws Exception {
        Class.forName(DRIVER_CLASS_NAME);
        return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    }
}
