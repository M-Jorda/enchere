package fr.eni.encheres.bll;

/**
 * Les codes disponibles sont entre 20000 et 29999
 */
public abstract class CodesResultatBLL {
	
	/**
	 * Echec le nom de l'article ne respecte pas les règles définies
	 */
	public static final int REGLE_ARTICLE_NOM_ERREUR=20000;
	/**
	 * Echec le nom de l'article ne respecte pas les règles définies
	 */
	public static final int REGLE_LISTE_NOM_ERREUR = 20001;
	public static final int CONNEXION_KO = 20002;
	public static final int PSEUDO_INCORRECT = 50008;
	public static final int NOM_INCORRECT = 50002;
	public static final int PRENOM_INCORRECT = 50001;
	public static final int MAIL_INCORRECT = 50003;
	public static final int TEL_INCORRECT = 50004;
	public static final int RUE_INCORRECT = 50005;
	public static final int CODE_POSTAL_INCORRECT = 50006;
	public static final int VILLE_INCORRECT = 50007;
	public static final int MDP_INCORRECT = 50009;
	
	public static final int USER_EXISTANT = 50010;
/* --------------------------------------------------------
 * 						NOUVELLE VENTE
-------------------------------------------------------- */
	
	public static final int NOM_TROP_LONG = 20100;
	public static final int DES_TROP_LONG = 20101;
	public static final int RUE_TROP_LONG = 20102;
	public static final int CPOSTAL_TROP_LONG = 20103;
	public static final int VILLE_TROP_LONG = 20104;
	
	public static final int DATE_DEBUT_ANTERIEUR = 20110;
	public static final int DATE_FIN_ANTERIEUR = 20111;
	
	public static final int PRIX_NEGATIF = 20121;
	
	public static final int PHOTO_NON_RECONNU = 20130;
	
	public static final int USER_INTROUVABLE = 20121;

	public static final int DES_OBLIGATOIRE = 20130;
	public static final int NOM_OBLIGATOIRE = 20131;

	public static final int CHAMP_NON_CORRECT = 20140;

}
