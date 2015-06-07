package io.github.mosestroyer.nations.spells.spellbooks;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;

import io.github.mosestroyer.nations.Nations;
import io.github.mosestroyer.nations.spells.Spellbook;



public class ArrowStorm extends Spellbook{
	
	String name = "Arrow Storm";
	String description = "Arrow Storm: Unleashes a storm of arrows upon your foes! Just left click to cast";
	String msgText = "Stormed the arrows!";
	int tier = 1;
	
	int s = 10;
	
	public ArrowStorm(){
		super.setName(name);
		super.setDescription(description);
		super.setMsg(msgText);
		super.setTier(tier);
	}
	
	@Override
	public void doSpell(Player p, Nations n){
		n.getLogger().info(p.getPlayerListName()+" "+msgText.toLowerCase());
		p.sendMessage(msgText);
	
		for(int j=0; j<s; j++){
			p.launchProjectile(Arrow.class);
		}
		
	}
}
