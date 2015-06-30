package io.github.mosestroyer.nations.spells.spellbooks;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import io.github.mosestroyer.nations.Nations;
import io.github.mosestroyer.nations.spells.Spellbook;

public class DigTunnel extends Spellbook{

	String name = "Dig Tunnel";
	String description = "Dig: A spell that will dig a hole right to the other side of minecraft! Just left click to cast";
	String msgText = "Dug the hole!";
	int tier = 1;
	
	public DigTunnel(){
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
		Block block2;
		Block block3;
		
		for(int i=0; i<20; i++){
			block = loc.add(dir).getBlock();
			block2 = p.getWorld().getBlockAt(block.getX(), block.getY()+1, block.getZ());
			block3 = p.getWorld().getBlockAt(block.getX(), block.getY()+2, block.getZ());
			
			((Block) block).setType(Material.AIR);
			((Block) block2).setType(Material.AIR);
			((Block) block3).setType(Material.AIR);

		}
		
		return true;
		
	}
}
