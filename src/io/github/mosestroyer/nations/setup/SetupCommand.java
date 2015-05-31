package io.github.mosestroyer.nations.setup;

import java.sql.Connection;
import java.sql.SQLException;

import io.github.mosestroyer.nations.Nations;
import io.github.mosestroyer.nations.nation.Nation;
import io.github.mosestroyer.nations.nation.NationDAO;
import io.github.mosestroyer.nations.util.DatabaseConnection;
import io.github.mosestroyer.nations.util.HelperFunctions;

import org.bukkit.DyeColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetupCommand implements CommandExecutor {
	
	private final Nations nations;
	
	public SetupCommand(Nations nations){
		this.nations = nations;
	} //end constructor

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		try {
		
			if(command.getName().equalsIgnoreCase("createNationsBoard")){
				if(HelperFunctions.commandCheck(sender, command, label, args, nations, -1, true, false))
					return createNationsBoard(sender, command, label, args);
				
			} else if(command.getName().equalsIgnoreCase("createPedestal")){
				//TODO(moses)
				//Check for nations board before letting this command run
				if(HelperFunctions.commandCheck(sender, command, label, args, nations, 1, true, false))
						return createPedestal(sender, command, label, args);
			} else if(command.getName().equalsIgnoreCase("createNation")){
				if(HelperFunctions.commandCheck(sender, command, label, args, nations, 2, false, false)){
					return createNation(sender, command, label, args, nations);
				}
			} else if(command.getName().equalsIgnoreCase("showNations")){
				return showNations(sender, command, label, args, nations);
			} else if(command.getName().equalsIgnoreCase("removeNation")){
				if(HelperFunctions.commandCheck(sender, command, label, args, nations, 1, false, false)){
					return removeNation(sender, command, label, args, nations);
				}
			}
			
			
		} catch (Exception e){
			nations.getLogger().info(e.getMessage());
		}
		
		return false;
	} //end onCommand
	
	private boolean createNationsBoard(CommandSender sender, Command command, String label, String[] args){
		
		return false;
	} //end createNationsBoard
	
	private boolean createPedestal(CommandSender sender, Command command, String label, String[] args){
			
		return false;
	} //end createPedestal
	
	private boolean createNation(CommandSender sender, Command command, String label, String[] args, Nations nations) throws SQLException{
		Connection c = DatabaseConnection.getConnection();
		
		NationDAO.getNations(c);
		
		Nation[] nationList = NationDAO.getNations(c);
		
		String name = args[0];
		String color = args[1];
		
		for(Nation n : nationList){
			if(n.getName().equals(name)){
				HelperFunctions.sendSenderMessage(nations, sender, "Cannot create nation, already exists!");
				DatabaseConnection.closeConnection(c);
				return false;
			}
			if(n.getColor().equals(color)){
				HelperFunctions.sendSenderMessage(nations, sender, "Cannot create nation, color already in use!");
				DatabaseConnection.closeConnection(c);
				return false;
			}
		}
		
		try{ 
			DyeColor.valueOf(color);
		} catch (Exception e) {
			HelperFunctions.sendSenderMessage(nations, sender, "Cannot create nation, given color is not a dye color!");
			DatabaseConnection.closeConnection(c);
			return false;
		}
		
		SetupDAO.createNation(c, name, color);

		DatabaseConnection.closeConnection(c);
		return true;
	} //end createNation
	
	private boolean showNations(CommandSender sender, Command command, String label, String[] args, Nations nations) throws SQLException {
		Connection c = DatabaseConnection.getConnection();
		
		Nation[] nationList = NationDAO.getNations(c);
		
		for(Nation n : nationList)
			HelperFunctions.sendSenderMessage(nations, sender, n.getName() + ", " + n.getColor());
		
		return true;
	} //end showNations
	
	private boolean removeNation(CommandSender sender, Command command, String label, String[] args, Nations nations) throws SQLException {
		Connection c = DatabaseConnection.getConnection();
		
		SetupDAO.removeNation(c, args[0]);
		
		return true;
	} //end removeNation

} //end SetupCommand class
