package Servlets;

import DAO.ProjectDAO;
import DAO.TaskDAO;
import Models.Project;
import Models.Tache;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class DeletTaskServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the project ID from the request parameter
        int TacheID = Integer.parseInt(request.getParameter("id"));

        // Call the DAO method to delete the project
        TaskDAO taskDAO = new TaskDAO();
        boolean isDeleted = taskDAO.deleteTache(TacheID);

        // If deletion is successful, fetch all projects again and forward to the JSP
        if (isDeleted) {
            // Get updated list of projects
            List<Tache> taches = taskDAO.getAllTaches();

            // Set the projects as a request attribute to pass to the JSP
            request.setAttribute("tasks", taches);

            // Forward to the projects.jsp page
            RequestDispatcher dispatcher = request.getRequestDispatcher("/tasks.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error deleting project.");
        }
    }
}
