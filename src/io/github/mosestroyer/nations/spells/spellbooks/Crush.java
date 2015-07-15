package io.github.mosestroyer.nations.spells.spellbooks;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.github.mosestroyer.nations.Nations;
import io.github.mosestroyer.nations.spells.Spellbook;

public class Crush extends Spellbook{
	
	String name = "Crush";
	String description = "Crushes (all) blocks in your inventory. (Cobble -> Gravel -> Dirt -> Sand) Just left click to cast";
	String msgText = "Crushed the blocks!";
	int tier = 1;
	
	public Crush(){ //Change from BlankSpell to name of the spell!
		super.setName(name);
		super.setDescription(description);
		super.setMsg(msgText);
		super.setTier(tier);
	}

	@Override
	public boolean doSpell(Player p, Nations n){
		
		boolean ret = false;
		
			
		for(int i=0; i < p.getInventory().getSize(); i++){
			
			if(p.getInventory().getItem(i) != null){
				
				if(p.getInventory().getItem(i).getType().equals(Material.COBBLESTONE)){
					ItemStack cobble = p.getInventory().getItem(i);
					ItemStack gravel = new ItemStack(Material.GRAVEL);
					gravel.setAmount(cobble.getAmount());
					
					p.getInventory().setItem(i, gravel);
					
					ret = true;
				}//End check for cobble
				
				
				if(p.getInventory().getItem(i).getType().equals(Material.GRAVEL)){
					ItemStack gravel = p.getInventory().getItem(i);
					ItemStack dirt = new ItemStack(Material.DIRT);
					gravel.setAmount(gravel.getAmount());
					
					p.getInventory().setItem(i, dirt);
					
					ret = true;
				}//End check for gravel
				
				if(p.getInventory().getItem(i).getType().equals(Material.DIRT)){
					ItemStack dirt = p.getInventory().getItem(i);
					ItemStack sand = new ItemStack(Material.SAND);
					sand.setAmount(dirt.getAmount());
					
					p.getInventory().setItem(i, sand);
					
					ret = true;
				}//End check for dirt
				
				
			}//End null check
			
		}//end for loop

		return ret;
		
	}
}
