package fr.eni.encheres.bll;

import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.User;

public class ValidationCompte {

	private static BusinessException be = new BusinessException();
	
	public static boolean isValidPseudo(String pseudo) throws BusinessException {
		EnchereManager eManager = EnchereManager.getInstance();
		
		
		try {
		    List<User> userList = eManager.selectAllUsers();

		    for (User user : userList) {
		        if (!user.getNom().equals(pseudo)) {
		            return true;
		        } else {
		        	be.ajouterErreur(CodesResultatBLL.PRENOM_INCORRECT);
					throw be;
		        }
		    }
		} catch (BusinessException e) {
		    
		    e.printStackTrace();
		}

		User user = new User();
		
		if (pseudo != null && pseudo.matches("^[a-zA-Z0-9\\p{Punct}]+$") && pseudo.length() <= 30) {
			return true;
        } else {
        	be.ajouterErreur(CodesResultatBLL.PSEUDO_INCORRECT);
			throw be;
        }
	}
	
	public static boolean isValidNom(String nom) throws BusinessException {
		if (nom != null && nom.matches("^[a-zA-Z]+$") && nom.length() <= 30) {
			return true;
        } else {
        	be.ajouterErreur(CodesResultatBLL.NOM_INCORRECT);
			throw be;
        }
	}
	
	public static boolean isValidPrenom(String prenom) throws BusinessException {
		if (prenom != null && prenom.matches("^[a-zA-Z]+$") && prenom.length() <= 30) {
			return true;
        } else {
        	be.ajouterErreur(CodesResultatBLL.PRENOM_INCORRECT);
			throw be;
        }
	}

	public static boolean isValidEmail(String email) throws BusinessException {
		if (email != null && email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$") && email.length() <= 50) {
			return true;
        } else {
        	be.ajouterErreur(CodesResultatBLL.MAIL_INCORRECT);
			throw be;
        }
	}
	
	public static boolean isValidTel(String tel) throws BusinessException {
		if (tel != null && tel.matches("^\\+?\\d{1,15}$")) {
			return true;
        } else {
        	be.ajouterErreur(CodesResultatBLL.TEL_INCORRECT);
			throw be;
        }
	}
	
	public static boolean isValidRue(String rue) throws BusinessException {
		if (rue != null && rue.matches("^[a-zA-Z0-9 ]{1,30}$")) {
			return true;
        } else {
        	be.ajouterErreur(CodesResultatBLL.RUE_INCORRECT);
			throw be;
        }
	}
	
	public static boolean isValidCPostal(String cPostal) throws BusinessException {
		if (cPostal != null && cPostal.matches("^(CEDEX)?\\d{1,10}$")) {
			return true;
        } else {
        	be.ajouterErreur(CodesResultatBLL.CODE_POSTAL_INCORRECT);
			throw be;
        }
	}
	
	public static boolean isValidVille(String ville) throws BusinessException {
		if (ville != null && ville.matches("^[\\p{L} .'-]{1,50}$")) {
			return true;
        } else {
        	be.ajouterErreur(CodesResultatBLL.VILLE_INCORRECT);
			throw be;
        }
	}
	
	public static boolean isValidMDP(String mdp) throws BusinessException {
		if (mdp != null && mdp.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,30}$")) {
			return true;
        } else {
        	be.ajouterErreur(CodesResultatBLL.MDP_INCORRECT);
			throw be;
        }
	}
}
