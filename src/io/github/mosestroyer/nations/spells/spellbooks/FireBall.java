package io.github.mosestroyer.nations.spells.spellbooks;

import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;

import io.github.mosestroyer.nations.Nations;
import io.github.mosestroyer.nations.spells.Spellbook;

public class FireBall extends Spellbook{
	
	String name = "Fire Ball";
	String description = "Fire Ball: A spell that lets you shoot fire from your hands! Just Left Click to cast";
	String msgText = "Shot the fire!";
	int tier = 1;

	public FireBall(){
		super.setName(name);
		super.setDescription(description);
		super.setMsg(msgText);
		super.setTier(tier);
	}
	
	@Override
	public boolean doSpell(Player p, Nations n){
		p.launchProjectile(Fireball.class);
		return true;
	}
}
