package io.github.mosestroyer.nations.spells.spellbooks;



import java.util.ArrayList;








import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

import io.github.mosestroyer.nations.Nations;
import io.github.mosestroyer.nations.spells.AvailableSpells;
import io.github.mosestroyer.nations.spells.Spellbook;



public class GiveSpellbook extends Spellbook{
	
	String name = "Give Spell Book";
	String id = "givespellbook-nationsplugin-56732437654";
	String description = "Gives the player a spellbook";
	String msgText = "Gave the Spellbook!";
	int tier = 1;
	
	
	public GiveSpellbook(){ //Change from BlankSpell to name of the spell!
		super.setName(name);
		super.setId(id);
		super.setDescription(description);
		super.setMsg(msgText);
		super.setTier(tier);
		
	}

	@Override
	public void doSpell(Player p, Nations n){
		Spellbook sb[] = AvailableSpells.getSpells();
		
		n.getLogger().info(msgText);
		p.sendMessage(msgText);
		
		PlayerInventory inv = p.getInventory();
		
		ItemStack book = new ItemStack(Material.WRITTEN_BOOK, 1);
		BookMeta bd = (BookMeta) book.getItemMeta();
		
		Random rand = new Random();
		int ranNum = rand.nextInt(10);
		
		bd.addPage(sb[ranNum].getId());
		bd.setAuthor("The Wizard");
		bd.setTitle(sb[ranNum].getName());

		book.setItemMeta(bd);
		inv.addItem(book);
		p.updateInventory();
		
	}
}
