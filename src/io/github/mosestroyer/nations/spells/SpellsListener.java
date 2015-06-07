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
		
		Action splAct = e.getAction();
		Player p = e.getPlayer();
		Spellbook sb[] = AvailableSpells.getSpells();
		
		//handles if spellbook is used
		if(splAct == Action.LEFT_CLICK_AIR || splAct == Action.LEFT_CLICK_BLOCK){
			
			if(p.getItemInHand().getType().equals(Material.WRITTEN_BOOK)){
				
				ItemStack book = p.getItemInHand();
				BookMeta bm = (BookMeta) book.getItemMeta();

				for(int i=0; i < sb.length; i++){
					if(bm.getTitle().equals(sb[i].getName()) && bm.getPage(1).equals(sb[i].getDescription())){
						if(sb[i].doSpell(p, n)){
							
							if(book.getAmount() > 1){
								book.setAmount(book.getAmount() - 1);
							}else{
								p.getInventory().remove(book);
							}
							
							n.getLogger().info(p.getPlayerListName() +" " + sb[i].getMsg().toLowerCase());
							p.sendMessage(sb[i].getMsg());
						}

					}
				}
			}
		}
		
		//handles so that spellbooks can't be read
		if(splAct == Action.RIGHT_CLICK_AIR || splAct == Action.RIGHT_CLICK_BLOCK){
			
			if(p.getItemInHand().getType().equals(Material.WRITTEN_BOOK)){
				
				ItemStack book = p.getItemInHand();
				BookMeta bm = (BookMeta) book.getItemMeta();

				for(int i=0; i < sb.length; i++){
					if(bm.getTitle().equals(sb[i].getName()) && bm.getPage(1).equals(sb[i].getDescription())){
						p.openInventory(p.getInventory());
						p.closeInventory();
					}
				}
			}
		}
		
	}
}
