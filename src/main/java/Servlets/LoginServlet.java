package Servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import DAO.UserDAO;
import Models.User;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email"); // Changed to email
        String motDePasse = request.getParameter("motDePasse");

        // Use the existing Login method to verify credentials
        int id = UserDAO.Login(email, motDePasse);

        if (id != 0) { // User found
            User user = new User(id, null, email, motDePasse); // Store email instead of nom

            // Store the user object in the session
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            response.sendRedirect("Home.jsp"); // Redirect to Dashboard
        } else {
            response.sendRedirect("index.jsp?error=1"); // Redirect with error
        }
    }
}
