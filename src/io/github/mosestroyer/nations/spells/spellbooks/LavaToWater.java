package io.github.mosestroyer.nations.spells.spellbooks;


import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import io.github.mosestroyer.nations.Nations;
import io.github.mosestroyer.nations.spells.Spellbook;

public class LavaToWater extends Spellbook{
	
	String name = "Lava to Water";
	String description = "Lava to Water: Turns a block of lava to water! Just left click to cast";
	String msgText = "Turned the lava!";
	int tier = 1;
	
	public LavaToWater(){ //Change from BlankSpell to name of the spell!
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
			
			if(block.getType().equals(Material.STATIONARY_LAVA)){
				((Block) block).setType(Material.STATIONARY_WATER);
				return true;
			}
		}
		
		return false;
	}
}
