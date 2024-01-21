package fr.eni.encheres.servlet.visitor;


import java.io.IOException;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bll.EnchereManager;
import fr.eni.encheres.bo.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Visitor/CreateAccount")
public class ServletCreateAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/JSP/CreateAccount.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            EnchereManager eManager = EnchereManager.getInstance();


            String pseudo = request.getParameter("pseudo");
    		String nom = request.getParameter("nom");
    		String prenom = request.getParameter("prenom");
    		String email = request.getParameter("email");
    		String tel = request.getParameter("tel");
    		String rue = request.getParameter("rue");
    		String codePostal = request.getParameter("codePostal");
    		String ville = request.getParameter("ville");
    		String mdp = request.getParameter("mdp");
    		String conf = request.getParameter("conf");
            String mdpConf = validateMDP(mdp, conf);
            
            
            
            User user = new User(pseudo, nom, prenom, email, tel, rue, codePostal, ville, mdpConf);
            
            if (eManager.ValidationCompte(user)) {
            	eManager.insertUser(user);
            }
            
            // Insertion dans la base de données
            
            try {
    			if (eManager.connexion(pseudo, mdp) != null) {
    			    request.getSession().setAttribute("user", user); // Enregistrement dans la session
    			    request.getRequestDispatcher("/WEB-INF/JSP/Home.jsp").forward(request, response);
    			} else {
    			    request.setAttribute("errorMessage", "Identifiant ou mot de passe incorrect");
    				request.getRequestDispatcher("/WEB-INF/JSP/HomeVisitor.jsp").forward(request, response);
    			}
    		} catch (BusinessException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();

    			
    		}
            // Redirection ou affichage d'un message de succès
            request.getRequestDispatcher("/WEB-INF/JSP/Home.jsp").forward(request, response);
        }
            catch (BusinessException e) {
    			request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
                request.getRequestDispatcher("/WEB-INF/JSP/CreateAccount.jsp").forward(request, response);
            }
    }


    private String validateMDP(String mdp, String conf) {
        if (!mdp.equals(conf)) {
            throw new IllegalArgumentException("Les mots de passe ne correspondent pas.");
        }
        return mdp;
    }
}
