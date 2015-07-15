package io.github.mosestroyer.nations.spells;

import org.bukkit.entity.Player;

import io.github.mosestroyer.nations.Nations;

public class BlankSpell extends Spellbook{
	
	String name = "";
	String description = "Just left click to cast";
	String msgText = "";
	int tier = 1;
	
	public BlankSpell(){ //Change from BlankSpell to name of the spell!
		super.setName(name);
		super.setDescription(description);
		super.setMsg(msgText);
		super.setTier(tier);
	}

	@Override
	public boolean doSpell(Player p, Nations n){
		return false;
	}
}
