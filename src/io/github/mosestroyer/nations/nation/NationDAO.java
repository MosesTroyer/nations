package io.github.mosestroyer.nations.nation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class NationDAO {
	
	private static String SELECT_NATION = "SELECT * FROM nationsColor ";
	private static String SELECT_COLOR = "SELECT color FROM nationsColor ";
	private static String WHERE_NAME = "WHERE name = ";

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
	
	public static Nation getNationByName(Connection c, String name) throws SQLException {
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery(SELECT_NATION + WHERE_NAME + "'" + name + "'");
		
		if(rs.next()){
			Nation ret = new Nation(rs.getString("name"), rs.getString("color"));
			stmt.close();
			return ret;
		}
		
		stmt.close();
		throw new SQLException("Nation does not exist!");
	} //end getNationByName
	
	public static String getColorByName(Connection c, String name) throws SQLException {
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery(SELECT_COLOR + WHERE_NAME + "'" + name + "'");
		
		if(rs.next()){
			return rs.getString("color");
		}
		
		throw new SQLException("Nation does not exist!");
	} //end getColorByName

} //end NationDAO class
