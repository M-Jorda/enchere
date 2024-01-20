package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Article;

public interface ArticleDAO {
	public Article selectByID(int noArt) throws BusinessException;
	
	public List<Article> selectAll() throws BusinessException;
	
	public void delete(int noArt) throws BusinessException;
	
	public void insert(Article article) throws BusinessException;
	
	public void update(Article article) throws BusinessException;
	
	public Article selectByName(String name) throws BusinessException;
	
}