package Servlets;

import DAO.ProjectDAO;
import Models.Project;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ViewProjetServlet")
public class ViewProjetServlet extends HttpServlet {

    private ProjectDAO projectDAO;

    @Override
    public void init() throws ServletException {
        projectDAO = new ProjectDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");

        if (idParam == null || idParam.isEmpty()) {
            resp.sendRedirect("ProjectServlet?errorMessage=ID du projet invalide");
            return;
        }

        try {
            int id = Integer.parseInt(idParam);
            Project projet = projectDAO.getProjectById(id); // Call the method with the id passed

            if (projet != null) {
                req.setAttribute("projet", projet);
                req.getRequestDispatcher("DashboardProjet.jsp").forward(req, resp);
            } else {
                resp.sendRedirect("ProjectServlet?errorMessage=Aucun projet trouvé avec cet ID");
            }
        } catch (NumberFormatException e) {
            resp.sendRedirect("ProjectServlet?errorMessage=ID du projet invalide");
        }
    }
}
