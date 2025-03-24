package Servlets;
import DAO.TaskDAO;
import Models.Project;
import Models.Tache;
import java.io.IOException;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AddTaskServlet")
public class TaskServlet extends HttpServlet {


    private TaskDAO taskDAO;

    @Override
    public void init() throws ServletException {
        taskDAO = new TaskDAO();  // Initialize TaskDAO
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the form parameters from the request
        String description = request.getParameter("description");
        String dateDebut = request.getParameter("dateDebut");
        String dateFin = request.getParameter("dateFin");
        int projectId = Integer.parseInt(request.getParameter("projectId"));  // Assume you are passing the project ID

        // Create a Project object (you could also retrieve this from the database if needed)
        Project project = new Project();
        project.setId(projectId);

        // Call the DAO to add the task
        try {
            taskDAO.addTask( description, dateDebut, dateFin, project.getId());
            response.sendRedirect("taskSuccess.jsp");  // Redirect to a success page
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("errorPage.jsp");  // Redirect to an error page
        }
    }
}

