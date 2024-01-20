package fr.eni.encheres.servlet.user;

import java.io.IOException;

import fr.eni.encheres.bo.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/Connected/Profile")
public class ServletProfile extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        // Assurez-vous que l'objet User est présent dans la session
        if (user != null) {
            request.setAttribute("user", user);
            request.getRequestDispatcher("/WEB-INF/JSP/MyProfile.jsp").forward(request, response);
        } else {
            // Redirigez vers une page appropriée si l'utilisateur n'est pas connecté
            response.sendRedirect("/WEB-INF/JSP/HomeVisitor.jsp");
            return; // Ajoutez cette ligne pour terminer la méthode ici
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
}
