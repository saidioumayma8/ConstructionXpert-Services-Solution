package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Load the MySQL driver
                Class.forName("com.mysql.cj.jdbc.Driver");

                // Establish the connection
                connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3305/Construction", // Your database URL
                        "root",  // Your username
                        "admin"  // Your password
                );

                System.out.println("Database connection established.");
            } catch (SQLException | ClassNotFoundException e) {
                // Log any exceptions
                System.err.println("Failed to establish database connection: " + e.getMessage());
                e.printStackTrace();
            }
        }
        return connection;
    }
}
