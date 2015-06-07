package io.github.mosestroyer.nations;


import java.sql.Connection;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.mosestroyer.nations.setup.SetupDAO;
import io.github.mosestroyer.nations.util.CommandRegistration;
import io.github.mosestroyer.nations.util.DatabaseConnection;
import io.github.mosestroyer.nations.util.ListenerRegistration;

public class Nations extends JavaPlugin implements Listener{

	public void onEnable(){
		
		try {
			
			CommandRegistration.registerCommands(this);
			ListenerRegistration.registerListeners(this);
			getServer().getPluginManager().registerEvents(this, this);
			
			Connection c = DatabaseConnection.getConnection();
			SetupDAO.checkForTables(c);
			DatabaseConnection.closeConnection(c);
		
			getLogger().info("Nations successfully enabled!");
		} catch (Exception e) {
			getLogger().info("Nations failed to start!");
			getLogger().info((e.getStackTrace())[0].toString());
		}
		
	} //end onEnable
	
	public void onDisable(){
		
		try {
			
			getLogger().info("Nations successfully disabled!");
			
		} catch (Exception e) {
			
			getLogger().info("Nations failed to disable successfully!");
			
		}	

	} //end onDisable
	

} //end Nations class
