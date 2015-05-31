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

	String name = "Grow Crops";
	String id = "growcrops-nationsplugin-56732437654";
	String description = "A spell that crows all crops within an area!";
	String msgText = "Grew the crops!";
	int tier = 1;
	int r = 20;
	
	public GrowCrops(){
		super.setName(name);
		super.setId(id);
		super.setDescription(description);
		super.setMsg(msgText);
		super.setTier(tier);
	}
	
	@Override
	public void doSpell(Player p, Nations n){
		Location loc = p.getLocation();
		p.sendMessage(msgText);
		n.getLogger().info(p.getDisplayName()+msgText);
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
