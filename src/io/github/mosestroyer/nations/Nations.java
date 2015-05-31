package io.github.mosestroyer.nations;


import io.github.mosestroyer.nations.spells.spellbooks.Heal;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.mysql.jdbc.Connection;

import io.github.mosestroyer.nations.setup.SetupDAO;
import io.github.mosestroyer.nations.util.CommandRegistration;
import io.github.mosestroyer.nations.util.DatabaseConnection;

public class Nations extends JavaPlugin {

	public void onEnable(){
		
		try {
			
			CommandRegistration.registerCommands(this);
			
			DatabaseConnection db = new DatabaseConnection();
			Connection c = (Connection) db.getConnection();
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
