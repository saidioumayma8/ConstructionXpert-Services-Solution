package Servlets;

import DAO.ProjectDAO;
import Models.Project;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


public class ProjectServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Collect form parameters
        String nom = request.getParameter("name");
        String description = request.getParameter("description");
        String dateDebut = request.getParameter("startDate");
        String dateFin = request.getParameter("endDate");
        double budget = Double.parseDouble(request.getParameter("budget"));

        // Create a Project object
        Project project = new Project(nom, description, dateDebut, dateFin, budget);

        // Create a ProjectDAO object
        ProjectDAO projectDAO = new ProjectDAO(connection);

        // Call the addProject method to insert the project into the database
        boolean success = projectDAO.addProject(project);

        // Redirect or display success/error messages
        if (success) {
            response.sendRedirect("projects.jsp?success=Project added successfully!");
        } else {
            response.sendRedirect("projects.jsp?error=Failed to add project.");
        }
    }
}
