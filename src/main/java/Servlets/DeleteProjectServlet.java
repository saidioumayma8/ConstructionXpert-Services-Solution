package Servlets;

import DAO.ProjectDAO;
import Models.Project;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/deleteProject")
public class DeleteProjectServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the project ID from the request parameter
        int projectId = Integer.parseInt(request.getParameter("id"));

        // Call the DAO method to delete the project
        ProjectDAO projectDAO = new ProjectDAO();
        boolean isDeleted = projectDAO.deleteProject(projectId);

        // If deletion is successful, fetch all projects again and forward to the JSP
        if (isDeleted) {
            // Get updated list of projects
            List<Project> projects = projectDAO.getAllProjects();

            // Set the projects as a request attribute to pass to the JSP
            request.setAttribute("projects", projects);

            // Forward to the projects.jsp page
            RequestDispatcher dispatcher = request.getRequestDispatcher("/projects.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error deleting project.");
        }
    }

}


