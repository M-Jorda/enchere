package fr.eni.encheres.servlet.user;

import java.io.IOException;

import fr.eni.encheres.bll.EnchereManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Connected/ForgotPassword")
public class ServletForgotPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EnchereManager eManager = EnchereManager.getInstance();
		
        String pseudo = request.getParameter("pseudo");
        String mdp = request.getParameter("mdp");

		eManager.MDPOublie();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
