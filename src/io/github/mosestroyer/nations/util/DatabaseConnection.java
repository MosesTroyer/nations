package io.github.mosestroyer.nations.util;

import java.sql.*;

public class DatabaseConnection {
	
	public DatabaseConnection(){
		
	} //end constructor

	public Connection getConnection() throws SQLException{
		
		Connection c = null;
		
		try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:nations.db");
		      		      
		      return c;
		} catch ( Exception e ) {}
		
		throw new SQLException();
	} //end getConnection
	
	public void closeConnection(Connection c) throws SQLException {
		
	} //end closeConnection
	
	public void checkForTables(Connection c) throws SQLException{
		
		Statement stmt = c.createStatement();
		
		String sql = "CREATE TABLE IF NOT EXISTS test (ID INT PRIMARY KEY NOT NULL)";
		
		stmt.executeUpdate(sql);
		
		stmt.close();
		
	} //end checkForTables
	
} //end DatabaseConnection class
