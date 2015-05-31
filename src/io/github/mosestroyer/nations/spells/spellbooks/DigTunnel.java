package io.github.mosestroyer.nations.spells.spellbooks;


import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

import io.github.mosestroyer.nations.Nations;
import io.github.mosestroyer.nations.spells.Spellbook;

public class DigTunnel extends Spellbook{

	public void doSpell(Location loc, int s, Nations n){
		n.getLogger().info("Dug a hole!");
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
