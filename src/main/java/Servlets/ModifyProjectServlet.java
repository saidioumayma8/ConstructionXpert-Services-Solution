package Servlets;

import DAO.ProjectDAO;
import Models.Project;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/modifyProject")
public class ModifyProjectServlet extends HttpServlet {

    // Handles GET requests (for displaying the edit page)
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        ProjectDAO projectDAO = new ProjectDAO();
        Project project = projectDAO.getProjectById(id);

        if (project == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Project not found");
            return;
        }

        request.setAttribute("project", project);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Projects/editProject.jsp");
        dispatcher.forward(request, response);
    }

    // Handles POST requests (for modifying the project)
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nom = request.getParameter("nom");
        String description = request.getParameter("description");
        String dateDebut = request.getParameter("dateDebut");
        String dateFin = request.getParameter("dateFin");
        double budget = Double.parseDouble(request.getParameter("budget"));

        Project project = new Project(id, nom, description, dateDebut, dateFin, budget);
        ProjectDAO projectDAO = new ProjectDAO();
        boolean updated = projectDAO.updateProject(project);

        if (updated) {
            response.sendRedirect("/Construction/Projects/projects.jsp"); // Redirect to the project list after updating
        } else {
            request.setAttribute("errorMessage", "Failed to update project.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/Projects/editProject.jsp");
            dispatcher.forward(request, response);
        }
    }
}
