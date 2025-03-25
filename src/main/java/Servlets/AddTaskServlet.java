package Servlets;

import DAO.TaskDAO;  // Change to TaskDAO if dealing with tasks
import java.io.IOException;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/addTask")  // Correct the mapping if it's for tasks
public class AddTaskServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Correct parameter names
        String description = request.getParameter("description");
        String dateDebut = request.getParameter("date_debut");
        String dateFin = request.getParameter("date_fin");
        String idProjectStr = request.getParameter("id_project");

        // Convert id_project to an integer
        int idProject = 0;
        try {
            idProject = Integer.parseInt(idProjectStr);
        } catch (NumberFormatException e) {
            System.err.println("Invalid project ID: " + idProjectStr);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid project ID.");
            return;
        }

        System.out.println("Received Task Data: Description = " + description + ", DateDebut = " + dateDebut + ", DateFin = " + dateFin + ", ID Project = " + idProject);

        TaskDAO taskDAO = new TaskDAO();

        try {
            // Validate required fields
            if (description == null || description.trim().isEmpty() ||
                    dateDebut == null || dateDebut.trim().isEmpty() ||
                    dateFin == null || dateFin.trim().isEmpty() ||
                    idProject <= 0) {

                System.err.println("Missing fields in task data.");
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "All fields are required.");
                return;
            }

            // Add the task
            taskDAO.addTask(description, dateDebut, dateFin, idProject);
            System.out.println("Task successfully added. Redirecting...");

            // Redirect to tasks page
            response.sendRedirect(request.getContextPath() + "/tasks");

        } catch (SQLException e) {
            System.err.println("SQL Exception in AddTaskServlet: " + e.getMessage());
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to add task due to a database error.");
        } catch (Exception e) {
            System.err.println("Unexpected error in AddTaskServlet: " + e.getMessage());
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An unexpected error occurred.");
        }
    }
}
