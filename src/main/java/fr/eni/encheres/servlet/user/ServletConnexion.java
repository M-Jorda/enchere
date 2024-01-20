package fr.eni.encheres.servlet.user;

import java.io.IOException;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bll.EnchereManager;
import fr.eni.encheres.bo.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Visitor/Connexion")
public class ServletConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/JSP/Connexion.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pseudo = request.getParameter("pseudo");
        String mdp = request.getParameter("mdp");
        String remember = request.getParameter("SeSouvenirDeMoi");
        
        
        boolean isRememberMeChecked = remember != null && remember.equals("on");

        
        if (isRememberMeChecked) {
        	String token = null;
			try {
				token = EnchereManager.getInstance().tokenSeSouvenirDeMoi();
				Cookie rememberMeCookie = new Cookie("rememberMe", token);
	        	response.addCookie(rememberMeCookie);
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
        }
        
        try {
        	User user = EnchereManager.getInstance().connexion(pseudo, mdp);
		
			    request.getSession().setAttribute("user", user); // Enregistrement dans la session
				request.getRequestDispatcher("/WEB-INF/JSP/Home.jsp").forward(request, response);
			
		} catch (BusinessException e) {
			
			request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
			request.getRequestDispatcher("/WEB-INF/JSP/Connexion.jsp").forward(request, response);

			
		}
    }	
}
