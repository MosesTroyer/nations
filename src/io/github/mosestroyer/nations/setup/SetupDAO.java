package io.github.mosestroyer.nations.setup;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SetupDAO {

	public SetupDAO() {
		
	} //end constructor
	
	public void checkForTables(Connection c) throws SQLException{
		
		Statement stmt = c.createStatement();
		
		String sql = "CREATE TABLE IF NOT EXISTS test (ID INT PRIMARY KEY NOT NULL)";
		
		stmt.executeUpdate(sql);
		
		stmt.close();
		
	} //end checkForTables
	
} //end SetupDAO class
