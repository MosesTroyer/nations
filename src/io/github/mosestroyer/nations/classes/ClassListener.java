package io.github.mosestroyer.nations.classes;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import io.github.mosestroyer.nations.Nations;
import io.github.mosestroyer.nations.playerActions.PlayerDAO;
import io.github.mosestroyer.nations.util.DatabaseConnection;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class ClassListener implements Listener{

	Nations n;
	
	public ClassListener(Nations n){
		this.n = n;
	}
	
	@EventHandler
	public void listen(PlayerInteractEvent e) throws SQLException{
		
		Action a = e.getAction();
		Player p = e.getPlayer();
		Connection c = DatabaseConnection.getConnection();
	
		//Acrobat Code
		if(PlayerDAO.getPlayerClass(c, p.getUniqueId()).equals("acrobat") ){
			if(a == Action.LEFT_CLICK_AIR){
				//do acrobat stuff
				p.sendMessage("acr");
			}
		}
		
		//Alchemist Code
		if(PlayerDAO.getPlayerClass(c, p.getUniqueId()).equals("alchemist") ){
			if(a == Action.LEFT_CLICK_AIR){
				//do acrobat stuff
				p.sendMessage("alc");
			}
		}
		
		//Archer Code
		if(PlayerDAO.getPlayerClass(c, p.getUniqueId()).equals("archer") ){
			if(a == Action.LEFT_CLICK_AIR){
				//do acrobat stuff
				p.sendMessage("arc");
			}
		}
		
		//Blacksmith code
		if(PlayerDAO.getPlayerClass(c, p.getUniqueId()).equals("blacksmith") ){
			if(a == Action.LEFT_CLICK_AIR){
				//do acrobat stuff
				p.sendMessage("bla");
			}
		}
		
		//King code
		if(PlayerDAO.getPlayerClass(c, p.getUniqueId()).equals("king") ){
			if(a == Action.LEFT_CLICK_AIR){
				//do acrobat stuff
				p.sendMessage("king");
			}
		}
		
		//Kinght Code
		if(PlayerDAO.getPlayerClass(c, p.getUniqueId()).equals("knight") ){
			if(a == Action.LEFT_CLICK_AIR){
				//do acrobat stuff
				p.sendMessage("knight");
			}
		}
		
		//Lumberjack Code
		if(PlayerDAO.getPlayerClass(c, p.getUniqueId()).equals("lumberjack") ){
			if(a == Action.LEFT_CLICK_AIR){
				//do acrobat stuff
				p.sendMessage("lumb");
			}
		}
		
		//Mage Code
		if(PlayerDAO.getPlayerClass(c, p.getUniqueId()).equals("mage") ){
			if(a == Action.LEFT_CLICK_AIR){
				//do acrobat stuff
				p.sendMessage("mag");
			}
		}
		
		//Miner Code
		if(PlayerDAO.getPlayerClass(c, p.getUniqueId()).equals("miner") ){
			if(a == Action.LEFT_CLICK_AIR){
				//do acrobat stuff
				p.sendMessage("min");
			}
		}
		
		//Peasant
		if(PlayerDAO.getPlayerClass(c, p.getUniqueId()).equals("peasant") ){
			if(a == Action.LEFT_CLICK_AIR){
				//do acrobat stuff
				p.sendMessage("Pea");
			}
		}
		
		//Pyromaniac Code
		if(PlayerDAO.getPlayerClass(c, p.getUniqueId()).equals("pyromaniac") ){
			if(a == Action.LEFT_CLICK_AIR){
				//do acrobat stuff
				p.sendMessage("pyr");
			}
		}
		
		//Swimmer Code
		if(PlayerDAO.getPlayerClass(c, p.getUniqueId()).equals("swimmer") ){
			if(a == Action.LEFT_CLICK_AIR){
				//do acrobat stuff
				p.sendMessage("swi");
			}
		}
		
		//Warrior Code
		if(PlayerDAO.getPlayerClass(c, p.getUniqueId()).equals("warrior") ){
			if(a == Action.LEFT_CLICK_AIR){
				//do acrobat stuff
				p.sendMessage("war");
			}
		}
		
	}
}
