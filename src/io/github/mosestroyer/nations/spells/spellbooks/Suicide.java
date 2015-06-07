package io.github.mosestroyer.nations.spells.spellbooks;

import org.bukkit.entity.Player;

import io.github.mosestroyer.nations.Nations;
import io.github.mosestroyer.nations.spells.Spellbook;



public class Suicide extends Spellbook{
	
	String name = "Suicide";
	String description = "Suicide: A spell that kills you! Just left click to cast";
	String msgText = "Took the life!";
	int tier = 1;
	
	public Suicide(){
		super.setName(name);
		super.setDescription(description);
		super.setMsg(msgText);
		super.setTier(tier);
	}

	@Override
	public void doSpell(Player p, Nations n){
		if(p.getHealth() > 0){
			p.setHealth(0);
			n.getLogger().info(p.getPlayerListName()+" "+msgText.toLowerCase());
			p.sendMessage(msgText);
		}
	}
		
		
}
