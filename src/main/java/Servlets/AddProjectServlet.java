package Servlets;

import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import DAO.ProjectDAO;
import Models.Project;
import Utils.DatabaseConnection;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/create-offre")
public class AddProjectServlet extends HttpServlet {
    private Connection connection;

    @Override
    public void init() throws ServletException {
        try {
            connection = DatabaseConnection.getConnection();
            if (connection != null) {
                System.out.println("Database connection established!");
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT 1");
                if (resultSet.next()) {
                    System.out.println("Connection is valid.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Database connection failed", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        try {
            String nom = request.getParameter("nom");
            String description = request.getParameter("description");
            String dateDebut = request.getParameter("dateDebut");
            String dateFin = request.getParameter("dateFin");
            String budget = request.getParameter("budget");


            //if (titre == null || description == null || dateStr == null) {
            //    response.getWriter().write("Error: Missing fields");
            // return;
            //}


            Project project = new Project();
            project.setNom(nom);
            project.setDescription(description);
            project.setDateDebut(dateDebut);
            project.setBudget(Double.parseDouble(budget));

            ProjectDAO dao = new ProjectDAO(connection);
            boolean isCreated = dao.addProject(project);

            if (isCreated) {
                response.getWriter().write("Project successfully created!");
            } else {
                response.getWriter().write("Error: Could not create Project.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Error: " + e.getMessage());
        }
    }

    @Override
    public void destroy() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
