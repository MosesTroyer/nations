package io.github.mosestroyer.nations.util;

import java.sql.*;

public class DatabaseConnection {

	public static Connection getConnection() throws SQLException{
		
		Connection c = null;
		
		try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:nations.db");
		      		      
		      return c;
		} catch ( ClassNotFoundException e ) {
			
		}
		
		throw new SQLException();
	} //end getConnection
	
	public static void closeConnection(Connection c) throws SQLException {
		
		c.close();
		
	} //end closeConnection
	
	
} //end DatabaseConnection class
