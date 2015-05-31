package io.github.mosestroyer.nations.spells.spellbooks;



import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;

import io.github.mosestroyer.nations.Nations;
import io.github.mosestroyer.nations.spells.Spellbook;



public class ArrowStorm extends Spellbook{
	
	String description = "A powerful spell that launches arrows at a target";
	String msgText = "Stormed the arrows!";
	int tier = 1;

	public void doSpell(Player p, Nations n, int s){
		n.getLogger().info(msgText);
		p.sendMessage(msgText);
	
		for(int i=0; i<s; i++){
			p.launchProjectile(Arrow.class);
		}
		
	}
}
