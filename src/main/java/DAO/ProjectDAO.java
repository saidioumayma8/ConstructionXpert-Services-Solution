package DAO;

import Utils.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectDAO {

    // Add a new project
    public boolean addProject(String name, String description, String startDate, String endDate, double budget) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "INSERT INTO Projet (nom, description, date_debut, date_fin, budget) VALUES (?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, description);
            pstmt.setString(3, startDate);
            pstmt.setString(4, endDate);
            pstmt.setDouble(5, budget);

            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Other methods (getProject, updateProject, deleteProject) can be added here
}

