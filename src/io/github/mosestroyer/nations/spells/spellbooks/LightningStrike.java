package io.github.mosestroyer.nations.spells.spellbooks;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import io.github.mosestroyer.nations.Nations;
import io.github.mosestroyer.nations.spells.Spellbook;

public class LightningStrike extends Spellbook{
	
	String name = "Lightning Strike";
	String description = "1.21 Gigawats to the face! Just left click to cast";
	String msgText = "Struck the lightning!";
	int tier = 2;
	
	public LightningStrike(){ 
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
		
		for(int i=0; i<100; i++){
			block = loc.add(dir).getBlock();
			
			if(!block.getType().equals(Material.AIR)){
				p.getWorld().strikeLightning(block.getLocation());
				return true;
			}
		}
		
		return false;
	}
}
