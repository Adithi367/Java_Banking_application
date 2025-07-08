package org.example.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection {
    private final static String url ="jdbc:mysql://localhost:3306/student";
    private final static String name="root";
    private final static String pass="mysql";
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,name,pass);
    }
}
