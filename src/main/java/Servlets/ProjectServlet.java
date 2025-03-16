package Servlets;

import Utils.DatabaseConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProjectServlet extends HttpServlet {

    // Retrieve Project Details (GET)
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int projectId = Integer.parseInt(request.getParameter("id"));
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM Projet WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, projectId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                request.setAttribute("projectId", rs.getInt("id"));
                request.setAttribute("projectName", rs.getString("nom"));
                request.setAttribute("projectDescription", rs.getString("description"));
                request.setAttribute("startDate", rs.getDate("date_debut"));
                request.setAttribute("endDate", rs.getDate("date_fin"));
                request.setAttribute("budget", rs.getDouble("budget"));
            }

            request.getRequestDispatcher("projectDetails.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
