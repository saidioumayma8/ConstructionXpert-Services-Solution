package Servlets;

import DAO.ProjectDAO;
import Models.Project;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/AddProjectServlet")
public class AddProjectServlet extends HttpServlet {

    private ProjectDAO projectDAO;

    @Override
    public void init() throws ServletException {
        projectDAO = new ProjectDAO();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Retrieve form data
        String nom = req.getParameter("nom");
        String description = req.getParameter("description");
        String dateDebutStr = req.getParameter("date_debut");
        String dateFinStr = req.getParameter("date_fin");
        double budget = Double.parseDouble(req.getParameter("budget"));

        // Validate dateDebut and dateFin
        if (dateDebutStr == null || dateDebutStr.isEmpty()) {
            resp.sendRedirect("Projects/addProject.jsp?error=Date de début manquante");
            return;
        }

        if (dateFinStr == null || dateFinStr.isEmpty()) {
            resp.sendRedirect("Projects/addProject.jsp?error=Date de fin manquante");
            return;
        }

        // Parse dates
        java.sql.Date dateDebut = java.sql.Date.valueOf(dateDebutStr);
        java.sql.Date dateFin = java.sql.Date.valueOf(dateFinStr);

        // Create the project object
        Project project = new Project(nom, description, dateDebut, dateFin, budget);

        // Add the project using the DAO
        boolean isAdded = projectDAO.addProject(project);

        if (isAdded) {
            resp.sendRedirect("projectList.jsp"); // Redirect to project list page if successful
        } else {
            resp.sendRedirect("Projects/addProject.jsp?error=Failed to add project");
        }
    }
}

