package io.github.mosestroyer.nations.util;

import io.github.mosestroyer.nations.Nations;
import io.github.mosestroyer.nations.playerActions.PlayerListener;
import io.github.mosestroyer.nations.spells.SpellsListener;

public class ListenerRegistration {
	
	public static void registerListeners(Nations nations){
		//Player Listener
		nations.getServer().getPluginManager().registerEvents(new PlayerListener(), nations);
		
		//Spells Listener
		nations.getServer().getPluginManager().registerEvents(new SpellsListener(nations), nations);
		
	} //end registerListeners

} //end ListenerRegistration class
