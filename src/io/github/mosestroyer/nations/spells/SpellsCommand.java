package io.github.mosestroyer.nations.spells;

import io.github.mosestroyer.nations.Nations;
import io.github.mosestroyer.nations.spells.spellbooks.ArrowStorm;
import io.github.mosestroyer.nations.spells.spellbooks.FireBall;
import io.github.mosestroyer.nations.spells.spellbooks.GiveSpellbook;
import io.github.mosestroyer.nations.spells.spellbooks.GrowCrops;
import io.github.mosestroyer.nations.spells.spellbooks.Heal;
import io.github.mosestroyer.nations.spells.spellbooks.Suicide;
import io.github.mosestroyer.nations.spells.spellbooks.TeleportSpawn;

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
			if(command.getName().equalsIgnoreCase("givespellbook")){
				GiveSpellbook gsp = new GiveSpellbook();
				gsp.doSpell(((Player) sender), this.nations);
			}
			
		}
		
		return false;
	} //end onCommand

} //end SpellsCommand
