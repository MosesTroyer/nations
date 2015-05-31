package io.github.mosestroyer.nations;

import java.sql.Connection;

import org.bukkit.plugin.java.JavaPlugin;

import io.github.mosestroyer.nations.setup.SetupDAO;
import io.github.mosestroyer.nations.util.CommandRegistration;
import io.github.mosestroyer.nations.util.DatabaseConnection;

public class Nations extends JavaPlugin {
	
	public void onEnable(){
		
		try {
			
			CommandRegistration.registerCommands(this);
			
			DatabaseConnection db = new DatabaseConnection();
			Connection c = db.getConnection();
			SetupDAO dao = new SetupDAO();
			dao.checkForTables(c);
			db.closeConnection(c);
		
			getLogger().info("Nations successfully enabled!");
		} catch (Exception e) {
			getLogger().info("Nations failed to start!");
			getLogger().info(e.getMessage());
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
