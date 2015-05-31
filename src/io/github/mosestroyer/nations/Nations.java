package io.github.mosestroyer.nations;


import io.github.mosestroyer.nations.spells.spellbooks.Heal;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Nations extends JavaPlugin {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("heal")) { 
			if(sender instanceof Player){
				Heal hl = new Heal();
				hl.doSpell((Player)sender, this);
			}
			return true;
		}
		return false; 
	}

	
	public void onEnable(){
		
		getLogger().info("Nations successfully enabled!");
		
	} //end onEnable
	
	public void onDisable(){
		
		getLogger().info("Nations successfully disabled!");
		
	} //end onDisable

} //end Nations class
