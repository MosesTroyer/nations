package io.github.mosestroyer.nations.spells.spellbooks;

import org.bukkit.CropState;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.material.Crops;

import io.github.mosestroyer.nations.Nations;
import io.github.mosestroyer.nations.spells.Spellbook;

public class GrowCrops extends Spellbook {

	public void doSpell(Location loc, int r, Nations n){
		n.getLogger().info("Grew the crops!");
		World world = loc.getWorld();
		
		int x1 = loc.getBlockX() - r/2;
		int y1 = loc.getBlockY() - r/2;
		int z1 = loc.getBlockZ() - r/2;
		
		Block cb = world.getBlockAt(loc.getBlockX(),loc.getBlockY(),loc.getBlockZ());
		cb.setType(Material.FIREWORK_CHARGE);
		
		for(int i=0; i<r; i++){
			for(int j=0; j<r; j++){
				for(int k=0; k<r; k++){
					cb = world.getBlockAt(x1+i,y1+j,z1+k);
					
					if(cb.getType().equals(Material.CROPS)){
						BlockState bs = cb.getState();
						bs.setData(new Crops(CropState.RIPE));
						bs.update();
					}
				}
			}
		}
		
		
		
	}
	
	
}
