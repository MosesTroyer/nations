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
	
	private final static String SELECT_PLAYER_CLASS = "SELECT class FROM PLAYERS WHERE id = ";
	
	private final static String INSERT_PLAYER_CLASS = "INSERT INTO PLAYERS (id, class) VALUES ";
	
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
	
	
	//TO ME: Change these when you actually have classes made so that it throws exception
	//if invalid class, whatever, so that people don't have fake classes that do nothing.
	public static String getPlayerClass(Connection c, UUID id) throws SQLException{
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery(SELECT_PLAYER_CLASS + "'" + id + "'");
		
		if(rs.next()){
			
			String ret = rs.getString("class");
			stmt.close();
			
			return ret;
		}
		
		stmt.close();
		return "";
	}//end getPlayerClass
	
	public static void setPlayerClass(Connection c, UUID id, String className) throws SQLException{
		Statement stmt = c.createStatement(); //create a statement to add to the database
		
		if(getPlayerClass(c, id).equals("")){ //get player class if they have none
			stmt.executeUpdate(INSERT_PLAYER_CLASS + "('" + id + "', '" + className + "')");
		}else{ //if they do have one, update the player class
			
			stmt.executeUpdate("UPDATE PLAYERS SET class = '" + className + "' WHERE id = '" + id + "' " );
		}
		
		stmt.close();
	}
	
	
} //end PlayerDAO class
