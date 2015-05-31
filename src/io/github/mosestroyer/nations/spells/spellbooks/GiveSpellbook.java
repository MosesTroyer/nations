package io.github.mosestroyer.nations.spells.spellbooks;



import java.util.ArrayList;






import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

import io.github.mosestroyer.nations.Nations;
import io.github.mosestroyer.nations.spells.Spellbook;



public class GiveSpellbook extends Spellbook{
	
	String description = "Gives the player a spellbook";
	String msgText = "Gave the Spellbook!";
	int tier = 1;
	
	public GiveSpellbook(){ //Change from BlankSpell to name of the spell!
		super.setDescription(description);
		super.setMsg(msgText);
		super.setTier(tier);
	}

	@Override
	public void doSpell(Player p, Nations n){
		n.getLogger().info(msgText);
		p.sendMessage(msgText);
		
		PlayerInventory inv = p.getInventory();
		ArrayList<String> pages = new ArrayList<String>();
		pages.add("Arrow Storm: " + "\nBrings a rain of arrows upon your foes!");
		
		ItemStack book = new ItemStack(Material.WRITTEN_BOOK, 1);
		BookMeta bd = (BookMeta) book.getItemMeta();
		bd.setTitle("Spellbook");
		bd.setAuthor("Wizard Bill");
		bd.addPage(pages.get(0));
		//bd.

		inv.addItem(book);
		p.updateInventory();
		
	}
}
