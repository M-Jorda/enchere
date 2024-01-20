package fr.eni.encheres.dal;

import fr.eni.encheres.BusinessException;

public interface ForgotPassword {

	public String MDPOublie(String email) throws BusinessException;
}
