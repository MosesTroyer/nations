package io.github.mosestroyer.nations.playerActions;

import java.sql.Connection;
import java.util.List;

import io.github.mosestroyer.nations.nation.NationDAO;
import io.github.mosestroyer.nations.nation.Pedestal;
import io.github.mosestroyer.nations.util.DatabaseConnection;

import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.material.Wool;

public class PlayerListener implements Listener {
	
	private final int CENTER = 4;
	
	@EventHandler
	public void setPlayerNameColor(PlayerLoginEvent event){
		
		PlayerCommand.setPlayerColor(event.getPlayer());
		
	} //end setPlayerNameColor
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		
		if(event.getAction() == Action.LEFT_CLICK_BLOCK) {
			
			if(event.getClickedBlock() == null) {
				event.setCancelled(true);
				return;
			}
			
			//Check for capturing flag
			Player player = event.getPlayer();
			
			if(event.getClickedBlock().getType().equals(Material.WOOL)) {
				
				Connection c = null;
				try {
					
					Location location = event.getClickedBlock().getLocation();
					
					try {
						c = DatabaseConnection.getConnection();
					} catch (Exception e){
						player.sendMessage(e.getMessage());
						return;
					}
					
					Pedestal pedestal = NationDAO.getPedestalByPosition(c, location.getBlockX(), location.getBlockY(), location.getBlockZ());
					
					if(pedestal != null && !pedestal.getFlag().equals("")) {
						
						//If the flag is team color and on an enemy pedestal
						String nationName = PlayerDAO.getPlayerNation(c, player.getUniqueId());
						if(nationName == ""){
							event.setCancelled(true);
							DatabaseConnection.closeConnection(c);
							return;
						}
						if(!pedestal.getName().equals(nationName) && pedestal.getFlag().equals(NationDAO.getColorByName(c, nationName))){
							
							Pedestal centerPedestal = NationDAO.getPedestal(c, nationName, CENTER);
							
							Location loc = player.getLocation();
							World world = loc.getWorld();
							
							Block currentBlock = world.getBlockAt(centerPedestal.getX(), centerPedestal.getY(), centerPedestal.getZ());
							currentBlock.setType(Material.WOOL);
							
							BlockState bs = currentBlock.getState();
							Wool woolmat = (Wool) bs.getData();
							woolmat.setColor(DyeColor.valueOf(pedestal.getFlag()));
							bs.setData(woolmat);
							bs.update();
							
							NationDAO.placeFlag(c, centerPedestal.getName(), centerPedestal.getPosition(), pedestal.getFlag());
							
							currentBlock = world.getBlockAt(pedestal.getX(), pedestal.getY(), pedestal.getZ());
							currentBlock.setType(Material.AIR);
							
							NationDAO.placeFlag(c, pedestal.getName(), pedestal.getPosition(), "");
							
							player.sendMessage("You recovered your nation's flag!");
							
							DatabaseConnection.closeConnection(c);
							return;
						} 
						//if the flag is an enemy color and on an enemy pedestal
						else if (!pedestal.getName().equals(nationName) && !pedestal.getFlag().equals(NationDAO.getColorByName(c, nationName))) {
							
							List<Pedestal> pedestals = NationDAO.getPedestalPositions(c, nationName);
							
							for(Pedestal p : pedestals) {
								if(p.getFlag().equals("") && p.getPosition() != CENTER){
									
									Location loc = player.getLocation();
									World world = loc.getWorld();
									
									Block currentBlock = world.getBlockAt(p.getX(), p.getY(), p.getZ());
									currentBlock.setType(Material.WOOL);
									
									BlockState bs = currentBlock.getState();
									Wool woolmat = (Wool) bs.getData();
									woolmat.setColor(DyeColor.valueOf(pedestal.getFlag()));
									bs.setData(woolmat);
									bs.update();
									
									NationDAO.placeFlag(c, p.getName(), p.getPosition(), pedestal.getFlag());
									
									currentBlock = world.getBlockAt(pedestal.getX(), pedestal.getY(), pedestal.getZ());
									currentBlock.setType(Material.AIR);
									
									NationDAO.placeFlag(c, pedestal.getName(), pedestal.getPosition(), "");
									
									player.sendMessage("You captured the flag from " + pedestal.getName() + "!");
									
									DatabaseConnection.closeConnection(c);
									return;
								}
							}

							player.sendMessage("You cannot steal any more flags!");
						}
						
					}
					
					DatabaseConnection.closeConnection(c);
				} catch (Exception e) {
					player.sendMessage("Failed to capture wool");
					player.sendMessage(e.getMessage());
					try {
						DatabaseConnection.closeConnection(c);
					} catch (Exception ex) {}
				}	
			}		
		}
		
	} //end onPlayerInteract

} //end playerListener class
