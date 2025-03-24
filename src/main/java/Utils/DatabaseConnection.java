package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static Connection connection;

    public static Connection getConnection() {
        try {
            // 🔍 Check if the connection is null OR closed before reusing it
            if (connection == null || connection.isClosed()) {
                System.out.println("🔄 Establishing a new database connection...");
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3305/Construction",
                        "root",
                        "admin"
                );
                System.out.println("✅ Database connection established.");
            } else {
                System.out.println("♻️ Reusing existing database connection.");
            }
        } catch (ClassNotFoundException e) {
            System.err.println("❌ MySQL Driver not found: " + e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("⚠️ SQL Error while connecting: " + e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }
}
