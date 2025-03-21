package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                System.out.println("Attempting to connect to the database...");
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3305/Construction",
                        "root",
                        "admin"
                );
                System.out.println("Database connection established.");
            } catch (ClassNotFoundException e) {
                System.err.println("MySQL Driver not found: " + e.getMessage());
                e.printStackTrace();
            } catch (SQLException e) {
                System.err.println("SQL Error while connecting to the database: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("Reusing existing database connection.");
        }
        return connection;
    }
}
