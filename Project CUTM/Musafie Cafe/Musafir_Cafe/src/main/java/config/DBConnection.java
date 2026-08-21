package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/musafir_cafe_db";

    private static final String USERNAME = "root";

    private static final String PASSWORD = "Jyoti@2003";   // Change if your MySQL password is different

    private static Connection connection;
    private DBConnection() {
    }
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            if (connection == null || connection.isClosed()) {

                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

                System.out.println("Database Connected Successfully.");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver Not Found.");
            e.printStackTrace();

        } catch (SQLException e) {

            System.out.println("Database Connection Failed.");
            e.printStackTrace();
        }
        return connection;
    }
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {

                connection.close();
                System.out.println("Database Connection Closed.");
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }
}