package Servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import DAO.UserDAO;
import Model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import Models.User;

import java.io.IOException;
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {
        String nom = request.getParameter("nom");
        String password = request.getParameter("password");

        // Use the existing Login method to verify credentials
        int id = UserDAO.Login(nom, password);

        if (id != 0) { // User found
            // Now retrieve the full user details using the userId
            String role = UserDAO.FountTheUserRole(id); // Get the role based on the user ID
            User user = new User(id, nom, password, role); // Assuming User class has an appropriate constructor

            // Store the full user object in the session
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("role", role);

            response.sendRedirect("Dashboard.jsp"); // Redirect to Dashboard
        } else {
            response.sendRedirect("login.jsp?error=1"); // Redirect with error
        }
    }
}

