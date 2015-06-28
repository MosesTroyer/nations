package io.github.mosestroyer.nations.setup;

import io.github.mosestroyer.nations.nation.NationDAO;
import io.github.mosestroyer.nations.nation.Pedestal;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SetupDAO {
	
	private static String INSERT_NATION = "INSERT INTO nationsColor (name, color) VALUES ";
	
	private static String INSERT_PEDESTAL = "INSERT INTO pedestals (name, position, x, y, z, flag) VALUES ";
	
	private static String DELETE_NATION_FROM_NATIONSCOLOR = "DELETE FROM nationsColor ";
	
	private static String DELETE_NATION_FROM_PEDESTALS = "DELETE FROM pedestals ";
	
	private static String WHERE_NAME = "WHERE name = ";
	
	private static String WHERE_FLAG = "WHERE flag = ";
	
	public static void checkForTables(Connection c) throws SQLException{
		
		Statement stmt = c.createStatement();
		
		String sql = "CREATE TABLE IF NOT EXISTS nationsColor (name TEXT PRIMARY KEY NOT NULL, color TEXT)";
		stmt.executeUpdate(sql);
		
		sql = "CREATE TABLE IF NOT EXISTS players (id TEXT PRIMARY KEY NOT NULL, nation TEXT, class TEXT)";
		stmt.executeUpdate(sql);
		
		sql = "CREATE TABLE IF NOT EXISTS pedestals (name TEXT, position INTEGER, x INTEGER, y INTEGER, z INTEGER, flag TEXT)";
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
		
		String flag = NationDAO.getColorByName(c, name);
		
		stmt.executeUpdate(DELETE_NATION_FROM_NATIONSCOLOR + WHERE_NAME + "'" + name + "'");
		
		stmt.executeUpdate(DELETE_NATION_FROM_NATIONSCOLOR + WHERE_FLAG + "'" + flag + "'");
		
		removePedestals(c, name);
		
		//REMOVE FROM ALL TABLES HERE
		stmt.close();
	} //end removeNation
	
	public static void removePedestals(Connection c, String name) throws SQLException {
		Statement stmt = c.createStatement();
		
		stmt.executeUpdate(DELETE_NATION_FROM_PEDESTALS + WHERE_NAME + "'" + name + "'");
		
		stmt.close();
	} //end removePedestals
	
	public static void insertFlagPosition(Connection c, Pedestal pedestal) throws SQLException{
		Statement stmt = c.createStatement();
		
		stmt.executeUpdate(INSERT_PEDESTAL + "('" + pedestal.getName() + "', '" + pedestal.getPosition() + "', '" + pedestal.getX() + "', '" + pedestal.getY() +"', '" + pedestal.getZ() +"', '" + pedestal.getFlag() + "')");
		
		stmt.close();
	} //end insertFlagPosition
	
} //end SetupDAO class
