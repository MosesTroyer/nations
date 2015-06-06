package io.github.mosestroyer.nations.playerActions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

public class PlayerDAO {
	
	private final static String SELECT_PLAYER_NATION = "SELECT * FROM PLAYERS WHERE id = ";
	
	private final static String INSERT_PLAYER_NATION = "INSERT INTO PLAYERS (id, nation) VALUES ";
	
	private final static String DELETE_PLAYER_NATION = "DELETE FROM PLAYERS WHERE id = ";
	
	public static String getPlayerNation(Connection c, UUID id) throws SQLException {
		
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery(SELECT_PLAYER_NATION + "'" + id + "'");
		
		if(rs.next()){
			String ret = rs.getString("nation");
			stmt.close();
			return ret;
		}
		
		stmt.close();
		return "";
	} //end getPlayerNation
	
	public static void setPlayerNation(Connection c, UUID id, String nationName) throws SQLException {
		Statement stmt = c.createStatement();
		stmt.executeUpdate(INSERT_PLAYER_NATION + "('" + id + "', '" + nationName + "')");
		
		stmt.close();
	} //end setPlayerNation 

	public static void leaveNation(Connection c, UUID id) throws SQLException {
		Statement stmt = c.createStatement();
		stmt.executeUpdate(DELETE_PLAYER_NATION + "'" + id + "'");
		
		stmt.close();
	} //end leaveNation
	
} //end PlayerDAO class
