package io.github.mosestroyer.nations.spells.spellbooks;

import org.bukkit.entity.Player;

import io.github.mosestroyer.nations.Nations;
import io.github.mosestroyer.nations.spells.Spellbook;



public class Heal extends Spellbook{
	
	String name = "Heal";
	String description = "Heal: A spell that will leave you feeling great! Just left click to cast";
	String msgText = "Healed the body!";
	int tier = 1;

	public Heal(){
		super.setName(name);
		super.setDescription(description);
		super.setMsg(msgText);
		super.setTier(tier);
	}
	
	@Override
	public void doSpell(Player p, Nations n){
		if(p.getHealth() < p.getMaxHealth()){
			p.setHealth(p.getMaxHealth());
			n.getLogger().info(p.getPlayerListName()+" "+msgText.toLowerCase());
			p.sendMessage(msgText);
		}
	}
}
