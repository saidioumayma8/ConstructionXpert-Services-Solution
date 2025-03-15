package DAO;

import Models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static Utils.DatabaseConnection.getConnection;

public class UserDAO {

    private static final String DB_URL = "jdbc:mysql://localhost:3305/sportflow";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "admin";

    public static int Login(String nom, String password) {
        try {
            Connection conn = getConnection();
            String sql = "SELECT * FROM Users WHERE nom = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nom);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String pass = rs.getString("password");
                if (pass.equals(password)) {
                    return rs.getInt("id");  // Return the user's ID if credentials match
                }
            }
            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 0;
    }


    public static String FountTheUserRole(int userId) {
        try {
            Connection conn = getConnection();
            String sql = "SELECT Role FROM users WHERE Id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("Role");
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<User> getUsersByRole(String role) {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";

        if (role != null && !role.equals("all")) {
            query += " WHERE role = ?";
        }

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement stmt = connection.prepareStatement(query)) {

            if (role != null && !role.equals("all")) {
                stmt.setString(1, role);
            }

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setNom(rs.getString("name"));
                user.setMotDePasse(rs.getString("password"));  // Set password
                user.setRole(rs.getString("role"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }


    // Add a new user
    public boolean addUser(User user) {
        String query = "INSERT INTO users (name, password, date_naissance, sport_pratique, role) VALUES (?, ?, ?, ?, 'Member')";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, user.getNom());
            stmt.setString(2, user.getMotDePasse());
            stmt.setDate(3, Date.valueOf(user.getDateNaissance()));
            stmt.setString(4, user.getSportPratique());

            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    // Delete a user by ID
    public boolean deleteUser(int id) {
        String query = "DELETE FROM users WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}
