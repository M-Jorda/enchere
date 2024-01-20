package fr.eni.encheres.bll;

import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.User;
import fr.eni.encheres.dal.ArticleDAO;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.ForgotPassword;
import fr.eni.encheres.dal.UserDAO;
import fr.eni.encheres.dal.services.ForgotPasswordService;



public class EnchereManager {
	
	private ArticleDAO artDAO = DAOFactory.getArticleDAO();
	private UserDAO userDAO = DAOFactory.getUserDAO();
	private ValidationCompte vCompte = DAOFactory.getValidationCompte();
	private static EnchereManager instance;
	
	public EnchereManager() {}
	
	public static EnchereManager getInstance() {
		if (instance == null) {
			instance = new EnchereManager();
		}
		
		return instance;
	}
	
/*------------------------------------------------------
 * 							USER
 -----------------------------------------------------*/
	
	public User selectUserByID(int noUtilisateur) throws BusinessException {
		return userDAO.selectByID(noUtilisateur);
	}
	
	public List<User> selectAllUsers() throws BusinessException {
		return userDAO.selectAll();
	}
	
	public void deleteUser(int noUtilisateur) throws BusinessException {
		userDAO.delete(noUtilisateur);
	}
	
	public void insertUser(User user) throws BusinessException {
        if (user == null) {
            throw new BusinessException();
        }
        userDAO.insert(user);
    }
    
    public void updateUser(User user) throws BusinessException {
        if (user == null) {
            throw new BusinessException()
;
        }
        userDAO.update(user);

    }
    
    public User selectByMail(String login) throws BusinessException {
    	return userDAO.selectByMail(login);
    }

	public User selectByPseudo(String login) throws BusinessException {
		return userDAO.selectByPseudo(login);
	}
	
	public boolean forgotPassword(String mail) throws BusinessException {
		return userDAO.forgotPassword(mail);
	}

/*
 *  ---------------CREATION COMPTE ---------------
 */
	
	public boolean ValidationCompte(User user) throws BusinessException {
		if (!vCompte.isValidPseudo(user.getPseudo())) {
            throw new BusinessException();
        }

        if (!vCompte.isValidNom(user.getNom())) {
            throw new BusinessException();
        }

        if (!vCompte.isValidPrenom(user.getPrenom())) {
            throw new BusinessException();
        }

        if (!vCompte.isValidEmail(user.getEmail())) {
            throw new BusinessException();
        }

        if (!vCompte.isValidTel(user.getTel())) {
            throw new BusinessException();
        }
        
        if (!vCompte.isValidRue(user.getRue())) {
            throw new BusinessException();
        }
        
        if (!vCompte.isValidCPostal(user.getCodePostale())) {
            throw new BusinessException();
        }
        
        if (!vCompte.isValidVille(user.getVille())) {
            throw new BusinessException();
        }
        
        if (!vCompte.isValidMDP(user.getMdp())) {
            throw new BusinessException();
        }
        
        return true;
	}
	
/*
 * --------------- CONNEXION ------------------
 */
    
	public User connexion(String login, String mdp) throws BusinessException {
		User user = null;
		if (login.contains("@")) {
			user = userDAO.selectByMail(login);
		} else {
			user = userDAO.selectByPseudo (login);
		}
		if (user == null) {
			BusinessException be= new BusinessException();
			be.ajouterErreur(CodesResultatBLL.CONNEXION_KO);
		throw be;
		} else {
			if (!user.getMdp().equals(mdp)) {
				BusinessException be= new BusinessException();
				be.ajouterErreur(CodesResultatBLL.CONNEXION_KO);
			throw be;
			}
		
		}
		return user;
	}
	
	public String tokenSeSouvenirDeMoi() throws BusinessException {
		return userDAO.tokenSeSouvenirDeMoi();
	}
	
	public void deleteTokenSeSouvenirDeMoi(String pseudo, String token) throws BusinessException {
		if (pseudo == null) {
			throw new BusinessException();
		}
		userDAO.deleteTokenSeSouvenirDeMoi(pseudo, token);
	}

	public boolean isValidTokenSeSouvenirDeMoi(String pseudo, String token) throws BusinessException {
		return userDAO.isValidTokenSeSouvenirDeMoi(pseudo, token);
	}
	

/*------------------------------------------------------
 * 							ARTICLE
 -----------------------------------------------------*/
	
	public Article selectArticleByID(int noArt) throws BusinessException {
		return artDAO.selectByID(noArt);
	}
	
	public List<Article> selectAllArticles() throws BusinessException {
		return artDAO.selectAll();
	}
	
	public void deleteArticle(int noArt) throws BusinessException {
		artDAO.delete(noArt);
	}
	
    public void insertArticle(Article article) throws BusinessException {
        if (article == null) {
            throw new BusinessException();
        }
        artDAO.insert(article);
    }
    
    public void updateArticle(Article article) throws BusinessException {
        if (article == null) {
            throw new BusinessException();
        }
        artDAO.update(article);
    }
    
    public Article selectArticleByName(String name) throws BusinessException {
        return artDAO.selectByName(name);
    }
	

}
