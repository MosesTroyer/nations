package io.github.mosestroyer.nations.util;

import io.github.mosestroyer.nations.Nations;
import io.github.mosestroyer.nations.playerActions.PlayerListener;

public class ListenerRegistration {
	
	public static void registerListeners(Nations nations){
		nations.getServer().getPluginManager().registerEvents(new PlayerListener(), nations);
	} //end registerListeners

} //end ListenerRegistration class
