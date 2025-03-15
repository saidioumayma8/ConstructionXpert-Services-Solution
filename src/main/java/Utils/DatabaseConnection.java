package Utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3305/sportflow", "root", "admin");
                System.out.println("Database Connected Successfully!");
            } catch (Exception e) {
                System.out.println("Database Connection Failed!");
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void main(String[] args) {
        getConnection(); // Just call this to test connection
    }
}
