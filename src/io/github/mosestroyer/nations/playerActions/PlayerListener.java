package io.github.mosestroyer.nations.playerActions;

import java.sql.Connection;
import java.util.UUID;

import io.github.mosestroyer.nations.nation.NationDAO;
import io.github.mosestroyer.nations.util.DatabaseConnection;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerListener implements Listener {
	
	@EventHandler
	public void formatChat(AsyncPlayerChatEvent event){
		
		String name = event.getPlayer().getDisplayName();
		UUID id =event.getPlayer().getUniqueId();
		
		try{
			Connection c = DatabaseConnection.getConnection();
			
			String nationName = PlayerDAO.getPlayerNation(c, id);
			
			if(!nationName.equals("")){
				
				NationDAO.getNationByName(c, nationName);
				
				//event.getPlayer().setDisplayName(nation + name);
				
			} 
			
			DatabaseConnection.closeConnection(c);
		} catch (Exception e) {}
		
	} //end formatchat

} //end playerListener class
