package fr.eni.encheres.dal.services;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.dal.ForgotPassword;

public class ForgotPasswordService implements ForgotPassword {

    @Override
    public String MDPOublie(String email) throws BusinessException {
        try {
        	
            boolean resetSuccess = resetPassword(email);

            if (resetSuccess) {
                System.out.println("Mot de passe réinitialisé, veuillez consulter vos mails.");
            } else {
                throw new BusinessException();
            }
        } catch (BusinessException e) {
            System.err.println("Impossible de réinitialiser le mot de passe: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Une erreur est survenue: " + e.getMessage());
        }
        return "oui";
    }

    private boolean resetPassword(String userEmail) {
        
        return true;
    }

}
