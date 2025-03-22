package DAO;

import Models.Project;
import Utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TaskDAO {

    public void addTask(String description, String dateDebut, String dateFin, Project project) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();

        if (connection == null) {
            System.err.println("Database connection is null, cannot proceed with the query.");
            throw new SQLException("Database connection is not available.");
        }

        // SQL query to insert a new task
        String sql = "INSERT INTO tache (description, date_debut, date_fin, id_projet) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            // Set the parameters for the SQL query
            statement.setString(1, description);
            statement.setString(2, dateDebut);  // Using java.sql.Date for date
            statement.setString(3, dateFin);    // Using java.sql.Date for date
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
