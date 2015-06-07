package io.github.mosestroyer.nations.spells.spellbooks;



import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import io.github.mosestroyer.nations.Nations;
import io.github.mosestroyer.nations.spells.Spellbook;



public class Transmute extends Spellbook{
	
	String name = "Transmutation";
	String id = "transmute-nationsplugin-56732437654";
	String description = "A spell that transmutes iron to gold!";
	String msgText = "Did the alchemy!";
	int tier = 1;
	
	public Transmute(){ 
		super.setName(name);
		super.setId(id);
		super.setDescription(description);
		super.setMsg(msgText);
		super.setTier(tier);
	}

	@Override
	public void doSpell(Player p, Nations n){
		n.getLogger().info(msgText);
		p.sendMessage(msgText);
		
		


	}
}
