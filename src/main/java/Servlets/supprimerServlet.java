package Servlets;

import DAO.ProjectDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/supprimerServlet")
public class supprimerServlet extends HttpServlet {
    private ProjectDAO projectDAO;

    @Override
    public void init() throws ServletException {
        projectDAO = new ProjectDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id_projet");

        if (idParam == null || idParam.isEmpty()) {
            resp.sendRedirect("ProjectServlet?errorMessage=ID du projet invalide");
            return;
        }

        try {
            int id = Integer.parseInt(idParam);
            boolean deleted = projectDAO.deleteProjet(id);

            if (deleted) {
                resp.sendRedirect("ProjectServlet?successMessage=Projet supprime avec succes");
            } else {
                resp.sendRedirect("ProjectServlet?errorMessage=Aucun projet trouvé avec cet ID");
            }
        } catch (NumberFormatException e) {
            resp.sendRedirect("ProjectServlet?errorMessage=ID du projet invalide");
        }
    }
}