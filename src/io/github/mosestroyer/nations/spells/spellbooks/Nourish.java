package io.github.mosestroyer.nations.spells.spellbooks;

import org.bukkit.entity.Player;

import io.github.mosestroyer.nations.Nations;
import io.github.mosestroyer.nations.spells.Spellbook;

public class Nourish extends Spellbook{
	
	String name = "Nourish";
	String description = "Nourish: Fills the player's hunger bar. Just left click to cast";
	String msgText = "Nourished the body!";
	int tier = 1;
	
	public Nourish(){ //Change from BlankSpell to name of the spell!
		super.setName(name);
		super.setDescription(description);
		super.setMsg(msgText);
		super.setTier(tier);
	}

	@Override
	public boolean doSpell(Player p, Nations n){

		p.setFoodLevel(20); //sets full food
		
		return true;
	}
}
