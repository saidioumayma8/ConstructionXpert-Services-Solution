package Servlets;

import DAO.ProjectDAO;
import Models.Project;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ProjectServlet")
public class ProjectServlet extends HttpServlet {

    private ProjectDAO projectDAO;

    @Override
    public void init() throws ServletException {
        projectDAO = new ProjectDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String errorMessage = req.getParameter("errorMessage");
        String successMessage = req.getParameter("successMessage");
        List<Project> projects = projectDAO.getAllProjects();

        req.setAttribute("projects", projects);
        req.setAttribute("errorMessage", errorMessage);
        req.setAttribute("successMessage", successMessage);

        req.getRequestDispatcher("ProjectList.jsp").forward(req, resp);
    }
}
