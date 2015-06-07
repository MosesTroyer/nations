package io.github.mosestroyer.nations.playerActions;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerListener implements Listener {
	
	@EventHandler
	public void setPlayerNameColor(PlayerLoginEvent event){
		
		PlayerCommand.setPlayerColor(event.getPlayer());
		
	} //end formatchat

} //end playerListener class
