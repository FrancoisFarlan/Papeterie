package fr.eni.papeterie.dal.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import fr.eni.papeterie.dal.Settings;

public class JdbcTools {

	private static Connection con;
	
	public static Connection getConnection() throws SQLException {
		if(con == null || con.isClosed()) {
			con = DriverManager.getConnection(Settings.getProperties("urldb"), Settings.getProperties("userdb"), Settings.getProperties("passworddb"));
		}
		return con;
	}
	

}
