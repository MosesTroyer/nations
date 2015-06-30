package io.github.mosestroyer.nations.spells.spellbooks;

import org.bukkit.Material;
import org.bukkit.entity.Player;



import org.bukkit.inventory.ItemStack;

import io.github.mosestroyer.nations.Nations;
import io.github.mosestroyer.nations.spells.Spellbook;

public class Transmute extends Spellbook{
	
	String name = "Transmutation";
	String description = "Transmuation: A spell that transmutes the iron in your inventory to gold! Just left click to cast";
	String msgText = "Did the alchemy!";
	int tier = 1;
	
	public Transmute(){ 
		super.setName(name);
		super.setDescription(description);
		super.setMsg(msgText);
		super.setTier(tier);
	}

	@Override
	public boolean doSpell(Player p, Nations n){
		boolean ret = false;
		
		if(p.getInventory().contains(Material.IRON_INGOT)){
			
			for(int i=0; i < p.getInventory().getSize(); i++){
				if(p.getInventory().getItem(i) != null){
					if(p.getInventory().getItem(i).getType().equals(Material.IRON_INGOT)){
						ItemStack iron = p.getInventory().getItem(i);
						ItemStack gold = new ItemStack(Material.GOLD_INGOT);
						gold.setAmount(iron.getAmount());
						
						p.getInventory().setItem(i, gold);
						
						ret = true;
					}
				}
			}
			
		}

		return ret;

	}
}
