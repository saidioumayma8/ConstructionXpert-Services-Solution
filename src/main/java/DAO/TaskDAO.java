package DAO;

import Models.Project;
import Servlets.ProjectServlet;
import Utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class TaskDAO {

    private Connection getConnection() throws SQLException {
        // Database connection setup (update your database credentials here)
        String url = "jdbc:mysql://localhost:3305/Construction";
        String username = "root";
        String password = "admin";
        return DriverManager.getConnection(url, username, password);
    }
    public void addTask(String description, String dateDebut, String dateFin, Project project) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();

        if (connection == null) {
            System.err.println("Database connection is null, cannot proceed with the query.");
            throw new SQLException("Database connection is not available.");
        }

        // SQL query to insert a new task
        String sql = "INSERT INTO tache (description, date_debut, date_fin, id_projet) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            // Convert String dates to java.sql.Date
            Date sqlDateDebut = Date.valueOf(dateDebut);
            Date sqlDateFin = Date.valueOf(dateFin);

            // Set the parameters for the SQL query
            statement.setString(1, description);
            statement.setDate(2, sqlDateDebut);  // Using java.sql.Date for date
            statement.setDate(3, sqlDateFin);    // Using java.sql.Date for date
            statement.setInt(4, project.getId());  // Get the project ID from the project object

            // Execute the query
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Task added successfully.");
            } else {
                System.err.println("Failed to add task.");
            }
        } catch (SQLException e) {
            // Log the exception with a detailed message
            System.err.println("Error executing query: " + e.getMessage());
            e.printStackTrace();
            throw e;  // Rethrow the exception to handle it further up the stack if necessary
        }
    }

}
