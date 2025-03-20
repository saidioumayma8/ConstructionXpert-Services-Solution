package DAO;

import Models.Project;
import Utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static Utils.DatabaseConnection.connection;

public class ProjectDAO {

    private Connection getConnection() throws SQLException {
        // Database connection setup (update your database credentials here)
        String url = "jdbc:mysql://localhost:3306/your_database";
        String username = "your_username";
        String password = "your_password";
        return DriverManager.getConnection(url, username, password);
    }

    // Method to get a single project by its ID
    // Method to get a single project by its ID
    public Project getProjectById(int id) { // Accept id as an argument
        Project project = null;
        String query = "SELECT * FROM projet WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);  // Set the ID parameter in the query
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                project = new Project(
                        resultSet.getInt("id"),  // If Project has an id field
                        resultSet.getString("nom"),
                        resultSet.getString("description"),
                        resultSet.getDate("date_debut"),
                        resultSet.getDate("date_fin"),
                        resultSet.getDouble("budget")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return project;
    }


    // Method to get all projects (for the project list page)
    public List<Project> getAllProjects() {
        List<Project> projects = new ArrayList<>();
        String query = "SELECT * FROM projet";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Project project = new Project(
                        resultSet.getInt("id"), resultSet.getString("nom"),
                        resultSet.getString("description"),
                        resultSet.getDate("date_debut"),
                        resultSet.getDate("date_fin"),
                        resultSet.getDouble("budget")
                );
                projects.add(project);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projects;
    }

    // Method to add a new project
    public boolean addProject(Project project) {
        String query = "INSERT INTO projects (nom, description, date_debut, date_fin, budget) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, project.getNom());
            statement.setString(2, project.getDescription());
            statement.setDate(3, project.getDateDebut());
            statement.setDate(4, project.getDateFin());
            statement.setDouble(5, project.getBudget());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0; // If rows were inserted, return true
        } catch (SQLException e) {
            System.out.println("Error inserting project: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    // Method to update a project
    public boolean updateProject(Project project, int id) {
        String query = "UPDATE projet SET nom = ?, description = ?, date_debut = ?, date_fin = ?, budget = ? WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, project.getNom());
            preparedStatement.setString(2, project.getDescription());
            preparedStatement.setDate(3, new java.sql.Date(project.getDateDebut().getTime()));
            preparedStatement.setDate(4, new java.sql.Date(project.getDateFin().getTime()));
            preparedStatement.setDouble(5, project.getBudget());
            preparedStatement.setInt(6, id); // Make sure to set the id here

            int result = preparedStatement.executeUpdate();
            return result > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    // Method to delete a project by its ID
    public boolean deleteProject(int id) {
        String query = "DELETE FROM projet WHERE id = ?";

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