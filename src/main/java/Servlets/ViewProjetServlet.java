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
import java.util.List;

@WebServlet("/projects")
public class ViewProjetServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProjectDAO projectDAO = new ProjectDAO();
        List<Project> projects = projectDAO.getAllProjects();
        if (projects.isEmpty()) {
            System.out.println("No projects found in the database.");
        }
        request.setAttribute("projects", projects);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Projects/projects.jsp");
        dispatcher.forward(request, response);

    }
}








