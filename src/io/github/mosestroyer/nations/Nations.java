package io.github.mosestroyer.nations;


import java.sql.Connection;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.mosestroyer.nations.nation.ChestPedestal;
import io.github.mosestroyer.nations.nation.NationDAO;
import io.github.mosestroyer.nations.setup.SetupDAO;
import io.github.mosestroyer.nations.spells.AvailableSpells;
import io.github.mosestroyer.nations.spells.Spellbook;
import io.github.mosestroyer.nations.util.CommandRegistration;
import io.github.mosestroyer.nations.util.DatabaseConnection;
import io.github.mosestroyer.nations.util.ListenerRegistration;

public class Nations extends JavaPlugin implements Listener{

	public void onEnable(){
		
		try {
			
			CommandRegistration.registerCommands(this);
			ListenerRegistration.registerListeners(this);
			getServer().getPluginManager().registerEvents(this, this);
			
			Connection c = DatabaseConnection.getConnection();
			SetupDAO.checkForTables(c);
			DatabaseConnection.closeConnection(c);
			
			Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() { 
	            public void run() {      
	            	addToChest(0, Material.COBBLESTONE, 1);
	            }
	        }, 0, 60*20L);
			
			Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() { 
	            public void run() {      
	            	addToChest(1, Material.IRON_INGOT, 1);
	            }
	        }, 0, 4*60*60*20L);
			
			Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() { 
	            public void run() {      
	            	addToChest(2, Material.DIAMOND, 1);
	            }
	        }, 0, 8*60*60*20L);
			
			Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() { 
	            public void run() {      
	            	addToChest(3, Material.BOOK, 1);
	            }
	        }, 0, 12*60*60*20L);
			
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
	
	//For the timer events to add items
	public void addToChest(int position, Material mat, int amount) {
		try {
    		
	    	Connection c = DatabaseConnection.getConnection();
	    	
	    	List<ChestPedestal> chests = NationDAO.getChestPedestalsByPosition(c, position);

	    	for(ChestPedestal chestPedestal : chests) {
	    		int flagCount = NationDAO.getFlagCount(c, chestPedestal.getName());
	    		
	    		if(flagCount > 0){
	    			
	    			World world = Bukkit.getServer().getWorld("world");

	    			Location loc = new Location(world, chestPedestal.getX(), chestPedestal.getY(), chestPedestal.getZ());
	    			
	    			Block block = world.getBlockAt(loc);
	    			
	    			Chest chest = (Chest) block.getState();
	    			Inventory inventory = chest.getBlockInventory();
	    			
	    			if(mat == Material.BOOK){
	    				for (int i = 0; i < flagCount; i++) {
		    				Spellbook sb[] = AvailableSpells.getApprovedSpells();
		    				
		    				ItemStack book = new ItemStack(Material.WRITTEN_BOOK, 1);
		    				BookMeta bd = (BookMeta) book.getItemMeta();
		    				
		    				Random rand = new Random();
		    				int ranNum = rand.nextInt(sb.length);
		    				
		    				bd.addPage(sb[ranNum].getDescription());
		    				bd.setAuthor("The Wizard");
		    				bd.setTitle(sb[ranNum].getName());
		    		
		    				book.setItemMeta(bd);
		    				
		    				inventory.addItem(book);
	    				}
	    			} else 
	    				inventory.addItem(new ItemStack(mat, amount * flagCount));
	    			
	    		}
	    		
	    	}
	    	
	        DatabaseConnection.closeConnection(c);
    	} catch (Exception e) {
    		getLogger().info(e.getMessage());
    	}
		
	} //end addToChest
	

} //end Nations class
