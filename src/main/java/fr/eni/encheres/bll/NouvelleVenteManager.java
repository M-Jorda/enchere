package fr.eni.encheres.bll;

import java.util.Date;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.NewArticleDAO;

public class NouvelleVenteManager {

	private NewArticleDAO newArticle = DAOFactory.getNewArticleDAO();
	private static NouvelleVenteManager instance;
	
	public NouvelleVenteManager() {}
	
	public static NouvelleVenteManager getInstance() {
		if (instance == null) {
			instance = new NouvelleVenteManager();
		}
		
		return instance;
	}
			
	public boolean validateNom(String nom) throws BusinessException {
		return newArticle.validateNom(nom);
	}
	
	public boolean validateDescription(String des) throws BusinessException {
		return newArticle.validateDescription(des);
	}
	
	public boolean validateDateDebutEnchere(Date date) throws BusinessException {
		return newArticle.validateDateDebutEnchere(date);
	}
	
	public boolean validateDateFinEnchere(Date date) throws BusinessException {
		return newArticle.validateDateFinEnchere(date);
	}
	
	public boolean validatePrix(int prix) throws BusinessException {
		return newArticle.validatePrix(prix);
	}
	
	public boolean validateRue(String rue) throws BusinessException {
		return newArticle.validateRue(rue);
	}
	
	public boolean validateCPostal(String codePostal) throws BusinessException {
		return newArticle.validateCPostal(codePostal);
	}
	
	public boolean validateVille(String ville) throws BusinessException {
		return newArticle.validateVille(ville);
	}
	
}
