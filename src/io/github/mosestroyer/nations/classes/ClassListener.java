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
//		Connection c = DatabaseConnection.getConnection();
//	
//		if(PlayerDAO.getPlayerClass(c, p.getUniqueId()).equals("acrobat") ){
//			if(a.){
//				//do acrobat stuff
//			}
//		}
		
	}
}
