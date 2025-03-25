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
import java.util.List;

@WebServlet("/deleteTask")
public class DeletTaskServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int TacheID = Integer.parseInt(request.getParameter("id"));

        TaskDAO taskDAO = new TaskDAO();
        boolean isDeleted = taskDAO.deleteTache(TacheID);

        if (isDeleted) {

            List<Tache> taches = taskDAO.getAllTaches();

            request.setAttribute("tasks", taches);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("/taches");
            dispatcher.forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error deleting tache.");
        }
    }
}
