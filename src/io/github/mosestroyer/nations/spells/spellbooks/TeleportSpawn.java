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
	public void doSpell(Player p, Nations n){
		n.getLogger().info(p.getPlayerListName()+" "+msgText.toLowerCase());
		p.sendMessage(msgText);
		
		//change for when we have a real spawn, this is just where I spawned.
		Location loc = new Location(p.getWorld(),-3040,34,1692);
		p.teleport(loc);
	}
}
