package io.github.mosestroyer.nations.playerActions;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;

import io.github.mosestroyer.nations.Nations;
import io.github.mosestroyer.nations.nation.Nation;
import io.github.mosestroyer.nations.nation.NationDAO;
import io.github.mosestroyer.nations.util.DatabaseConnection;
import io.github.mosestroyer.nations.util.HelperFunctions;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayerCommand  implements CommandExecutor {
	
	private final Nations nations;
	
	public PlayerCommand(Nations nations){
		this.nations = nations;
	} //end constructor

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		try {
			
			if(command.getName().equalsIgnoreCase("joinNation")){
				if(HelperFunctions.commandCheck(sender, command, label, args, nations, 1, true, false)){
					return joinNation(sender, command, label, args);
				}			
			} else if (command.getName().equalsIgnoreCase("leaveNation")){
				if(HelperFunctions.commandCheck(sender, command, label, args, nations, -1, true, false))
					return leaveNation(sender, command, label, args);
			}
			
			
		} catch (Exception e){
			nations.getLogger().info(e.getMessage());
		}
		
		return false;
	} //end onCommand
	
	private boolean joinNation(CommandSender sender, Command command, String label, String[] args) throws SQLException{
		Connection c = DatabaseConnection.getConnection();
		
		if(!(PlayerDAO.getPlayerNation(c, ((Player) sender).getUniqueId()).equals(""))){
			sender.sendMessage("You are already in a nation!");
			c.close();
			return true;
		}
		
		Nation[] nationList = NationDAO.getNations(c);
		for(Nation n : nationList){
			if(n.getName().equals(args[0])){
				PlayerDAO.setPlayerNation(c, ((Player) sender).getUniqueId(), args[0]);
				setPlayerColor((Player) sender);
				c.close();
				sender.sendMessage(HelperFunctions.dyeColorToChatColor(n.getDyeColor()) + "Nation Joined!");
				return true;
			}
		}
		
		sender.sendMessage("Nation does not exist!");
		c.close();
		return true;
	} //end joinNation

	private boolean leaveNation(CommandSender sender, Command command, String label, String[] args) throws SQLException {
		Connection c = DatabaseConnection.getConnection();
		
		if(PlayerDAO.getPlayerNation(c, ((Player) sender).getUniqueId()).equals("")){
			sender.sendMessage("You are not in a nation!");
			c.close();
			return true;
		}
		
		PlayerDAO.leaveNation(c, ((Player) sender).getUniqueId());
		setPlayerColor((Player) sender);
		
		c.close();
		sender.sendMessage("You left your nation!");
		return true;
	} //end leaveNation
	
	public static void setPlayerColor(Player player){

		String name = ChatColor.stripColor(player.getDisplayName());
		UUID id = player.getUniqueId();
		
		try{
			Connection c = DatabaseConnection.getConnection();
			
			String nationName = PlayerDAO.getPlayerNation(c, id);
						
			if(!nationName.equals("")){

				Nation n = NationDAO.getNationByName(c, nationName);
				
				player.setDisplayName(HelperFunctions.dyeColorToChatColor(n.getDyeColor()) + name + ChatColor.WHITE);
				
			} else {
				player.setDisplayName(ChatColor.WHITE + name);
			}
			
			DatabaseConnection.closeConnection(c);
		} catch (Exception e) {}
		
	} //end setPlayerColor
	
} //end PlayerCommand class


