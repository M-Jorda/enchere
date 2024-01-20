package fr.eni.encheres.dal;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public abstract class ConnectionProvider {
	private static DataSource dtSrc;
	
	static {
		Context ctx;
		try {
			ctx = new InitialContext();
			ConnectionProvider.dtSrc = (DataSource) ctx.lookup("java:comp/env/jdbc/pool_cnx");
		} catch (NamingException e) {
			e.printStackTrace();
			throw new RuntimeException("impossible d'accéder à la BDD");
		}
	}
	
	public static Connection getConnection() throws SQLException {
		return ConnectionProvider.dtSrc.getConnection();
	}
	
}
