package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.User;

public interface UserDAO {
	
	public User selectByID(int noUtilisateur) throws BusinessException;
	
	public List<User> selectAll() throws BusinessException;
	
	public void delete(int noUtilisateur) throws BusinessException;
	
	public void insert(User user) throws BusinessException;
	
	public void update(User user) throws BusinessException;
	
// -------------------------- CONNEXION ------------------------
	
	public boolean connexion(String user, String mdp) throws BusinessException;
	
	public String tokenSeSouvenirDeMoi() throws BusinessException;
	
	public void deleteTokenSeSouvenirDeMoi(String pseudo, String token) throws BusinessException;
	
	public boolean isValidTokenSeSouvenirDeMoi(String pseudo, String token) throws BusinessException;

	public User selectByMail(String login) throws BusinessException;

	public User selectByPseudo(String login) throws BusinessException;
	
	public boolean forgotPassword(String mail) throws BusinessException;
}