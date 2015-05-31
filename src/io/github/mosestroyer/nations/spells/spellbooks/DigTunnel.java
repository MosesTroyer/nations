package io.github.mosestroyer.nations.spells.spellbooks;


import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import io.github.mosestroyer.nations.Nations;
import io.github.mosestroyer.nations.spells.Spellbook;

public class DigTunnel extends Spellbook{

	String name = "Dig Tunnel";
	String id = "digtunnel-nationsplugin-56732437654";
	String description = "A spell that will dig a hole right to the other side of minecraft!";
	String msgText = "Dug the hole!";
	int tier = 1;
	
	public DigTunnel(){
		super.setName(name);
		super.setId(id);
		super.setDescription(description);
		super.setMsg(msgText);
		super.setTier(tier);
	}
	
	@Override
	public void doSpell(Player p, Nations n, int s){
		Location loc = p.getLocation();
		
		n.getLogger().info(p.getDisplayName() + msgText);
		p.sendMessage(msgText);
		World world = loc.getWorld();
		
		int x1 = loc.getBlockX();
		int y1 = loc.getBlockY();
		int z1 = loc.getBlockZ();
		
		Block cb = world.getBlockAt(loc.getBlockX(),loc.getBlockY(),loc.getBlockZ());
		
		for(int i=0; i<s; i++){
			
			for(int j=0; j<3; j++){
				for(int k=0; k<3; k++){
					for(int l=0; l<3; l++){
						cb = world.getBlockAt(x1+j, y1+k, z1+l);
						cb.setType(Material.AIR);
					}
				}
			}
			x1 += loc.getDirection().getX();
			z1 += loc.getDirection().getZ();
		}
		
		
		
	}
}
