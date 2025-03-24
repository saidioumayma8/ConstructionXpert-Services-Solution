package DAO;

import Models.Tache;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static Utils.DatabaseConnection.getConnection;

public class TaskDAO {

    // Method to get a task by its ID
    public Tache getTacheById(int id) {
        Tache tache = null;
        String query = "SELECT * FROM tache WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                tache = new Tache(
                        resultSet.getInt("id"),
                        resultSet.getString("description"),
                        resultSet.getString("date_debut"),
                        resultSet.getString("date_fin"),
                        resultSet.getInt("project_id") // Assuming the foreign key column is named project_id
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tache;
    }

    // Method to get all tasks
    public static List<Tache> getAllTaches() {
        List<Tache> taches = new ArrayList<>();
        String sql = "SELECT * FROM tache";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Tache tache = new Tache();
                tache.setId(rs.getInt("id"));
                tache.getDescription(rs.getString("description"));
                tache.getDateDebut(rs.getString("date_debut"));
                tache.getDateFin(rs.getString("date_fin"));
                tache.getProjectId(rs.getInt("project_id"));
                taches.add(tache);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return taches;
    }

    // Method to add a new task
    public void addTache(String description, String dateDebut, String dateFin, int projectId) throws SQLException {
        Connection connection = getConnection();

        if (connection == null) {
            System.err.println("Database connection is null, cannot proceed with the query.");
            throw new SQLException("Database connection is not available.");
        }

        String sql = "INSERT INTO tache (description, date_debut, date_fin, project_id) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, description);
            statement.setString(2, dateDebut);  // Assuming date format is "yyyy-MM-dd"
            statement.setString(3, dateFin);    // Assuming date format is "yyyy-MM-dd"
            statement.setInt(4, projectId);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Task added successfully.");
            } else {
                System.err.println("Failed to add task.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;  // Rethrow the exception to handle it further up the stack
        }
    }

    // Method to update a task
    //

    // Method to delete a task by its ID
    public boolean deleteTache(int id) {
        String query = "DELETE FROM tache WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
