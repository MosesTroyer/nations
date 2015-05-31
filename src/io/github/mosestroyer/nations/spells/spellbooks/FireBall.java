package io.github.mosestroyer.nations.spells.spellbooks;

import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;

import io.github.mosestroyer.nations.Nations;
import io.github.mosestroyer.nations.spells.Spellbook;



public class FireBall extends Spellbook{
	
	String name = "Fire Ball";
	String id = "fireball-nationsplugin-56732437654";
	String description = "A spell that lets you shoot fire from your hands!";
	String msgText = "Shot the fire!";
	int tier = 1;

	public FireBall(){
		super.setName(name);
		super.setId(id);
		super.setDescription(description);
		super.setMsg(msgText);
		super.setTier(tier);
	}
	
	@Override
	public void doSpell(Player p, Nations n){
		p.sendMessage(msgText);
		n.getLogger().info(p.getDisplayName()+msgText);

		p.launchProjectile(Fireball.class);
	}
}
