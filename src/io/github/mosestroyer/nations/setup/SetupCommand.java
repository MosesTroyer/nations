package io.github.mosestroyer.nations.setup;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import io.github.mosestroyer.nations.Nations;
import io.github.mosestroyer.nations.nation.ChestPedestal;
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
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.material.Chest;
import org.bukkit.material.Wool;

import com.sk89q.worldedit.BlockVector;
import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.bukkit.RegionContainer;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;

public class SetupCommand implements CommandExecutor {
	
	private final Nations nations;
	private final int CENTER = 4;
	
	public SetupCommand(Nations nations){
		this.nations = nations;
	} //end constructor

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		try {
		
			if(command.getName().equalsIgnoreCase("showNations")){
				return showNations(sender, command, label, args, nations);
			} 
			
			if(sender instanceof Player && !((Player)sender).isOp())
				return true;
			
			if(command.getName().equalsIgnoreCase("createNationsBoard")){
				if(HelperFunctions.commandCheck(sender, command, label, args, nations, -1, true, false))
					return createNationsBoard(sender, command, label, args);			
			} else if(command.getName().equalsIgnoreCase("createPedestal")){
				if(HelperFunctions.commandCheck(sender, command, label, args, nations, 1, true, false))
						return createPedestal(sender, command, label, args, nations);
			} else if (command.getName().equalsIgnoreCase("removePedestal")) {
				if(HelperFunctions.commandCheck(sender, command, label, args, nations, 1, false, false))
					return removePedestal(sender, command, label, args, nations);
			} else if(command.getName().equalsIgnoreCase("createNation")){
				if(HelperFunctions.commandCheck(sender, command, label, args, nations, 2, false, false)){
					return createNation(sender, command, label, args, nations);
				}
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
			
			WorldGuardPlugin worldGuard = WGBukkit.getPlugin();
			
			RegionContainer container = worldGuard.getRegionContainer();
			RegionManager regions = container.get(world);
			
			int xMin = loc.getBlockX() - 5;
			int yMin = loc.getBlockY() - 4;
			int zMin = loc.getBlockZ() - 5;
			
			BlockVector corner1 = new BlockVector(xMin, yMin, zMin);
			BlockVector corner2 = new BlockVector(loc.getBlockX() + 5, loc.getBlockY() + 4, loc.getBlockZ() + 5);
			
			int x;
			int y = yMin;
			int z;
			
			//bottom stone
			for(x = xMin; x < xMin + 11; x++){
				for(z = zMin; z < zMin + 11; z++){
					Block currentBlock = world.getBlockAt(x, y, z);
					currentBlock.setType(Material.STONE);
				}
			}

			xMin = loc.getBlockX() - 3;
			yMin = loc.getBlockY() - 3;
			zMin = loc.getBlockZ() - 3;
			
			y = yMin;

			Pedestal pedestal;
			int position = 0;

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
			
			//chests NOT USING A FOR LOOP BECAUSE I'M FEELIN LAZY
			position = 0;
			
			x = loc.getBlockX();
			y = loc.getBlockY() - 3;
			z = loc.getBlockZ() - 4;
				
			currentBlock = world.getBlockAt(x, y, z);
			currentBlock.setType(Material.CHEST);
			
			bs = currentBlock.getState();
			Chest chest = (Chest) currentBlock.getState().getData();
			chest.setFacingDirection(BlockFace.NORTH);
			bs.setData(chest);
			bs.update();
			
			SetupDAO.insertChestPosition(c, new ChestPedestal(nationName, position++, x, y, z));

			x = loc.getBlockX() + 4;
			y = loc.getBlockY() - 3;
			z = loc.getBlockZ();
				
			currentBlock = world.getBlockAt(x, y, z);
			currentBlock.setType(Material.CHEST);
			
			bs = currentBlock.getState();
			chest = (Chest) currentBlock.getState().getData();
			chest.setFacingDirection(BlockFace.EAST);
			bs.setData(chest);
			bs.update();
			
			SetupDAO.insertChestPosition(c, new ChestPedestal(nationName, position++, x, y, z));
			
			x = loc.getBlockX();
			y = loc.getBlockY() - 3;
			z = loc.getBlockZ() + 4;
				
			currentBlock = world.getBlockAt(x, y, z);
			currentBlock.setType(Material.CHEST);
			
			bs = currentBlock.getState();
			chest = (Chest) currentBlock.getState().getData();
			chest.setFacingDirection(BlockFace.SOUTH);
			bs.setData(chest);
			bs.update();
			
			SetupDAO.insertChestPosition(c, new ChestPedestal(nationName, position++, x, y, z));
			
			x = loc.getBlockX() - 4;
			y = loc.getBlockY() - 3;
			z = loc.getBlockZ();
				
			currentBlock = world.getBlockAt(x, y, z);
			currentBlock.setType(Material.CHEST);
			
			bs = currentBlock.getState();
			chest = (Chest) currentBlock.getState().getData();
			chest.setFacingDirection(BlockFace.WEST);
			bs.setData(chest);
			bs.update();
			
			SetupDAO.insertChestPosition(c, new ChestPedestal(nationName, position++, x, y, z));
			
			ProtectedCuboidRegion region = new ProtectedCuboidRegion(nationName, corner1, corner2);
			
			region.setPriority(100);
			
			region.setFlag(DefaultFlag.BUILD, StateFlag.State.DENY);
			region.setFlag(DefaultFlag.MOB_SPAWNING, StateFlag.State.DENY);
			region.setFlag(DefaultFlag.CREEPER_EXPLOSION, StateFlag.State.DENY);
			region.setFlag(DefaultFlag.OTHER_EXPLOSION, StateFlag.State.DENY);
			//enderman grief
			//region.setFlag(DefaultFlag.enderman-greif, StateFlag.State.DENY);
			region.setFlag(DefaultFlag.GHAST_FIREBALL, StateFlag.State.DENY);
			region.setFlag(DefaultFlag.TNT, StateFlag.State.DENY);
			region.setFlag(DefaultFlag.LIGHTER, StateFlag.State.DENY);
			region.setFlag(DefaultFlag.FIRE_SPREAD, StateFlag.State.DENY);
			region.setFlag(DefaultFlag.LAVA_FIRE, StateFlag.State.DENY);
			region.setFlag(DefaultFlag.LIGHTNING, StateFlag.State.DENY);
			region.setFlag(DefaultFlag.PISTONS, StateFlag.State.DENY);
			region.setFlag(DefaultFlag.WATER_FLOW, StateFlag.State.DENY);
			region.setFlag(DefaultFlag.LAVA_FLOW, StateFlag.State.DENY);
			region.setFlag(DefaultFlag.DENY_MESSAGE, "");
			region.setFlag(DefaultFlag.CHEST_ACCESS, StateFlag.State.ALLOW);
			
			regions.addRegion(region);
			
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
			
			WorldGuardPlugin worldGuard = WGBukkit.getPlugin();
			
			RegionContainer container = worldGuard.getRegionContainer();
			RegionManager regions = container.get(world);
			regions.removeRegion(nationName);
			
			String flag = pedestal.getFlag();

			int xMin = loc.getBlockX() - 5;
			int yMin = loc.getBlockY() - 3;
			int zMin = loc.getBlockZ() - 5;
			
			int x;
			int y = yMin;
			int z;
			
			//bottom stone
			for(x = xMin; x < xMin + 11; x++){
				for(z = zMin; z < zMin + 11; z++){
					Block currentBlock = world.getBlockAt(x, y, z);
					currentBlock.setType(Material.AIR);
				}
			}

			xMin = pedestal.getX() - 4;
			yMin = pedestal.getY() - 2;
			zMin = pedestal.getZ() - 4;
				
			y = yMin;
			
			Block currentBlock;
			
			//bottom base
			for(x = xMin; x < xMin + 9; x++){
				for(z = zMin; z < zMin + 9; z++){
					currentBlock = world.getBlockAt(x, y, z);
					currentBlock.setType(Material.AIR);
				}
			}
			
			//stands
			y++;
			for(x = xMin + 1; x < xMin + 7; x++){
				for(z = zMin + 1; z < zMin + 7; z++){
					currentBlock = world.getBlockAt(x, y, z);
					currentBlock.setType(Material.AIR);
					currentBlock = world.getBlockAt(x, y + 1, z);
					currentBlock.setType(Material.AIR);
				}
			}
			
			pedestal = NationDAO.findFlag(c, NationDAO.getColorByName(c, nationName));
			
			if(pedestal != null) {	
				
				currentBlock = world.getBlockAt(pedestal.getX(), pedestal.getY(), pedestal.getZ());
				currentBlock.setType(Material.AIR);
				
				NationDAO.placeFlag(c, pedestal.getName(), pedestal.getPosition(), "");
				
			}	
			
			enemyFlags = NationDAO.getPedestalPositions(c, nationName);
			
			for(Pedestal enemyFlag : enemyFlags){
				if(enemyFlag.getPosition() != CENTER && !enemyFlag.getFlag().equals("")){
					
					String enemyNation = NationDAO.getNameByColor(c, enemyFlag.getFlag());

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
			
			SetupDAO.removePedestals(c, nationName, flag);
			
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
