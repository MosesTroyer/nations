package io.github.mosestroyer.nations.setup;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SetupDAO {
	
	private static String INSERT_NATION = "INSERT INTO nationsColor (name, color) VALUES ";
	
	private static String DELETE_NATION_FROM_NATIONSCOLOR = "DELETE FROM nationsColor WHERE name = ";
	
	public static void checkForTables(Connection c) throws SQLException{
		
		Statement stmt = c.createStatement();
		
		String sql = "CREATE TABLE IF NOT EXISTS nationsColor (name TEXT PRIMARY KEY NOT NULL, color TEXT)";
		stmt.executeUpdate(sql);
		
		sql = "CREATE TABLE IF NOT EXISTS players (id TEXT PRIMARY KEY NOT NULL, nation TEXT)";
		stmt.executeUpdate(sql);
		
		sql = "CREATE TABLE IF NOT EXISTS pedestals (name TEXT, position INTEGER, flag TEXT)";
		stmt.executeUpdate(sql);
		
		stmt.close();
		
	} //end checkForTables
	
	public static void createNation(Connection c, String name, String color) throws SQLException {
		
		Statement stmt = c.createStatement();
		String sql = INSERT_NATION + "('" + name + "', '" + color + "');";
		stmt.executeUpdate(sql);
		stmt.close();
		
	} //end createNation
	
	public static void removeNation(Connection c, String name) throws SQLException {
		Statement stmt = c.createStatement();
		stmt.executeUpdate(DELETE_NATION_FROM_NATIONSCOLOR + "'" + name + "'");
		
		
		//REMOVE FROM ALL TABLES HERE
		stmt.close();
	} //end removeNation
	
} //end SetupDAO class
