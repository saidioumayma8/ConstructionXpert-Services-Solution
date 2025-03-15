package DAO;

import Models.User;
import java.sql.*;

import static Utils.DatabaseConnection.getConnection;

public class UserDAO {

    public static int Login(String email, String motDePasse) {
        try {
            Connection conn = getConnection();
            String sql = "SELECT * FROM User WHERE email = ?"; // Login with email instead of nom
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String pass = rs.getString("motDePasse"); // Ensure correct column name
                if (pass.equals(motDePasse)) {
                    return rs.getInt("id");  // Return user ID if credentials match
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
}
