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
@WebServlet("/updateServlet")

public class ModifierServlet extends HttpServlet {

    public ProjectDAO projectDAO;
    @Override
    public void init() throws ServletException {
        projectDAO = new ProjectDAO();

    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id_projet"));
        String nom = req.getParameter("nom");
        String Description = req.getParameter("Description");
        Date DateDebut = java.sql.Date.valueOf(req.getParameter("date_debut"));
        Date DateFin = java.sql.Date.valueOf(req.getParameter("date_fin"));
        Double Budget = Double.valueOf(req.getParameter("budget"));

        Project project = new Project(nom,Description,DateDebut,DateFin,Budget);

        boolean isUpdated = projectDAO.updateProjet(project,id);
        if (isUpdated) {
            resp.sendRedirect("ProjectServlet?id=" +id);
        } else {
            resp.sendRedirect("EditeProjet.jsp?id=" +id);
        }


    }
}