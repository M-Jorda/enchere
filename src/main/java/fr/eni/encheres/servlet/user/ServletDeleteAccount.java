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
import jakarta.servlet.http.HttpSession;


@WebServlet("/Connected/DeleteAccount")
public class ServletDeleteAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletDeleteAccount() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/JSP/AlterAccount.jsp").forward(request, response);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
            // Récupérer l'identifiant de l'utilisateur à supprimer depuis la requête
			User user = getUser(request);
			int noUser = user.getNoUtilisateur();
			 // Appeler la méthode delete du DAO
			EnchereManager eManager = EnchereManager.getInstance();
			eManager.deleteUser(noUser);
			
			response.sendRedirect("HomeVisitor.jsp");


        } catch (BusinessException e) {
            e.printStackTrace();
        }
	}
	public User getUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
	    User user = (User) session.getAttribute("user");
		return user;
	}

}
