package fr.eni.encheres.dal.jdbc;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.User;
import fr.eni.encheres.dal.CodesResultatDAL;
import fr.eni.encheres.dal.ConnectionProvider;
import fr.eni.encheres.dal.UserDAO;

public class UserDAOJdbcImpl implements UserDAO {

		private static final String SELECT_USER_ALL = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM UTILISATEURS";
		private static final String SELECT_BY_ID =	"SELECT * FROM UTILISATEURS WHERE no_utilisateur=?";
		private static final String SELECT_BY_MAIL = "SELECT * FROM UTILISATEURS WHERE EMAIL=?;";
		private static final String INSERT_USER = "insert into UTILISATEURS(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit) values(?,?,?,?,?,?,?,?,?,100);";
		private static final String DELETE_USER = "delete from UTILISATEURS where no_utilisateur=?";
		private static final String SELECT_BY_PSEUDO = "SELECT * FROM UTILISATEURS WHERE PSEUDO=?;";

		private static final String UPDATE_USER = 	"UPDATE utilisateurs SET pseudo=?, nom=?, prenom=?, email=?, telephone=?, rue=?, code_postal=?, ville=?, mot_de_passe=?"
				+ "WHERE no_utilisateur=?";
		//---------------------------------------------- CONNEXION -----------------------------------------------------------------
		private static final String CONNECT_USER = "SELECT mot_de_passe FROM utilisateurs WHERE pseudo = ?";
		private static final String INSERT_TOKEN = "INSERT INTO token_user (token, no_utilisateur) values (?, ?);";
		private static final String GET_TOKEN = "SELECT token FROM token_user WHERE token = ? AND expiry_date > NOW()";
		private static final String DELETE_TOKEN = "DELETE * FROM token_user WHERE token = ?";

		
		public void insert(User user) throws BusinessException {
			if(user==null)
			{
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJECT_NULL);
				throw businessException;
			}
			
			try(Connection cnx = ConnectionProvider.getConnection())
			{
				try
				{
					cnx.setAutoCommit(false);
					PreparedStatement pstmt;
					pstmt = cnx.prepareStatement(INSERT_USER, PreparedStatement.RETURN_GENERATED_KEYS);
					pstmt.setString(1, user.getPseudo());
					pstmt.setString(2, user.getNom());
					pstmt.setString(3, user.getPrenom());
					pstmt.setString(4, user.getEmail());
					pstmt.setString(5, user.getTel());
					pstmt.setString(6, user.getRue());
					pstmt.setString(7, user.getCodePostale());
					pstmt.setString(8, user.getVille());
					pstmt.setString(9, user.getMdp());
					pstmt.executeUpdate();

					try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
					    if (generatedKeys.next()) {
					        user.setNoUtilisateur(generatedKeys.getInt(1)); // récupère et assigne l'ID généré
					    } else {
					        throw new SQLException("La création de l'utilisateur a échoué, pas d'ID obtenu.");
					    }
					}
					
					pstmt.close();
					cnx.commit();
				}
				catch(Exception e)
				{
					e.printStackTrace();
					cnx.rollback();
					throw e;
					
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(CodesResultatDAL.INSERT_USER_ECHEC);
				throw businessException;
			}
			
		}

		public void delete(int id) throws BusinessException {
			if(id == 0) {
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(CodesResultatDAL.DELETE_OBJECT_NULL);
				throw businessException;
			}
			
			try(Connection cnx = ConnectionProvider.getConnection())
			{
				PreparedStatement pstmt = cnx.prepareStatement(DELETE_USER);
				pstmt.setInt(1, id);
				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(CodesResultatDAL.DELETE_USER_ECHEC);
				throw businessException;
			}
			
		}

		public List<User> selectAll() throws BusinessException {
			List<User> users = new ArrayList<User>();
			try(Connection cnx = ConnectionProvider.getConnection())
			{
				PreparedStatement pstmt = cnx.prepareStatement(SELECT_USER_ALL);
				ResultSet rs = pstmt.executeQuery();
				while(rs.next())
				{

					//pseudo, nom, prenom, email, telephone, rue, code_postale, ville, credit, administrateur
					users.add(new User(	rs.getString("pseudo"), 
										rs.getString("nom"), 
										rs.getString("prenom"), 
										rs.getString("email"), 
										rs.getString("telephone"), 
										rs.getString("rue"), 
										rs.getString("code_postal"), 
										rs.getString("ville"),  
										rs.getString("mot_de_passe")));

				}
			}
			catch(Exception e){
				e.printStackTrace();
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(CodesResultatDAL.SELECT_ALL_ECHEC);
				throw businessException;
			}
			return users;
		}

		public User selectByID(int id) throws BusinessException {
			if (id == 0) {
				BusinessException bE = new BusinessException();
				bE.ajouterErreur(CodesResultatDAL.SELECT_OBJECT_NULL);
				throw bE;
			}
			
			User user = new User();
			try(Connection cnx = ConnectionProvider.getConnection())
			{
				PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID);
				pstmt.setInt(1, id);
				ResultSet rs = pstmt.executeQuery();
				rs.next();
				String pseudo = rs.getString("pseudo");
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String email = rs.getString("email");
				String telephone = rs.getString("telephone");
				String rue = rs.getString("rue");
				String cp = rs.getString("code_postal");
				String ville = rs.getString("ville");
				String mdp = rs.getString("mdp");
				user = new User(pseudo,nom, prenom, email, telephone, rue, cp, ville, mdp);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(CodesResultatDAL.SELECT_USER_ECHEC);
				throw businessException;
			}
			
			if(user.getNoUtilisateur()==0) {
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(CodesResultatDAL.LECTURE_USER_INEXISTANT);
				throw businessException;
			}
			return user;
			
		}

		@Override
		public void update(User user) throws BusinessException {
			if (user == null) {
				BusinessException bE = new BusinessException();
				bE.ajouterErreur(CodesResultatDAL.UPDATE_OBJECT_NULL);
				throw bE;
			}
			
			try(Connection cnx = ConnectionProvider.getConnection()) {
				try {
					cnx.setAutoCommit(false);
					PreparedStatement pstmt;
					ResultSet rs;
					
					pstmt = cnx.prepareStatement(UPDATE_USER, PreparedStatement.RETURN_GENERATED_KEYS);
					pstmt.setString(1, user.getPseudo());
					pstmt.setString(2, user.getNom());
					pstmt.setString(3, user.getPrenom());
					pstmt.setString(4, user.getEmail());
					pstmt.setString(5, user.getTel());
					pstmt.setString(6, user.getRue());
					pstmt.setString(7, user.getCodePostale());
					pstmt.setString(8, user.getVille());
					pstmt.setString(9, user.getMdp());
					pstmt.setInt(10, user.getNoUtilisateur());
					pstmt.executeUpdate();
					//int rowsAffected = pstmt.executeUpdate();
		            //if (rowsAffected == 0) {
		               //throw new SQLException("La mise à jour n'a affecté aucune ligne.");
		            //}

		            cnx.commit();
					
				} catch(Exception e) {
					e.printStackTrace();
					cnx.rollback();
					throw e;
				}
				
			} catch(Exception e) {
				e.printStackTrace();
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(CodesResultatDAL.UPDATE_USER_ECHEC);
				throw businessException;
			}
		}

		@Override
		 public boolean connexion(String pseudo, String password) throws BusinessException {
	        boolean isValidUser = false;
	        try (Connection cnx = ConnectionProvider.getConnection()) {
	        	
	            try (PreparedStatement pstmt = cnx.prepareStatement(CONNECT_USER)) {
	                pstmt.setString(1, pseudo);
	                
	                try (ResultSet rs = pstmt.executeQuery()) {
	                	
	                    if (rs.next()) {
	                        String storedPassword = rs.getString("mot_de_passe");
	                        isValidUser = password.equals(storedPassword); // Comparez les mots de passe
	                    }
	                }
	            }
	        } catch (SQLException e) {
	        	e.printStackTrace();
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(CodesResultatDAL.CONNECT_USER_ECHEC);
				throw businessException;
	        }
	        return isValidUser;
	    }

		
		@Override
		public String tokenSeSouvenirDeMoi(String pseudo) throws BusinessException {
			String token = null;
			User user = selectByPseudo(pseudo);
			
			try(Connection cnx = ConnectionProvider.getConnection()) {
				PreparedStatement psmt = cnx.prepareStatement(INSERT_TOKEN);
				
				SecureRandom random = new SecureRandom();
		        byte[] bytes = new byte[64]; // 64 bytes = 512 bits
		        random.nextBytes(bytes);
		        token = Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
		        
		        psmt.setString(1, token);
		        psmt.setInt(2, user.getNoUtilisateur());
		        
			} catch (Exception e) {
				e.printStackTrace();
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(CodesResultatDAL.TOKEN_NOT_CREATED);
				throw businessException;
			}

	        return token;
	    }

		@Override
		public boolean isValidTokenSeSouvenirDeMoi(String pseudo, String token) throws BusinessException {
			
			try(Connection cnx = ConnectionProvider.getConnection()) {
				PreparedStatement psmt = cnx.prepareStatement(GET_TOKEN);
				psmt.setString(1, pseudo);
				
				try (ResultSet resultSet = psmt.executeQuery()) {
	                // Si le token existe et n'est pas expiré, la méthode renvoie true
	                return resultSet.next();
	            }
				
			} catch (Exception e) {
				e.printStackTrace();
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(CodesResultatDAL.TOKEN_NOT_FOUND);
				throw businessException;
			}
		}

		@Override
		public void deleteTokenSeSouvenirDeMoi(String pseudo, String token) throws BusinessException {
			
			try(Connection cnx = ConnectionProvider.getConnection()) {
				PreparedStatement psmt = cnx.prepareStatement(DELETE_TOKEN);
				psmt.setString(1, token);
				psmt.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(CodesResultatDAL.TOKEN_NOT_FOUND);
				throw businessException;
			}
			
		}

		
		public User selectByLogin(String requete, String login) {
			User user = null;
			try (Connection cnx = ConnectionProvider.getConnection();
					PreparedStatement pstmt = cnx.prepareStatement(requete)){
				pstmt.setString(1, login);
				
				try(ResultSet rs = pstmt.executeQuery()){
					if (rs.next()) {
						user = new User(rs.getInt("no_utilisateur"),rs.getString("pseudo"),rs.getString("nom"),rs.getString("prenom"),rs.getString("email"),rs.getString("telephone"),rs.getString("rue"),rs.getString("code_postal"),rs.getString("ville"),rs.getString("mot_de_passe"),rs.getInt("credit"),rs.getBoolean("administrateur"));
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return user;
		}

		@Override
		public User selectByPseudo(String login) {
			return selectByLogin(SELECT_BY_PSEUDO,login);
		}

		@Override
		public User selectByMail(String login) {
			return selectByLogin(SELECT_BY_MAIL, login);
		}

		@Override
		public boolean forgotPassword(String mail) throws BusinessException {			
			try (Connection cnx = ConnectionProvider.getConnection();
					PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_MAIL)){
				pstmt.setString(1, mail);
				
				try (ResultSet rs = pstmt.executeQuery()) {
		            if (rs.next()) {
		                // Mail trouvé en base de données
		                return true;
		            } else {
		                // Aucun mail similaire en base de données
		                return false;
		            }
		        }
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return false;
		}
}