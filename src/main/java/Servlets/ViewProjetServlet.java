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
        List<Project> projects = ProjectDAO.getAllProjects();
        request.setAttribute("projects", projects);  // Set the projects list as a request attribute

        // Forward the request to the JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("Projects/projects.jsp");  // Path to your JSP
        dispatcher.forward(request, response);
    }
}








