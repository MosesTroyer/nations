package io.github.mosestroyer.nations.spells;



import org.bukkit.entity.Player;

import io.github.mosestroyer.nations.Nations;



public class BlankSpell extends Spellbook{
	
	String description = "";
	String msgText = "";
	int tier = 1;
	
	public BlankSpell(){ //Change from BlankSpell to name of the spell!
		super.setDescription(description);
		super.setMsg(msgText);
		super.setTier(tier);
	}

	@Override
	public void doSpell(Player p, Nations n){
		n.getLogger().info(msgText);
		p.sendMessage(msgText);
	}
}
