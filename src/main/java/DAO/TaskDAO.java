package DAO;

import Models.Project;
import Models.Tache;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static Utils.DatabaseConnection.getConnection;

public class TaskDAO {

    // Method to get a task by its ID
    public static Tache getTacheById(int id) {
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
                        resultSet.getInt("id_projet") // Assuming the foreign key column is named project_id
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
                tache.getDescription();
                tache.getDateDebut();
                tache.getDateFin();
                tache.getProjectId();
                taches.add(tache);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return taches;
    }

    // Method to add a new task
    public boolean addTask(Tache task) throws SQLException {
        String sql = "INSERT INTO tache (description, date_debut, date_fin, id_projet) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, task.getDescription());
            stmt.setString(2, task.getDateDebut());
            stmt.setString(3, task.getDateFin());
            stmt.setInt(4, task.getProjectId());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
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

    //mofidie une tache

    public boolean updateTache(Tache tache) {
        String sql = "UPDATE tache SET description=?, date_debut=?, date_fin=?, id_projet=? WHERE id=?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(2, tache.getDescription());
            stmt.setString(3, tache.getDateDebut());
            stmt.setString(4, tache.getDateFin());
            stmt.setDouble(5, tache.getProjectId());
            stmt.setInt(6, tache.getId());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
