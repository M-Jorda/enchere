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
			String pseudo = notEmptyField(request.getParameter("pseudo"),user.getPseudo());
			String nom = notEmptyField(request.getParameter("nom"),user.getNom());
			String prenom = notEmptyField(request.getParameter("prenom"),user.getPrenom());
			String email = notEmptyField(request.getParameter("email"),user.getEmail());
			String tel = notEmptyField(request.getParameter("tel"),user.getTel());
			String rue = notEmptyField(request.getParameter("rue"),user.getRue());
			String codePostal = notEmptyField(request.getParameter("codePostal"),user.getCodePostale());
			String ville = notEmptyField(request.getParameter("ville"),user.getVille());
			String mdp = notEmptyField(request.getParameter("mdp"),user.getMdp());
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
				    request.getRequestDispatcher("/WEB-INF/JSP/Home.jsp").forward(request, response);
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
	
	public String notEmptyField(String field, String attribut) {
		if (field == null || field.isEmpty()) {
	        return attribut;
	    } else {
	        return field;
	    }
	}
}


