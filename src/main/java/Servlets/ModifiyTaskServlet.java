package Servlets;

import DAO.TaskDAO;
import Models.Tache;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/modifyProject")
public class ModifiyTaskServlet extends HttpServlet {

    // Handles GET requests (for displaying the edit page)
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        TaskDAO taskDAO = new TaskDAO();
        Tache tache = TaskDAO.getTacheById(id);

        if (tache == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "tache not found");
            return;
        }

        request.setAttribute("tache", tache);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/taches/editTask.jsp");
        dispatcher.forward(request, response);
    }

    // Handles POST requests (for modifying the project)
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String description = request.getParameter("description");
        String dateDebut = request.getParameter("dateDebut");
        String dateFin = request.getParameter("dateFin");
        int project_id = Integer.parseInt(request.getParameter("project_id"));

        Tache tache = new Tache(id, description, dateDebut, dateFin, project_id);
        TaskDAO taskDAO = new TaskDAO();
        boolean updated = taskDAO.updateTache(tache);

        if (updated) {
            response.sendRedirect("/Projects/projects.jsp"); // Redirect to the project list after updating
        } else {
            request.setAttribute("errorMessage", "Failed to update project.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/Projects/editProject.jsp");
            dispatcher.forward(request, response);
        }
    }
}
