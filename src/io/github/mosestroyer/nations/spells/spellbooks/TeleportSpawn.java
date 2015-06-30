package io.github.mosestroyer.nations.spells.spellbooks;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import io.github.mosestroyer.nations.Nations;
import io.github.mosestroyer.nations.spells.Spellbook;

public class TeleportSpawn extends Spellbook{
	
	String name = "Teleport Spawn";
	String description = "Teleport to Spawn: A simple teleportation tome to get you home! Just left click to cast";
	String msgText = "Teleported to spawn!";
	int tier = 1;
	
	public TeleportSpawn(){
		super.setName(name);
		super.setDescription(description);
		super.setMsg(msgText);
		super.setTier(tier);
	}

	@Override
	public boolean doSpell(Player p, Nations n){
		
		Location loc = p.getWorld().getSpawnLocation();
		p.teleport(loc);
		
		return true;
	}
}
