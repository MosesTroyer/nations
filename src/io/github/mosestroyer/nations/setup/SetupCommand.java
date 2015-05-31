package io.github.mosestroyer.nations.setup;

import io.github.mosestroyer.nations.Nations;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetupCommand implements CommandExecutor {
	
	private final Nations nations;
	
	public SetupCommand(Nations nations){
		this.nations = nations;
	} //end constructor

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(sender instanceof Player){
			
			nations.getLogger().info("Hello World");
			
			return true;
		}
		
		return false;
	} //end onCommand

} //end SetupCommand class
