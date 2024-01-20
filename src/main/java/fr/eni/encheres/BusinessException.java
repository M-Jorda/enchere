package fr.eni.encheres;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Administrator
 *
 * Cette classe permet de recenser l'ensemble des erreurs (par leur code) pouvant survenir lors d'un traitement
 * quel que soit la couche à l'origine.
 */
public class BusinessException extends Exception {
	private static final long serialVersionUID = 1L;
	
	private List<Integer> listeCodesErreur;
	private List<String> errorMessages;
	
	public BusinessException() {
		super();
		this.listeCodesErreur=new ArrayList<>();
		 this.errorMessages = new ArrayList<>();
	}
	
	/**
	 * 
	 * @param code Code de l'erreur. 
	 * Doit avoir un message associé dans un fichier properties.
	 */
	public void ajouterErreur(int code)
	{
		if(!this.listeCodesErreur.contains(code))
		{
			this.listeCodesErreur.add(code);
		}
	}
	
	public void ajouterMessageErreur(String message) { // Méthode pour ajouter un message d'erreur
        if (message != null && !message.trim().isEmpty()) {
            this.errorMessages.add(message);
        }
    }
	
	public boolean hasErreurs()
	{
		return this.listeCodesErreur.size()>0;
	}
	
	public List<Integer> getListeCodesErreur()
	{
		return this.listeCodesErreur;
	}
	
	public List<String> getErrorMessages() { // Méthode pour récupérer les messages d'erreur
        return this.errorMessages;
    }

}