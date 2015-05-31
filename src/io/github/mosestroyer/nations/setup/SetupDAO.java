package io.github.mosestroyer.nations.setup;

import io.github.mosestroyer.nations.nation.Nation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SetupDAO {
	
	private static String SELECT_NATION = "SELECT * FROM nationsColor";
	
	private static String INSERT_NATION = "INSERT INTO nationsColor (name, color) VALUES ";
	
	private static String DELETE_NATION_FROM_NATIONSCOLOR = "DELETE FROM nationsColor WHERE name = ";
	
	public static void checkForTables(Connection c) throws SQLException{
		
		Statement stmt = c.createStatement();
		
		String sql = "CREATE TABLE IF NOT EXISTS nationsColor (name TEXT PRIMARY KEY NOT NULL, color TEXT)";
		stmt.executeUpdate(sql);
		
		stmt.close();
		
	} //end checkForTables
	
	public static Nation[] getNations(Connection c) throws SQLException {
		
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery(SELECT_NATION);
		
		ArrayList<Nation> NationsArrayList = new ArrayList<Nation>();
		while(rs.next()){
			NationsArrayList.add(new Nation(rs.getString("name"), rs.getString("color")));
		}
		
		stmt.close();
		
		Nation[] NationsArray = new Nation[NationsArrayList.size()];
		for(int i = 0; i < NationsArray.length; i++)
			NationsArray[i] = NationsArrayList.get(i);
			
		return NationsArray;
	} //end getNations
	
	public static void createNation(Connection c, String name, String color) throws SQLException {
		
		Statement stmt = c.createStatement();
		String sql = INSERT_NATION + "('" + name + "', '" + color + "');";
		stmt.executeUpdate(sql);
		stmt.close();
		
	} //end createNation
	
	public static void removeNation(Connection c, String name) throws SQLException {
		Statement stmt = c.createStatement();
		stmt.executeUpdate(DELETE_NATION_FROM_NATIONSCOLOR + "'" + name + "'");
		stmt.close();
		
		//REMOVE FROM ALL TABLES HERE
		
	} //end removeNation
	
} //end SetupDAO class
