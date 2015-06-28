package io.github.mosestroyer.nations.setup;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import io.github.mosestroyer.nations.Nations;
import io.github.mosestroyer.nations.nation.Nation;
import io.github.mosestroyer.nations.nation.NationDAO;
import io.github.mosestroyer.nations.nation.Pedestal;
import io.github.mosestroyer.nations.util.DatabaseConnection;
import io.github.mosestroyer.nations.util.HelperFunctions;

import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.material.Wool;

public class SetupCommand implements CommandExecutor {
	
	private final Nations nations;
	private final int CENTER = 4;
	
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
						return createPedestal(sender, command, label, args, nations);
			} else if (command.getName().equalsIgnoreCase("removePedestal")) {
				if(HelperFunctions.commandCheck(sender, command, label, args, nations, 1, false, false))
					return removePedestal(sender, command, label, args, nations);
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
	
	private boolean createPedestal(CommandSender sender, Command command, String label, String[] args, Nations nations){
		
		Connection c = null;
		
		try {
			
			try {
				removePedestal(sender, command, label, args, nations);
			} catch (Exception e) {}
			
			Location loc = ((Player) sender).getLocation();
			World world = loc.getWorld();
			String nationName = args[0];
			
			if(!HelperFunctions.nationExists(nationName)){
				sender.sendMessage("Could not create pedestal, nation does not exist!");
				return true;
			}
			
			c = DatabaseConnection.getConnection();
			
			int xMin = loc.getBlockX() - 3;
			int yMin = loc.getBlockY() - 3;
			int zMin = loc.getBlockZ() - 3;
			
			int x;
			int y = yMin;
			int z;

			Pedestal pedestal;
			int position = 0;
			

//			int xMax;
//			int yMax;
//			int zMax;
			
			//bottom base
			for(x = xMin; x < xMin + 7; x++){
				for(z = zMin; z < zMin + 7; z++){
					Block currentBlock = world.getBlockAt(x, y, z);
					currentBlock.setType(Material.BEDROCK);
				}
			}
			
			//stands
			y++;
			for(x = xMin + 1; x < xMin + 7; x +=2){
				for(z = zMin + 1; z < zMin + 7; z +=2){
					Block currentBlock = world.getBlockAt(x, y, z);
					pedestal = new Pedestal(nationName, position, x, y + 1, z, "");
					if(position == CENTER){
						pedestal.setFlag(NationDAO.getColorByName(c, nationName));
						SetupDAO.insertFlagPosition(c, pedestal);
					}
					else {
						SetupDAO.insertFlagPosition(c, pedestal);
					}
					currentBlock.setType(Material.BEDROCK);
					position++;
				}
			}
			
			//flag
			Block currentBlock = world.getBlockAt(xMin + 3, yMin + 2, zMin + 3);
			currentBlock.setType(Material.WOOL);
			
			BlockState bs = currentBlock.getState();
			Wool woolmat = (Wool) bs.getData();
			woolmat.setColor(DyeColor.valueOf(NationDAO.getColorByName(c, nationName)));
			bs.setData(woolmat);
			bs.update();
			
			DatabaseConnection.closeConnection(c);
			
			return true;
		} catch (Exception e){
			HelperFunctions.sendSenderMessage(nations, sender, "Failed to create pedestal!");
			nations.getLogger().info(e.getMessage());
			return false;
		} finally {
			try {
				DatabaseConnection.closeConnection(c);
			} catch (Exception e) {
				HelperFunctions.sendSenderMessage(nations, sender, "Failed to close database connection after attempting to create pedestal!");
				nations.getLogger().info(e.getMessage());
			}
		}
		
	} //end createPedestal
	
	private boolean removePedestal(CommandSender sender, Command command, String label, String[] args, Nations nations){
		
		Connection c = null;
		
		try {
			
			Location loc = ((Player) sender).getLocation();
			World world = loc.getWorld();
			String nationName = args[0];
			List<Pedestal> enemyFlags;
			
			c = DatabaseConnection.getConnection();
				
			Pedestal pedestal = NationDAO.getPedestal(c, nationName, CENTER);
			
			if(pedestal == null)
				return true;
			
			//TODO remove physical blocks
			int xMin = pedestal.getX() - 3;
			int yMin = pedestal.getY() - 2;
			int zMin = pedestal.getZ() - 3;
			
			int x;
			int y = yMin;
			int z;
			
			Block currentBlock;
			
			//bottom base
			for(x = xMin; x < xMin + 7; x++){
				for(z = zMin; z < zMin + 7; z++){
					currentBlock = world.getBlockAt(x, y, z);
					currentBlock.setType(Material.AIR);
				}
			}
			
			//stands
			y++;
			for(x = xMin + 1; x < xMin + 7; x +=2){
				for(z = zMin + 1; z < zMin + 7; z +=2){
					currentBlock = world.getBlockAt(x, y, z);
					currentBlock.setType(Material.AIR);
					currentBlock = world.getBlockAt(x, y + 1, z);
					currentBlock.setType(Material.AIR);
				}
			}
			
			pedestal = NationDAO.findFlag(c, NationDAO.getColorByName(c, nationName));
			
			if(pedestal != null) {	
				
				sender.sendMessage("Found the flag!");
				
				currentBlock = world.getBlockAt(pedestal.getX(), pedestal.getY(), pedestal.getZ());
				currentBlock.setType(Material.AIR);
			}	
			
			enemyFlags = NationDAO.getPedestalPositions(c, nationName);
			
			for(Pedestal enemyFlag : enemyFlags){
				if(enemyFlag.getPosition() != CENTER && !enemyFlag.getFlag().equals("")){
					
					String enemyNation = NationDAO.getNameByColor(c, enemyFlag.getFlag());
					
					sender.sendMessage("Returning flag!");
	
					NationDAO.placeFlag(c, enemyFlag.getName(), enemyFlag.getPosition(), "");
					
					NationDAO.placeFlag(c, enemyNation, CENTER, enemyFlag.getFlag());
					
					pedestal = NationDAO.getPedestal(c, enemyNation, CENTER);
					
					currentBlock = world.getBlockAt(pedestal.getX(), pedestal.getY(), pedestal.getZ());
					currentBlock.setType(Material.WOOL);
					
					BlockState bs = currentBlock.getState();
					Wool woolmat = (Wool) bs.getData();
					woolmat.setColor(DyeColor.valueOf(enemyFlag.getFlag()));
					bs.setData(woolmat);
					bs.update();
					
				}
			}
			
			//Make flags go away in db
			
			SetupDAO.removePedestals(c, nationName);
			
			return false;
		} catch (Exception e){
			HelperFunctions.sendSenderMessage(nations, sender, "Failed to remove pedestal!");
			nations.getLogger().info(e.getMessage());
			return false;
		} finally {
			try {
				DatabaseConnection.closeConnection(c);
			} catch (Exception e) {
				HelperFunctions.sendSenderMessage(nations, sender, "Failed to close database connection after attempting to remove pedestal!");
				nations.getLogger().info(e.getMessage());
			}
		}
		
	} //end removePedestal
	
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
				return true;
			}
			if(n.getColor().equals(color)){
				HelperFunctions.sendSenderMessage(nations, sender, "Cannot create nation, color already in use!");
				DatabaseConnection.closeConnection(c);
				return true;
			}
		}
		
		try{ 
			DyeColor.valueOf(color);
		} catch (Exception e) {
			HelperFunctions.sendSenderMessage(nations, sender, "Cannot create nation, given color is not a dye color!");
			DatabaseConnection.closeConnection(c);
			return true;
		}
		
		SetupDAO.createNation(c, name, color);

		DatabaseConnection.closeConnection(c);
		
		HelperFunctions.sendSenderMessage(nations, sender, "Nation created!");
		return true;
	} //end createNation
	
	private boolean showNations(CommandSender sender, Command command, String label, String[] args, Nations nations) throws SQLException {
		Connection c = DatabaseConnection.getConnection();
		
		Nation[] nationList = NationDAO.getNations(c);
		
		for(Nation n : nationList){
			String color = n.getColor();
			HelperFunctions.sendSenderMessage(nations, sender, n.getName() + ", " + HelperFunctions.dyeColorToChatColor(DyeColor.valueOf(color)) + color);
		}
		
		return true;
	} //end showNations
	
	private boolean removeNation(CommandSender sender, Command command, String label, String[] args, Nations nations) throws SQLException {
		Connection c = DatabaseConnection.getConnection();
		
		removePedestal(sender, command, label, args, nations);
		
		SetupDAO.removeNation(c, args[0]);
		
		HelperFunctions.sendSenderMessage(nations, sender, "Nation removed!");
		return true;
	} //end removeNation

} //end SetupCommand class
