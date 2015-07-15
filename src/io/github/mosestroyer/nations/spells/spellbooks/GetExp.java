package io.github.mosestroyer.nations.spells.spellbooks;

import org.bukkit.entity.Player;

import io.github.mosestroyer.nations.Nations;
import io.github.mosestroyer.nations.spells.Spellbook;

public class GetExp extends Spellbook{
	
	String name = "Get Exp";
	String description = "Get Exp: Gives the player 300 Exp. Just left click to cast";
	String msgText = "Gave the Exp!";
	int tier = 1;
	
	public GetExp(){ //Change from BlankSpell to name of the spell!
		super.setName(name);
		super.setDescription(description);
		super.setMsg(msgText);
		super.setTier(tier);
	}

	@Override
	public boolean doSpell(Player p, Nations n){
		
		p.giveExp(300);
		
		return false;
	}
}
