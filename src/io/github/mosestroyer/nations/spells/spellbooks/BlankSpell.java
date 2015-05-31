package io.github.mosestroyer.nations.spells.spellbooks;



import org.bukkit.entity.Player;

import io.github.mosestroyer.nations.Nations;
import io.github.mosestroyer.nations.spells.Spellbook;



public class BlankSpell extends Spellbook{
	
	String description = "";
	String msgText = "";
	int tier = 1;

	public void doSpell(Player p, Nations n){
		n.getLogger().info(msgText);
		p.sendMessage(msgText);
	}
}
