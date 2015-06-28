package io.github.mosestroyer.nations.nation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

public class NationDAO {
	
	private final static String SELECT_NATION = "SELECT * FROM nationsColor ";
	private final static String SELECT_COLOR = "SELECT color FROM nationsColor ";
	private final static String SELECT_PEDESTAL = "SELECT * FROM pedestals ";
	private final static String UPDATE_PEDESTAL = "UPDATE pedestals SET flag = ";
	private final static String WHERE_NAME = "WHERE name = ";
	private final static String WHERE_FLAG = "WHERE flag = ";
	private final static String WHERE_COLOR = "WHERE color = ";
	private final static String WHERE_X = "WHERE x = ";
	private final static String AND_Y = " AND y = ";
	private final static String AND_Z = " AND z = ";
	
	private final static String AND_POSITION = " AND position = ";

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
	
	public static String getNameByColor(Connection c, String color) throws SQLException {
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery(SELECT_NATION + WHERE_COLOR + "'" + color + "'");
		
		if(rs.next()){
			return rs.getString("name");
		}
		
		throw new SQLException("Nation does not exist!");
	} //end getNameByColor
	
	public static Pedestal getPedestal(Connection c, String name, int position) throws SQLException {
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery(SELECT_PEDESTAL + WHERE_NAME + "'" + name + "'" + AND_POSITION + "'" + position + "'");
		
		if(rs.next()){
			return new Pedestal(rs.getString("name"), rs.getInt("position"), rs.getInt("x"), rs.getInt("y"), rs.getInt("z"), rs.getString("flag"));
		}
		
		return null;
	} //end getPedestal
	
	//Only returns positions that have flags
	public static List<Pedestal> getPedestalPositions(Connection c, String name) throws SQLException {
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery(SELECT_PEDESTAL + WHERE_NAME + "'" + name + "'");
		
		List<Pedestal> pedestals = new ArrayList<Pedestal>();
		
		while(rs.next()){
			pedestals.add(new Pedestal(rs.getString("name"), rs.getInt("position"), rs.getInt("x"), rs.getInt("y"), rs.getInt("z"), rs.getString("flag")));
		}
		
		return pedestals;	
	} //end getPedestalPositions

	public static Pedestal findFlag(Connection c, String flag) throws SQLException {
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery(SELECT_PEDESTAL + WHERE_FLAG + "'" + flag + "'");
		
		if(rs.next()){
			Pedestal ret = new Pedestal(rs.getString("name"), rs.getInt("position"), rs.getInt("x"), rs.getInt("y"), rs.getInt("z"), rs.getString("flag"));
			stmt.close();
			return ret;
		}
		
		stmt.close();
		return null;
	} //end findFlag
	
	public static void placeFlag(Connection c, String name, int position, String flag) throws SQLException {
		Statement stmt = c.createStatement();
		stmt.executeUpdate(UPDATE_PEDESTAL + "'" + flag + "' " + WHERE_NAME + "'" + name + "'" + AND_POSITION + "'" + position + "'");
		
		stmt.close();
	} //end placeFlag
	
	public static Pedestal getPedestalByPosition(Connection c, int x, int y, int z) throws SQLException {
		Statement stmt = c.createStatement();
		String sql = SELECT_PEDESTAL + WHERE_X + x + AND_Y + y + AND_Z + z;
		ResultSet rs = stmt.executeQuery(sql);
		
		if(rs.next()){
			Pedestal ret = new Pedestal(rs.getString("name"), rs.getInt("position"), rs.getInt("x"), rs.getInt("y"), rs.getInt("z"), rs.getString("flag"));
			stmt.close();
			return ret;
		}
		
		stmt.close();
		return null;
	} //end getPedestalByPosition
	

	
} //end NationDAO class
