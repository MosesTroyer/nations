package io.github.mosestroyer.nations.playerActions;

import java.sql.Connection;
import java.util.List;

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
		
	} //end formatchat
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		
		if(event.getAction() == Action.LEFT_CLICK_BLOCK) {
			
			//Check for capturing flag
			Player player = event.getPlayer();
			
			if(event.getClickedBlock().getType().equals(Material.WOOL)) {
				try {
					
					Location location = event.getClickedBlock().getLocation();
					
					Connection c = DatabaseConnection.getConnection();
					
					Pedestal pedestal = NationDAO.getPedestalByPosition(c, location.getBlockX(), location.getBlockY(), location.getBlockZ());
					
					if(pedestal != null && !pedestal.getFlag().equals("")) {
						
						//If the flag is team color and on an enemy pedestal
						String nationName = PlayerDAO.getPlayerNation(c, player.getUniqueId());
						if(!pedestal.getName().equals(nationName) && pedestal.getFlag().equals(NationDAO.getColorByName(c, nationName))){
							player.sendMessage("Your flag, enemy pedestal!");
						} 
						//if the flag is an enemy color and on an enemy pedestal
						else if (!pedestal.getName().equals(nationName) && !pedestal.getFlag().equals(NationDAO.getColorByName(c, nationName))) {
							player.sendMessage("Not your flag, enemy pedestal!");
							
							List<Pedestal> pedestals = NationDAO.getPedestalPositions(c, nationName);
							
							for(Pedestal p : pedestals) {
								player.sendMessage("Searching");
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
									
									player.sendMessage(p.getFlag());
									
									NationDAO.placeFlag(c, p.getName(), p.getPosition(), pedestal.getFlag());
									
									currentBlock = world.getBlockAt(pedestal.getX(), pedestal.getY(), pedestal.getZ());
									currentBlock.setType(Material.AIR);
									
									NationDAO.placeFlag(c, pedestal.getName(), pedestal.getPosition(), "");
									
									player.sendMessage("You captured the flag of " + pedestal.getName() + "!");
									
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
				}
				
			}
			
		
			
		}
		
	} //end onPlayerInteract

} //end playerListener class
