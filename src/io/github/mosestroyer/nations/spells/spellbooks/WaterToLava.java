package io.github.mosestroyer.nations.spells.spellbooks;


import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import io.github.mosestroyer.nations.Nations;
import io.github.mosestroyer.nations.spells.Spellbook;

public class WaterToLava extends Spellbook{
	
	String name = "Water to Lava";
	String description = "Water to Lava: Turns a block of water to lava! Just left click to cast";
	String msgText = "Turned the water!";
	int tier = 1;
	
	public WaterToLava(){ //Change from BlankSpell to name of the spell!
		super.setName(name);
		super.setDescription(description);
		super.setMsg(msgText);
		super.setTier(tier);
	}

	@Override
	public boolean doSpell(Player p, Nations n){
		
		Location loc = p.getEyeLocation();
		Vector dir = loc.getDirection().normalize();
		
		Block block;
		
		for(int i=0; i<10; i++){
			block = loc.add(dir).getBlock();
			
			if(block.getType().equals(Material.STATIONARY_WATER)){
				((Block) block).setType(Material.STATIONARY_LAVA);
				return true;
			}
		}
		
		return false;
	}
}
