package io.github.mosestroyer.nations.spells;

import io.github.mosestroyer.nations.Nations;
import io.github.mosestroyer.nations.spells.spellbooks.FireBall;
import io.github.mosestroyer.nations.spells.spellbooks.GrowCrops;
import io.github.mosestroyer.nations.spells.spellbooks.Heal;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpellsCommand implements CommandExecutor {
	
	private final Nations nations;
	
	public SpellsCommand(Nations nations){
		this.nations = nations;
	} //end constructor

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(sender instanceof Player){
			if (command.getName().equalsIgnoreCase("heal")) { 
				Heal hl = new Heal();
				hl.doSpell((Player)sender, this.nations);
				return true;
			}
			if(command.getName().equalsIgnoreCase("digtunnel")){
				//TODO
				//Fix the fuck out of /digtunnel before implementation
			}
			if(command.getName().equalsIgnoreCase("growcrops")){
				GrowCrops gw = new GrowCrops();
				gw.doSpell((Player)sender, 20, this.nations);
			}
			if(command.getName().equalsIgnoreCase("fireball")){
				FireBall gw = new FireBall();
				gw.doSpell(((Player) sender), this.nations);
			}
			
			
		}
		
		return false;
	} //end onCommand

} //end SpellsCommand
