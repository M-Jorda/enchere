package fr.eni.encheres.servlet.user.seller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import fr.eni.encheres.bll.EnchereManager;
import fr.eni.encheres.bll.NouvelleVenteManager;
import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/Connected/newSell")
public class ServletNewSell extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        //Vérifie si le user est connecté
        if (user != null) {
            request.setAttribute("user", user);
            request.getRequestDispatcher("/WEB-INF/JSP/NewSell.jsp").forward(request, response);
        } else {
            response.sendRedirect("/WEB-INF/JSP/HomeVisitor.jsp");
        }
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			// Appel des managers
			EnchereManager eManager = EnchereManager.getInstance();
			NouvelleVenteManager newVente = NouvelleVenteManager.getInstance();
			
			// Préparation du formatage de la date
			String pattern = "dd-MM-yyyy";
	        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
	        
	        //récupération de l'id deu User
			User user = getUser(request);
			int noUser = user.getNoUtilisateur();
			
			//Récupération des champs du formulaire
			String nom = request.getParameter("article");
			String description = request.getParameter("description");
			int categorie = Integer.valueOf(request.getParameter("categorie"));
			Integer prix = Integer.valueOf(request.getParameter("prix"));
			Date debutEnchere = (Date) dateFormat.parse(request.getParameter("debutEnchere"));
			Date finEnchere = (Date) dateFormat.parse(request.getParameter("finEnchere"));
			String rue = request.getParameter("rue");
			String codePostal = request.getParameter("codePostal");
			String ville = request.getParameter("ville");
	        
			//Appelle des méthodes pour vérifier 
	        newVente.validateNom(nom);
	        newVente.validateDescription(description);
	        newVente.validatePrix(prix);
	        newVente.validateDateDebutEnchere(debutEnchere);
	        newVente.validateDateFinEnchere(finEnchere);
	        newVente.validateRue(rue);
	        newVente.validateCPostal(codePostal);
	        newVente.validateVille(ville);
	        
			//Création d'un objet
			Article art = new Article(nom, description, debutEnchere, finEnchere, prix, noUser, categorie);
			
			try { 
				//si le formulaire d'adresse de livraison est inchangé, ou vide, ne change rien, sinon change dans la BDD
				if (	(!rue.equals(user.getRue()) || !rue.isEmpty()) || 
						(!codePostal.equals(user.getCodePostale()) || !codePostal.isEmpty()) || 
						(!ville.equals(user.getVille()) || !ville.isEmpty())) {
					
					User updatedUser = new User(rue, codePostal, ville);
					eManager.updateUser(updatedUser);
				}
				
				//Insertion dans la bdd de l'objet
				eManager.insertArticle(art);
			    request.getRequestDispatcher("/WEB-INF/JSP/Home.jsp").forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
				
	            request.getRequestDispatcher("/WEB-INF/JSP/HomeVisitor.jsp").forward(request, response);

			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(request.getParameter("categorie"));
            request.getRequestDispatcher("/WEB-INF/JSP/HomeVisitor.jsp").forward(request, response);

		}
		
		
	}
	
	public User getUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
	    User user = (User) session.getAttribute("user");
		return user;
	}
}
