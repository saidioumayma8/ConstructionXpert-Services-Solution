package DAO;

import Models.Project;
import Utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectDAO {

    private Connection getConnection() throws SQLException {
        // Database connection setup (update your database credentials here)
        String url = "jdbc:mysql://localhost:3305/Construction";
        String username = "root";
        String password = "admin";
        return DriverManager.getConnection(url, username, password);
    }

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
    public static List<Project> getAllProjects() {
        List<Project> projects = new ArrayList<>();

        String query = "SELECT id, nom, description, date_debut, date_fin, budget FROM projet";

        // Open a new connection inside the method
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Project project = new Project();
                project.setId(rs.getInt("id"));
                project.setNom(rs.getString("nom"));
                project.setDescription(rs.getString("description"));
                project.setDateDebut(rs.getDate("date_debut"));
                project.setDateFin(rs.getDate("date_fin"));
                project.setBudget(rs.getDouble("budget"));
                projects.add(project);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return projects;
    }


    // Method to add a project to the database
        public void addProject(String nom, String description, String dateDebut, String dateFin, double budget) throws SQLException {
            Connection connection = DatabaseConnection.getConnection();

            if (connection == null) {
                System.err.println("Database connection is null, cannot proceed with the query.");
                throw new SQLException("Database connection is not available.");
            }

            // SQL query to insert a new project
            String sql = "INSERT INTO Projet (nom, description, date_debut, date_fin, budget) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                // Set the parameters for the SQL query
                statement.setString(1, nom);
                statement.setString(2, description);
                statement.setString(3, dateDebut);  // Assuming date format is "yyyy-MM-dd"
                statement.setString(4, dateFin);    // Assuming date format is "yyyy-MM-dd"
                statement.setDouble(5, budget);

                // Execute the query
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Project added successfully.");
                } else {
                    System.err.println("Failed to add project.");
                }
            } catch (SQLException e) {
                // Log the exception with a detailed message
                System.err.println("Error executing query: " + e.getMessage());
                e.printStackTrace();
                throw e;  // Rethrow the exception to handle it further up the stack if necessary
            }
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