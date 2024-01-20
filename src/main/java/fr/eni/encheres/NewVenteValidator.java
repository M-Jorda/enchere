package fr.eni.encheres;

import java.util.Date;

import fr.eni.encheres.bll.CodesResultatBLL;
import fr.eni.encheres.dal.NewArticleDAO;

public class NewVenteValidator implements NewArticleDAO {
	
	public static Date dateDebutEnchere = null;
	public static BusinessException bE = new BusinessException();

	public boolean validateNom(String nom) throws BusinessException {
		try {
			if (nom != null) {
				if (nom.length() <= 30) {
					return true;
				} else {
					bE.ajouterErreur(CodesResultatBLL.NOM_TROP_LONG);
				}
	        } else {
				bE.ajouterErreur(CodesResultatBLL.NOM_OBLIGATOIRE);
	        }
			
			
		}catch(Exception e) {
			e.printStackTrace();
			throw bE;
		}
		return false;
	}
	
	public boolean validateDescription(String des) throws BusinessException {
		try {
			if (des != null) {
				if (des.length() <= 300) {
					return true;
				} else {
					bE.ajouterErreur(CodesResultatBLL.DES_TROP_LONG);
				}
	        } else {
				bE.ajouterErreur(CodesResultatBLL.DES_OBLIGATOIRE);
	        }
			
			
		}catch(Exception e) {
			e.printStackTrace();
			throw bE;
		}
		return false;
	}
	
	public boolean validateDateDebutEnchere(Date date) throws BusinessException {
		try {
			Date today = new Date();
			dateDebutEnchere = date;
			if (!date.before(today)) {
				return true;
			} else {
				bE.ajouterErreur(CodesResultatBLL.DATE_DEBUT_ANTERIEUR);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			throw bE;
		}
		
		return false;
	}
	
	public boolean validateDateFinEnchere(Date date) throws BusinessException {
		try {
			if (date.before(dateDebutEnchere)) {
	            return true;
			} else {
				bE.ajouterErreur(CodesResultatBLL.DATE_FIN_ANTERIEUR);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			throw bE;
		}
		
		return false;
	}
	
	public boolean validatePrix(int prix) throws BusinessException {
		try {
			if (prix >= 0) {
	            return true;
			} else {
				bE.ajouterErreur(CodesResultatBLL.PRIX_NEGATIF);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			throw bE;
		}
		
		return false;
	}
	
	public boolean validateRue(String rue) throws BusinessException {
		try {
			if (rue.length() <= 30) {
	            return true;
			} else {
				bE.ajouterErreur(CodesResultatBLL.RUE_TROP_LONG);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			throw bE;
		}
		
		return false;
	}
	
	public boolean validateCPostal(String codePostal) throws BusinessException {
		try {
			if (codePostal.length() <= 10) {
	            return true;
			} else {
				bE.ajouterErreur(CodesResultatBLL.CPOSTAL_TROP_LONG);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			throw bE;
		}
		
		return false;
	}
	
	public boolean validateVille(String ville) throws BusinessException {
		try {
			if (ville.length() <= 50) {
	            return true;
			} else {
				bE.ajouterErreur(CodesResultatBLL.VILLE_TROP_LONG);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			throw bE;
		}
		
		return false;
	}	
}
