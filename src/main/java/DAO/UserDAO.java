package DAO;
import org.mindrot.jbcrypt.BCrypt;
import Models.User;
import java.sql.*;

import static Utils.DatabaseConnection.getConnection;

public class UserDAO {

    public static int Login(String email, String motDePasse) {
        try {
            Connection conn = getConnection();
            String sql = "SELECT * FROM user WHERE email = ?"; // Login with email instead of nom
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String pass = rs.getString("motDePasse"); // Ensure correct column name
                if (BCrypt.checkpw(motDePasse, pass)) {
                    return rs.getInt("id");
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
