package DAO;

import Models.Tache;
import Utils.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO {

    // get task by id
    public static Tache getTacheById(int id) {
        Tache tache = null;
        String query = "SELECT * FROM tache WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                tache = new Tache(
                        resultSet.getInt("id"),
                        resultSet.getString("description"),
                        resultSet.getString("date_debut"),
                        resultSet.getString("date_fin"),
                        resultSet.getInt("id_projet") // Make sure this column exists
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tache;
    }

    // get all tasks
    public List<Tache> getAllTaches() throws SQLException {
        List<Tache> taskList = new ArrayList<>();
        String query = "SELECT * FROM tache";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query))
        {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String description = resultSet.getString("description");
                String dateDebut = resultSet.getString("date_debut");
                String dateFin = resultSet.getString("date_fin");
                int projectId = resultSet.getInt("id_projet");

                // DEBUG: Print out each retrieved task
                System.out.println("Retrieved Task -> ID: " + id + ", Description: " + description + ", Start: " + dateDebut + ", End: " + dateFin + ", ProjectID: " + projectId);

                taskList.add(new Tache(id, description, dateDebut, dateFin, projectId));
            }
        }  catch (SQLException e) {
            e.printStackTrace();
        }
        return taskList;
    }

    // Add a new task
    public void addTask(String description, String dateDebut, String dateFin, int idProject) throws SQLException {
        System.out.println("Inserting Task: Description = " + description + ", Start Date = " + dateDebut + ", End Date = " + dateFin + ", ProjectID = " + idProject);

        String query = "INSERT INTO tache (description, date_debut, date_fin, id_projet) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, description);
            preparedStatement.setString(2, dateDebut);
            preparedStatement.setString(3, dateFin);
            preparedStatement.setInt(4, idProject);

            preparedStatement.executeUpdate();
        }
    }

    // Delete task by its ID
    public boolean deleteTache(int id) {
        String query = "DELETE FROM tache WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Update a task
    public boolean updateTache(Tache tache) {
        String sql = "UPDATE tache SET description=?, date_debut=?, date_fin=?, id_projet=? WHERE id=?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, tache.getDescription());
            stmt.setString(2, tache.getDateDebut());
            stmt.setString(3, tache.getDateFin());
            stmt.setInt(4, tache.getProjectId());
            stmt.setInt(5, tache.getId());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
