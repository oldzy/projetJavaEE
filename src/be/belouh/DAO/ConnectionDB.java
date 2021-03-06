package be.belouh.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
	private static String url = "jdbc:oracle:thin:@char-oracle11.condorcet.be:1521:xe";
	private static String user = "exa4";
	private static String passwd = "elephantsflagadas";
	private static Connection connect = null;

	private ConnectionDB() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connect = DriverManager.getConnection(url, user, passwd);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (connect == null) {
			System.out.println("Base de donn�es innaccessible.");
		}
	}

	public static Connection getInstance() {
		if (connect == null) {
			new ConnectionDB();
		}
		return connect;
	}
}
