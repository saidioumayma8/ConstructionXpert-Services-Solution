package DAO;

import Models.Project;
import Utils.DatabaseConnection;

import java.sql.*;

public class ProjectDAO {
    private Connection connection;

    // Constructor to initialize database connection
    public ProjectDAO(Connection connection) {
        this.connection = DatabaseConnection.getConnection();
    }

    // Method to insert a project into the database

    public boolean addProject(Project project) {

        String sql = "INSERT INTO Projet (nom, description, date_debut, date_fin, budget) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, project.getNom());
            pstmt.setString(2, project.getDescription());
            pstmt.setDate(3, project.getDateDebut());
            pstmt.setInt(4, project.getDateFin());

            int rowsAffected = pstmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
