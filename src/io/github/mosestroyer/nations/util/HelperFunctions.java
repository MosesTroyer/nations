package io.github.mosestroyer.nations.util;

import io.github.mosestroyer.nations.Nations;
import io.github.mosestroyer.nations.util.DatabaseConnection;

import java.security.InvalidParameterException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/*  HelperFunctions is a class to be used for quality of life
 *  functions and tricks, like common conversions or tasks 
 *  used across the plugin.
 */

public class HelperFunctions {

	//Returns true or false if the conditions are met and prints error messages to the intended parties.
	//Pass true to the variables if commandCheck needs to check them, and an int if that needs to be checked.
	//If the arglength does not need to be checked, pass -1
	public static boolean commandCheck(CommandSender sender, Command command, String label, String[] args, 
								Nations nations, int arglength, boolean playerRequired, boolean serverRequired){
		
		if(playerRequired && serverRequired)
			throw new InvalidParameterException("Both playerRequired and serverRequired cannot be true.");
		
		if(playerRequired && !(sender instanceof Player)){
			nations.getLogger().info("You must be a player to execute this command!");
			return false;
		} else if (serverRequired && sender instanceof Player){
			sender.sendMessage("You must execute this command from the server console!");
			return false;
		}
		
		if(arglength > -1){
			if (args.length < arglength){
				if(sender instanceof Player){
					sender.sendMessage("Not enough arguments for this command!");
					return false;
				} else {
					nations.getLogger().info("Not enough arguments for this command!");
					return false;
				}
			}				
		}
			
		return true;
	} //end commandCheck

	//Sends a commandSender a message, depending on if they are the server console or a player
	public static void sendSenderMessage(Nations nations, CommandSender sender, String message){
		if(sender instanceof Player)
			sender.sendMessage(message);
		else
			nations.getLogger().info(message);	
	} //end sendSenderMessage
	
} //end HelperFunctions class
