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

public class ViewTaskServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TaskDAO taskDAO = new TaskDAO();
        List<Tache> taches = taskDAO.getAllTaches();
        if (taches.isEmpty()) {
            System.out.println("No tasks found in the database.");
        }
        request.setAttribute("tasks", taches);
        RequestDispatcher dispatcher = request.getRequestDispatcher("taches/Taches.jsp");
        dispatcher.forward(request, response);

    }
}
