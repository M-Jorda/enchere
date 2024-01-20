package fr.eni.encheres.dal;

import java.util.Date;

import fr.eni.encheres.BusinessException;

public interface NewArticleDAO {

	public boolean validateNom(String nom) throws BusinessException;
	
	public boolean validateDescription(String des) throws BusinessException;
	
	public boolean validateDateDebutEnchere(Date date) throws BusinessException;
	
	public boolean validateDateFinEnchere(Date date) throws BusinessException;
	
	public boolean validatePrix(int prix) throws BusinessException;
	
	public boolean validateRue(String rue) throws BusinessException;
	
	public boolean validateCPostal(String codePostal) throws BusinessException;
	
	public boolean validateVille(String ville) throws BusinessException;
	
}
