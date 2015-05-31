package io.github.mosestroyer.nations.spells.spellbooks;

import org.bukkit.entity.Player;

import io.github.mosestroyer.nations.Nations;
import io.github.mosestroyer.nations.spells.Spellbook;



public class Heal extends Spellbook{
	
	String description = "A spell that will leave you feeling ";
	String msgText = "Healed the body!";
	int tier = 1;

	public void doSpell(Player p, Nations n){
		if(p.getHealth() < p.getMaxHealth()){
			p.setHealth(p.getMaxHealth());
			n.getLogger().info(p.getDisplayName() + msgText);
			p.sendMessage(msgText);
		}
	}
}
