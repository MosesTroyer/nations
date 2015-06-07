package io.github.mosestroyer.nations;


import java.sql.Connection;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.mosestroyer.nations.setup.SetupDAO;
import io.github.mosestroyer.nations.spells.AvailableSpells;
import io.github.mosestroyer.nations.spells.Spellbook;
import io.github.mosestroyer.nations.util.CommandRegistration;
import io.github.mosestroyer.nations.util.DatabaseConnection;

public class Nations extends JavaPlugin implements Listener{

	public void onEnable(){
		
		try {
			
			CommandRegistration.registerCommands(this);
			getServer().getPluginManager().registerEvents(this, this);
			
			Connection c = DatabaseConnection.getConnection();
			SetupDAO.checkForTables(c);
			DatabaseConnection.closeConnection(c);
		
			getLogger().info("Nations successfully enabled!");
		} catch (Exception e) {
			getLogger().info("Nations failed to start!");
			getLogger().info((e.getStackTrace())[0].toString());
		}
		
	} //end onEnable
	
	public void onDisable(){
		
		try {
			
			getLogger().info("Nations successfully disabled!");
			
		} catch (Exception e) {
			
			getLogger().info("Nations failed to disable successfully!");
			
		}	

	} //end onDisable
	
	
	//Right click listener
	@EventHandler
	public void RCListener(PlayerInteractEvent event){
		Action la = event.getAction();
		Player p = event.getPlayer();
		Spellbook sb[] = AvailableSpells.getSpells();
		
		
		if(la == Action.RIGHT_CLICK_AIR || la == Action.RIGHT_CLICK_BLOCK){
			
			if(p.getItemInHand().getType().equals(Material.WRITTEN_BOOK)){

				ItemStack book = p.getItemInHand();
				BookMeta bm = (BookMeta) book.getItemMeta();
				getLogger().info(bm.getTitle() + " " + bm.getPage(1));
				getLogger().info(sb[0].getName() + " " + sb[0].getId());
				
				for(int i=0; i<sb.length; i++){
					if(bm.getTitle().equals(sb[i].getName()) && bm.getPage(1).equals(sb[i].getId())){
						p.getInventory().removeItem(book);
						p.openInventory(p.getInventory());
						p.closeInventory();
						sb[i].doSpell(p, this);
					}
				}

			}
		}
	} //end rclistener

} //end Nations class
