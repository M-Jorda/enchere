package fr.eni.encheres.dal;

import fr.eni.encheres.NewVenteValidator;
import fr.eni.encheres.bll.ValidationCompte;
import fr.eni.encheres.dal.jdbc.ArticleDAOJdbcImpl;
import fr.eni.encheres.dal.jdbc.UserDAOJdbcImpl;
import fr.eni.encheres.dal.services.ForgotPasswordService;

public class DAOFactory {
	
    private static UserDAO userDAO;
    private static ArticleDAO articleDAO;
    private static NewArticleDAO newArticleDAO;
    private static ValidationCompte vCompte;

	
	public static ArticleDAO getArticleDAO() {
        if (articleDAO == null) {
            articleDAO = new ArticleDAOJdbcImpl();
        }
        return articleDAO;
    }

	public static UserDAO getUserDAO() {
        if (userDAO == null) {
            userDAO = new UserDAOJdbcImpl();
        }
        return userDAO;
    }
	
	public static NewArticleDAO getNewArticleDAO() {
        if (newArticleDAO == null) {
        	newArticleDAO = new NewVenteValidator();
        }
        return newArticleDAO;
    }
	
	public static ValidationCompte getValidationCompte() {
        if (vCompte == null) {
        	vCompte = new ValidationCompte();
        }
        return vCompte;
    }
}