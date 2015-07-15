package io.github.mosestroyer.nations.spells.spellbooks;

import org.bukkit.CropState;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.material.Crops;

import io.github.mosestroyer.nations.Nations;
import io.github.mosestroyer.nations.spells.Spellbook;

public class GrowCrops extends Spellbook {

	String name = "Grow Wheat";
	String description = "Grow What: A spell that grows all wheat within an area! Just left click to cast";
	String msgText = "Grew the wheat!";
	int tier = 1;
	int r = 20;
	
	public GrowCrops(){
		super.setName(name);
		super.setDescription(description);
		super.setMsg(msgText);
		super.setTier(tier);
	}
	
	@Override
	public boolean doSpell(Player p, Nations n){
		Location loc = p.getLocation();
		World world = loc.getWorld();
		boolean ret = false;
		
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
						ret = true;
					}
				}
			}
		}
		
		return ret;
		
	}
	
	
}
