package demo.example.demo.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseUtil {

    private static Connection con;
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASS = "tiger";

    private DatabaseUtil() {
    }

    public static Connection getConnection() {
        try {
            if (con == null || con.isClosed()) {
                con = DriverManager.getConnection(DB_URL, USER, PASS);
                 System.out.println("Database connection created.");
            }
            return con;
        } catch (Exception e) {
            System.out.println("Error getting the connection " + e.getMessage());
            return null;
        }
    }

     public static void closeConnection() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
                System.out.println("Database connection closed.");
            }
        } catch (Exception e) {
            System.out.println("Error closing the connection " + e.getMessage());
        } finally {
            con = null;
        }
    }

}
