package io.github.mosestroyer.nations.setup;

import io.github.mosestroyer.nations.nation.ChestPedestal;
import io.github.mosestroyer.nations.nation.NationDAO;
import io.github.mosestroyer.nations.nation.Pedestal;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SetupDAO {
	
	private static final String INSERT_NATION = "INSERT INTO nationsColor (name, color) VALUES ";
	
	private static final String INSERT_PEDESTAL = "INSERT INTO pedestals (name, position, x, y, z, flag) VALUES ";
	
	private static final String INSERT_CHEST = "INSERT INTO chests (name, position, x, y, z) VALUES ";
	
	private static final String DELETE_NATION_FROM_NATIONSCOLOR = "DELETE FROM nationsColor ";
	
	private static final String DELETE_NATION_FROM_PEDESTALS = "DELETE FROM pedestals ";
	
	private static final String DELETE_NATION_IN_CHESTS = "DELETE FROM chests  ";
	
	private static final String WHERE_NAME = "WHERE name = ";
	
	private static final String WHERE_COLOR = "WHERE color = ";
	
	private static final String WHERE_FLAG = "WHERE flag = ";
	
	public static void checkForTables(Connection c) throws SQLException{
		
		Statement stmt = c.createStatement();
		
		String sql = "CREATE TABLE IF NOT EXISTS nationsColor (name TEXT PRIMARY KEY NOT NULL, color TEXT)";
		stmt.executeUpdate(sql);
		
		sql = "CREATE TABLE IF NOT EXISTS players (id TEXT PRIMARY KEY NOT NULL, nation TEXT, class TEXT)";
		stmt.executeUpdate(sql);
		
		sql = "CREATE TABLE IF NOT EXISTS pedestals (name TEXT, position INTEGER, x INTEGER, y INTEGER, z INTEGER, flag TEXT)";
		stmt.executeUpdate(sql);
		
		sql = "CREATE TABLE IF NOT EXISTS chests (name TEXT, position INTEGER, x INTEGER, y INTEGER, z INTEGER)";
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
		
		stmt.executeUpdate(DELETE_NATION_FROM_NATIONSCOLOR + WHERE_COLOR + "'" + flag + "'");
		
		stmt.close();
	} //end removeNation
	
	public static void removePedestals(Connection c, String name, String color) throws SQLException {
		Statement stmt = c.createStatement();
		
		stmt.executeUpdate(DELETE_NATION_IN_CHESTS + WHERE_NAME + "'" + name + "'");
		stmt.executeUpdate(DELETE_NATION_FROM_PEDESTALS + WHERE_NAME + "'" + name + "'");
		stmt.executeUpdate(DELETE_NATION_FROM_PEDESTALS + WHERE_FLAG + "'" + color + "'");
		
		stmt.close();
	} //end removePedestals
	
	public static void insertFlagPosition(Connection c, Pedestal pedestal) throws SQLException{
		Statement stmt = c.createStatement();
		
		stmt.executeUpdate(INSERT_PEDESTAL + "('" + pedestal.getName() + "', '" + pedestal.getPosition() + "', '" + pedestal.getX() + "', '" + pedestal.getY() +"', '" + pedestal.getZ() +"', '" + pedestal.getFlag() + "')");
		
		stmt.close();
	} //end insertFlagPosition
	
	public static void insertChestPosition(Connection c, ChestPedestal chestPedestal) throws SQLException {
		Statement stmt = c.createStatement();
		
		stmt.executeUpdate(INSERT_CHEST + "('" + chestPedestal.getName() + "', '" + chestPedestal.getPosition() + "', '" + chestPedestal.getX() + "', '" + chestPedestal.getY() +"', '" + chestPedestal.getZ() + "')");
		
		stmt.close();
	} //end insertChestPosition
	
} //end SetupDAO class
