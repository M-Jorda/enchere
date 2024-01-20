package fr.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Article;
import fr.eni.encheres.dal.ArticleDAO;
import fr.eni.encheres.dal.CodesResultatDAL;
import fr.eni.encheres.dal.ConnectionProvider;

public class ArticleDAOJdbcImpl implements ArticleDAO {

	private static final String SELECT_BY_ID =	"SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie " +
			 									" FROM articles_vendus WHERE no_article = ?";
	
	private static final String SELECT_ALL = 	"SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie" + 
												" FROM articles_vendus";
	
	private static final String UPDATE_ARTICLE = 	"UPDATE utilisateurs SET no_article=?, nom_article=?, description=?, date_debut_encheres=?, date_fin_encheres=?, prix_initial=?, prix_vente=?, no_utilisateur=?, no_categorie=?" + 
													" WHERE no_article=?";
	
	private static final String INSERT_ARTICLE = 	"insert into ARTICLES_VENDUS(nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, no_utilisateur, no_categorie)" + 
													" values(?,?,?,?,?,?,?);";
	
	private static final String DELETE_ARTICLE = 	"delete from ARTICLES " +
													" where no_article=?";
	
	private static final String SELECT_BY_NAME = 	"SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie" +
													" FROM articles_vendus WHERE nom_article = ?";
	
	public Article selectByID(int id) throws BusinessException {
		if (id == 0) {
			BusinessException bE = new BusinessException();
			bE.ajouterErreur(CodesResultatDAL.SELECT_OBJECT_NULL);
			throw bE;
		}
		Article art = new Article();
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			boolean premiereLigne = true;
			
			while(rs.next()) {
				art.setNoArt(rs.getInt("no_article"));
				art.setNomArt(rs.getString("nom_article"));
				art.setDescription(rs.getString("description"));
				art.setDateDebutEncheres(rs.getDate("date_debut_encheres"));
				art.setDateFinEncheres(rs.getDate("date_fin_encheres"));
				art.setPrixInitial(rs.getInt("prix_initial"));
				art.setPrixVente(rs.getInt("prix_vente"));
				art.setNoUser(rs.getInt("no_utilisateur"));
				art.setNoCat(rs.getInt("no_categorie"));
			}
		}
		
		catch(Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SELECT_ARTICLE_ECHEC);
			throw businessException;
		}
		
		if(art.getNoArt()==0) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_ARTICLE_INEXISTANT);
			throw businessException;
		}
		
		return art;
	}
	
	public List<Article> selectAll() throws BusinessException {
		List<Article> listeArticles = new ArrayList<Article>();
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				listeArticles.add(new Article(	rs.getInt("no_article"),
												rs.getString("nom_article"), 
												rs.getString("description"), 
												rs.getDate("date_debut_enchere"),
												rs.getDate("date_fin_enchere"),
												rs.getInt("prix_initial"), 
												rs.getInt("prix_vente"), 
												rs.getInt("no_utilisateur"), 
												rs.getInt("no_categorie")));
			}
		}
		
		catch(Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SELECT_ALL_ECHEC);
			throw businessException;
		}
		return listeArticles;
	}
	
	public void update(Article art) throws BusinessException {
		if (art == null) {
			BusinessException bE = new BusinessException();
			bE.ajouterErreur(CodesResultatDAL.UPDATE_OBJECT_NULL);
			throw bE;
		}
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			try {
				cnx.setAutoCommit(false);
				PreparedStatement pstmt;
				ResultSet rs;
				
				pstmt = cnx.prepareStatement(UPDATE_ARTICLE, PreparedStatement.RETURN_GENERATED_KEYS);
				pstmt.setInt(1, art.getNoArt());
				pstmt.setString(2, art.getNomArt());
				pstmt.setString(3, art.getDescription());
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				pstmt.setDate(4, java.sql.Date.valueOf(art.getDateDebutEncheres().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));
				pstmt.setDate(5, java.sql.Date.valueOf(art.getDateFinEncheres().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));
				pstmt.setInt(6, art.getPrixInitial());
				pstmt.setInt(7, art.getPrixVente());
				pstmt.setInt(8, art.getNoUser());
				pstmt.setInt(9, art.getNoCat());
				pstmt.executeUpdate();
				rs = pstmt.getGeneratedKeys();
				
				if(rs.next()) {
					art.setNoArt(rs.getInt(1));
				}
				rs.close();
				pstmt.close();
				cnx.commit();
				
			} catch(Exception e) {
				e.printStackTrace();
				cnx.rollback();
				throw e;
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_ARTICLE_ECHEC);
			throw businessException;
		}
	}
	
	public void insert(Article art) throws BusinessException {
	    if (art == null) {
	        BusinessException businessException = new BusinessException();
	        businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJECT_NULL);
	        throw businessException;
	    }

	    try (Connection cnx = ConnectionProvider.getConnection()) {
	        try {
	            cnx.setAutoCommit(false);
	            PreparedStatement pstmt;
	            ResultSet rs;

	            pstmt = cnx.prepareStatement(INSERT_ARTICLE, PreparedStatement.RETURN_GENERATED_KEYS);
	            pstmt.setString(1, art.getNomArt());
	            pstmt.setString(2, art.getDescription());
	            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	            pstmt.setDate(3, java.sql.Date.valueOf(art.getDateDebutEncheres().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));
	            pstmt.setDate(4, java.sql.Date.valueOf(art.getDateFinEncheres().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));
	            pstmt.setInt(5, art.getPrixInitial());
	            pstmt.setInt(6, art.getNoUser());
	            pstmt.setInt(7, art.getNoCat());
	            pstmt.executeUpdate();
	            rs = pstmt.getGeneratedKeys();

	            if (rs.next()) {
	                art.setNoArt(rs.getInt(1));
	            }

	            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
	                if (generatedKeys.next()) {
	                    art.setNoArt(generatedKeys.getInt(1)); // récupère et assigne l'ID généré
	                } else {
	                    throw new SQLException("La création de l'utilisateur a échoué, pas d'ID obtenu.");
	                }
	            }

	            cnx.commit();
	        } catch (Exception e) {
	            e.printStackTrace();
	            cnx.rollback();
	            throw e;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        BusinessException businessException = new BusinessException();
	        businessException.ajouterErreur(CodesResultatDAL.INSERT_ARTICLE_ECHEC);
	        throw businessException;
	    }
	}


	public void delete(int id) throws BusinessException {
		if(id == 0) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.DELETE_OBJECT_NULL);
			throw businessException;
		}
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(DELETE_ARTICLE);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.DELETE_ARTICLE_ECHEC);
			throw businessException;
		}
	}
	
	public Article selectByName(String name) throws BusinessException {
		if(name == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SELECT_OBJECT_NULL);
			throw businessException;
		}
		
		Article art = new Article();
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_NAME);
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			boolean premiereLigne = true;
			
			while(rs.next()) {
				art.setNoArt(rs.getInt("no_article"));
				art.setNomArt(rs.getString("nom_article"));
				art.setDescription(rs.getString("description"));
				art.setDateDebutEncheres(rs.getDate("date_debut_encheres"));
				art.setDateFinEncheres(rs.getDate("date_fin_encheres"));
				art.setPrixInitial(rs.getInt("prix_initial"));
				art.setPrixVente(rs.getInt("prix_vente"));
				art.setNoUser(rs.getInt("no_utilisateur"));
				art.setNoCat(rs.getInt("no_categorie"));
			}
		}
		
		catch(Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SELECT_OBJECT_NULL);
			throw businessException;
		}
		
		if(art.getNoArt()==0) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SELECT_OBJECT_NULL);
			throw businessException;
		}
		
		return art;
		
	}
}