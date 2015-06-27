package io.github.mosestroyer.nations.spells;

import io.github.mosestroyer.nations.Nations;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class SpellsListener implements Listener{

	Nations n;
	
	public SpellsListener(Nations n){
		this.n = n;
	}
	
	@EventHandler
	public void castSpell(PlayerInteractEvent e){
		
		Action splAct = e.getAction(); //gets the player's action
		Player p = e.getPlayer(); //gets the player
		Spellbook sb[] = AvailableSpells.getSpells(); //Uses our spellbook to gather spells 
		
		//handles if spellbook is used
		if(splAct == Action.LEFT_CLICK_AIR || splAct == Action.LEFT_CLICK_BLOCK){
			
			if(p.getItemInHand().getType().equals(Material.WRITTEN_BOOK)){
				
				//create a book object so we can work with it and check values
				ItemStack book = p.getItemInHand();
				BookMeta bm = (BookMeta) book.getItemMeta();

				for(int i=0; i < sb.length; i++){ //go through the spellbook and see if this book is a correct spell
					if(bm.getTitle().equals(sb[i].getName()) && bm.getPage(1).equals(sb[i].getDescription())){
						if(sb[i].doSpell(p, n)){ //does the spell if all checks come back
							
							//decrements the amount of books, and turns it to 0 if the current # of  books is 1
							if(book.getAmount() > 1){
								book.setAmount(book.getAmount() - 1);
							}else{
								p.getInventory().remove(book);
							}
							
							//tell the player and the server that a player casted a spell
							n.getLogger().info(p.getPlayerListName() +" " + sb[i].getMsg().toLowerCase());
							p.sendMessage(sb[i].getMsg());
						}

					}
				}
			}
		}
		
	}
}
