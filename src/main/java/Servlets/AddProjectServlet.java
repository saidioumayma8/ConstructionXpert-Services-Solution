package Servlets;

import DAO.ProjectDAO;
import java.io.IOException;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/addProject")
public class AddProjectServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom = request.getParameter("nom");
        String description = request.getParameter("description");
        String dateDebut = request.getParameter("date_debut");
        String dateFin = request.getParameter("date_fin");
        double budget;

        try {
            budget = Double.parseDouble(request.getParameter("budget"));
        } catch (NumberFormatException e) {
            System.err.println("Invalid budget input: " + e.getMessage());
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid budget value.");
            return;
        }

        System.out.println("Received data: Nom = " + nom + ", Description = " + description + ", DateDebut = " + dateDebut + ", DateFin = " + dateFin + ", Budget = " + budget);

        ProjectDAO projectDAO = new ProjectDAO();

        try {
            if (nom == null || nom.trim().isEmpty() ||
                    description == null || description.trim().isEmpty() ||
                    dateDebut == null || dateFin == null || budget <= 0) {
                System.err.println("Missing fields in project data.");
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "All fields are required.");
                return;
            }

            projectDAO.addProject(nom, description, dateDebut, dateFin, budget);
            System.out.println("Project successfully added. Redirecting...");
            request.setAttribute("message", "Project added successfully!");
            response.sendRedirect(request.getContextPath() + "/projects");
            //request.getRequestDispatcher("/Projects/projects.jsp").forward(request, response);

        } catch (SQLException e) {
            System.err.println("SQL Exception in AddProjectServlet: " + e.getMessage());
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to add project due to a database error.");
        } catch (Exception e) {
            System.err.println("Unexpected error in AddProjectServlet: " + e.getMessage());
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An unexpected error occurred.");
        }
    }
}
