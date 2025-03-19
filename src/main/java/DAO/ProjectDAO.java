package DAO;

import Models.Project;
import Utils.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectDAO {

    public ProjectDAO() {
    }

    public void addProject(Project projet) {
        String query = "INSERT INTO projet (nom, description, date_debut, date_fin, Budget) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, projet.getNom());
            preparedStatement.setString(2, projet.getDescription());
            preparedStatement.setDate(3, new java.sql.Date(projet.getDateDebut().getTime()));
            preparedStatement.setDate(4, new java.sql.Date(projet.getDateFin().getTime()));
            preparedStatement.setDouble(5, projet.getBudget());

            preparedStatement.executeUpdate();
            System.out.println("Projet ajouté avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();
            // You can add more detailed error handling and logging here
        }
    }

    public Project getProjetById(int id) {
        Project projet = null;
        String query = "SELECT * FROM projet WHERE id_projet = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                projet = new Project(
                        resultSet.getInt("id_projet"),
                        resultSet.getString("nom"),  // Ensure column name matches your database schema
                        resultSet.getString("description"),
                        resultSet.getDate("date_debut"),
                        resultSet.getDate("date_fin"),
                        resultSet.getDouble("budget")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projet;
    }

    public List<Project> getProjetList() {
        List<Project> projets = new ArrayList<>();
        String query = "SELECT * FROM projet";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Project projet = new Project(
                        resultSet.getInt("id_projet"),
                        resultSet.getString("nom"),  // Column name should match database
                        resultSet.getString("description"),
                        resultSet.getDate("date_debut"),
                        resultSet.getDate("date_fin"),
                        resultSet.getDouble("budget")
                );
                projets.add(projet);
            }
            System.out.println("Projects retrieved from DB: " + projets.size()); // Debugging line

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projets;
    }

    public boolean updateProjet(Project projet, int id) {
        String query = "UPDATE projet SET nom=?, description=?, date_debut=?, date_fin=?, budget=? WHERE id_projet=?";
        boolean result = false;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, projet.getNom());
            preparedStatement.setString(2, projet.getDescription());
            preparedStatement.setDate(3, new java.sql.Date(projet.getDateDebut().getTime()));
            preparedStatement.setDate(4, new java.sql.Date(projet.getDateFin().getTime()));
            preparedStatement.setDouble(5, projet.getBudget());
            preparedStatement.setInt(6, id);

            preparedStatement.executeUpdate();
            result = true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean deleteProjet(int id) {
        String query = "DELETE FROM projet WHERE id_projet = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate(); // Get affected rows

            return rowsAffected > 0; // If at least one row was deleted, return true
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
