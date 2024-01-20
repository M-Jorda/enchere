package fr.eni.encheres.servlet.user;

import java.io.IOException;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bll.EnchereManager;
import fr.eni.encheres.bo.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Visitor/ForgotPassword")
public class ServletForgotPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.getRequestDispatcher("/WEB-INF/JSP/ForgotPassword.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EnchereManager eManager = EnchereManager.getInstance();
		
        String mail = request.getParameter("email");
        
		try {
			User user = eManager.selectByMail(mail);
			
			eManager.forgotPassword(user.getEmail());
			//Send email for reinitialize password
			
			request.getRequestDispatcher("/WEB-INF/JSP/Connexion.jsp").forward(request, response);
			
		} catch (BusinessException e) {
			e.printStackTrace();
			//TODO Email non trouvé
			request.getRequestDispatcher("/WEB-INF/JSP/CreateAccount.jsp").forward(request, response);
		}
	}

}
