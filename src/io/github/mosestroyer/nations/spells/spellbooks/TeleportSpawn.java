package io.github.mosestroyer.nations.spells.spellbooks;



import org.bukkit.Location;
import org.bukkit.entity.Player;

import io.github.mosestroyer.nations.Nations;
import io.github.mosestroyer.nations.spells.Spellbook;



public class TeleportSpawn extends Spellbook{
	
	String name = "Teleport Spawn";
	String id = "teleportspawn-nationsplugin-56732437654";
	String description = "A simple teleportation tome to get you home!";
	String msgText = "Teleported to spawn!";
	int tier = 1;
	
	public TeleportSpawn(){
		super.setName(name);
		super.setId(id);
		super.setDescription(description);
		super.setMsg(msgText);
		super.setTier(tier);
	}

	@Override
	public void doSpell(Player p, Nations n){
		n.getLogger().info(msgText);
		p.sendMessage(msgText);
		
		//change for when we have a real spawn, this is just where I spawned.
		Location loc = new Location(p.getWorld(),-3040,34,1692);
		p.teleport(loc);
	}
}
