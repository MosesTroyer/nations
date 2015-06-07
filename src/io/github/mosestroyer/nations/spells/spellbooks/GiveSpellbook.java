package io.github.mosestroyer.nations.spells.spellbooks;


import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.BookMeta;

import io.github.mosestroyer.nations.Nations;
import io.github.mosestroyer.nations.spells.AvailableSpells;
import io.github.mosestroyer.nations.spells.Spellbook;



public class GiveSpellbook extends Spellbook{
	
	String name = "Give Spell Book";
	String description = "Give Spellbook: You shouldn't have this... Left click to cast";
	String msgText = "Gave the Spellbook!";
	int tier = 1;
	
	
	public GiveSpellbook(){ //Change from BlankSpell to name of the spell!
		super.setName(name);
		super.setDescription(description);
		super.setMsg(msgText);
		super.setTier(tier);
		
	}

	@Override
	public boolean doSpell(Player p, Nations n){
		for(int i=0; i<10; i++){
			Spellbook sb[] = AvailableSpells.getSpells();
			PlayerInventory inv = p.getInventory();
			
			ItemStack book = new ItemStack(Material.WRITTEN_BOOK, 1);
			BookMeta bd = (BookMeta) book.getItemMeta();
			
			Random rand = new Random();
			int ranNum = rand.nextInt(sb.length);
			
			bd.addPage(sb[ranNum].getDescription());
			bd.setAuthor("The Wizard");
			bd.setTitle(sb[ranNum].getName());
	
			book.setItemMeta(bd);
			inv.addItem(book);

		}
		
		return true;
		
	}
}
