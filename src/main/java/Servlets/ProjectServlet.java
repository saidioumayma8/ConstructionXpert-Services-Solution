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
import java.util.List;


@WebServlet("/ProjectServlet")
public class ProjectServlet extends HttpServlet {
    public ProjectDAO projectDAO;

    @Override
    public void init() throws ServletException {
        projectDAO = new ProjectDAO();

    }

    @Override

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Project> projets = projectDAO.getProjetList(); // Fetch updated list
        req.setAttribute("projets", projets);

        String successMessage = req.getParameter("successMessage");
        String errorMessage = req.getParameter("errorMessage");

        if (successMessage != null) {
            req.setAttribute("successMessage", successMessage);
        }
        if (errorMessage != null) {
            req.setAttribute("errorMessage", errorMessage);
        }

        req.getRequestDispatcher("ListProjet.jsp").forward(req, resp);
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {

            String Nom = req.getParameter("projectName");
            String description = req.getParameter("projectDescription");
            Date DateDebut = java.sql.Date.valueOf(req.getParameter("startDate"));
            Date DateFin = java.sql.Date.valueOf(req.getParameter("endDate"));
            Double Budget = Double.parseDouble(req.getParameter("budget"));
            if (Nom == null || description == null || DateDebut == null || DateFin == null || Budget == null) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Veuillez remplir tous les champs");
                return;
            }


            Project projet = new Project(Nom, description, DateDebut, DateFin, Budget);
            projectDAO.addProject(projet);
            resp.sendRedirect("ProjectServlet");


        } catch (Exception e) {
            req.setAttribute("errorMessage", "Erreur lors de l'ajout du projet : " + e.getMessage());
            req.getRequestDispatcher("/AjouterProjet.jsp").forward(req, resp);
        }


    }}