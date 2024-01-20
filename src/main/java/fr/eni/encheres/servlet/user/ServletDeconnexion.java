package fr.eni.encheres.servlet.user;

import java.io.IOException;

import fr.eni.encheres.bll.EnchereManager;
import fr.eni.encheres.bo.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/Connected/Deconnexion")
public class ServletDeconnexion extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String nom = user.getNom();
    	
    	String token = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("rememberMe".equals(cookie.getName())) {
                    token = cookie.getValue();
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                    break;
                }
            }
        }

        // Supprimez le token de la base de données
        if (token != null) {
            try {
                EnchereManager.getInstance().deleteTokenSeSouvenirDeMoi(nom, token);
            } catch (Exception e) {
                e.printStackTrace();
                // Gestion de l'erreur
            }
        }
        

        request.getRequestDispatcher("/WEB-INF/JSP/HomeVisitor.jsp").forward(request, response); // Modifier selon votre URL de connexion
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
}
