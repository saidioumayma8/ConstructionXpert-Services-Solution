package Servlets;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import DAO.TaskDAO;
import Models.Tache;

@WebServlet("/AddTaskServlet")
public class AddTaskServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form parameters
        String nom = request.getParameter("nom"); // ✅ Fix: Ensure 'nom' is retrieved
        String description = request.getParameter("description");
        String date_debut = request.getParameter("date_debut");
        String date_fin = request.getParameter("date_fin");
        int project_id = Integer.parseInt(request.getParameter("project_id"));

        // Create a Task object
        Tache newTask = new Tache(description, date_debut, date_fin, project_id);

        // Insert task into the database
        TaskDAO taskDAO = new TaskDAO();
        boolean success = false;
        try {
            success = taskDAO.addTask(newTask);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Redirect or show a message
        if (success) {
            response.sendRedirect("Taches.jsp?success=1");
        } else {
            response.sendRedirect("addTache.jsp?error=1");
        }
    }
}
