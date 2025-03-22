package Servlets;
import DAO.TaskDAO;
import Models.Project;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/createTask")
public class AddTaskServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get data from the form
        String description = request.getParameter("description");
        String dateDebut = request.getParameter("dateDebut");
        String dateFin = request.getParameter("dateFin");
        int projectId = Integer.parseInt(request.getParameter("projectId"));

        // Create Project object (this can be fetched from DB or passed as parameter)
        Project project = new Project();  // Assuming you fetch the actual project from the database

        TaskDAO taskDAO = new TaskDAO();
        try {
            taskDAO.addTask(description, dateDebut, dateFin, project);
            // If task is added successfully, redirect to a success page
            response.sendRedirect("/addTache.jsp");  // Redirect to success page after task creation
        } catch (SQLException e) {
            // Handle exception and send error message in response
            e.printStackTrace();

            // Set an error message as an attribute and send it back in the same page
            request.setAttribute("errorMessage", "An error occurred while adding the task: " + e.getMessage());

            // Forward back to the task creation page
            request.getRequestDispatcher("/createTask.jsp").forward(request, response);
        }
    }
}

