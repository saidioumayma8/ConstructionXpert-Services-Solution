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

@WebServlet("/ViewProjetServlet")
public class ViewProjetServlet extends HttpServlet {

    private ProjectDAO projectDAO;

    @Override
    public void init() throws ServletException {
        projectDAO = new ProjectDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Fetch all projects from the database
        List<Project> projects = projectDAO.getAllProjects();

        req.setAttribute("projects", projects); // Pass the list of projects to the JSP
        req.getRequestDispatcher("projects.jsp").forward(req, resp); // Forward to the JSP page for display
    }
}




