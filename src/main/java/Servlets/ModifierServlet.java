package Servlets;

import DAO.ProjectDAO;
import Models.Project;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/ModifierServlet")
public class ModifierServlet extends HttpServlet {

    private ProjectDAO projectDAO;

    @Override
    public void init() throws ServletException {
        projectDAO = new ProjectDAO();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get project details from the request
        String idParam = req.getParameter("id");
        String nom = req.getParameter("nom");
        String description = req.getParameter("description");
        long dateDebut = Date.parse(req.getParameter("date_debut")); // Assuming the date format is YYYY-MM-DD
        long dateFin = Date.parse(req.getParameter("date_fin"));
        double budget = Double.parseDouble(req.getParameter("budget"));

        if (idParam == null || idParam.isEmpty()) {
            resp.sendRedirect("ProjectServlet?errorMessage=ID du projet invalide");
            return;
        }

        try {
            int id = Integer.parseInt(idParam);

            // Create a new project object
            Project project = new Project(id, nom, description, dateDebut, dateFin, budget);

            // Call the update method
            boolean isUpdated = projectDAO.updateProject(project, id);

            if (isUpdated) {
                resp.sendRedirect("ProjectServlet?successMessage=Projet modifié avec succès");
            } else {
                resp.sendRedirect("ProjectServlet?errorMessage=Erreur lors de la modification du projet");
            }

        } catch (NumberFormatException e) {
            resp.sendRedirect("ProjectServlet?errorMessage=ID ou budget invalide");
        }
    }
}
