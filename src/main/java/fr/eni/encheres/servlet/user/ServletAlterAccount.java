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


@WebServlet("/Connected/AlterAccount")
public class ServletAlterAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        if (user != null) {
            request.setAttribute("user", user);
            request.getRequestDispatcher("/WEB-INF/JSP/AlterAccount.jsp").forward(request, response);
        } else {
            response.sendRedirect("/WEB-INF/JSP/HomeVisitor.jsp");
        }
     }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = getUser(request);
		int noUser = user.getNoUtilisateur();

		try {
			String pseudo = request.getParameter("pseudo");
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			String email = request.getParameter("email");
			String tel = request.getParameter("tel");
			String rue = request.getParameter("rue");
			String codePostal = request.getParameter("codePostal");
			String ville = request.getParameter("ville");
			String mdp = request.getParameter("mdp");
			String newMdp = request.getParameter("newMdp");
			String conf = request.getParameter("conf");
			
			String mdpConf;
			if (newMdp != "") {
				mdpConf = validateMDP(newMdp, conf);
			}else {
				mdpConf = mdp;
			}
	      
	
	        User userModified = new User(pseudo, nom, prenom, email, tel, rue, codePostal, ville, mdpConf, noUser);
	        EnchereManager eManager = EnchereManager.getInstance();
	        eManager.updateUser(userModified);
	        
	        try {
				if (eManager.connexion(pseudo, mdp) != null) {
				    request.getSession().setAttribute("user", userModified); // Enregistrement dans la session
				    request.getRequestDispatcher("/WEB-INF/JSP/HomeVisitor.jsp").forward(request, response);
				} 
			} catch (BusinessException e) {
				e.printStackTrace();
		
			}
		 }catch (BusinessException e) {
		 log("Erreur des modifications de l'utilisateur : ", e);
		 request.setAttribute("errorMessage", e.getMessage());
		 request.getRequestDispatcher("/WEB-INF/JSP/AlterAccount.jsp").forward(request, response);
		 }
	}


	private String validateMDP(String newMdp, String conf) {
	    if (!newMdp.equals(conf)) {
	        throw new IllegalArgumentException("La confirmation du nouveau mot de passe ne correspond pas.");
	    }
	    return newMdp;
	}
	
	public User getUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
	    User user = (User) session.getAttribute("user");
		return user;
	}
}


