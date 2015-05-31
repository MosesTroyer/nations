package io.github.mosestroyer.nations.spells.spellbooks;

import org.bukkit.entity.Player;

import io.github.mosestroyer.nations.Nations;
import io.github.mosestroyer.nations.spells.Spellbook;



public class Heal extends Spellbook{

	public void doSpell(Player p, Nations nations){
		if(p.getHealth() < p.getMaxHealth()){
			p.setHealth(p.getMaxHealth());
			nations.getLogger().info("Healed the body!");
		}
	}
}
