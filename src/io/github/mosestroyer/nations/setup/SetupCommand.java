package io.github.mosestroyer.nations.setup;

import io.github.mosestroyer.nations.Nations;
import io.github.mosestroyer.nations.util.HelperFunctions;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SetupCommand implements CommandExecutor {
	
	private final Nations nations;
	
	public SetupCommand(Nations nations){
		this.nations = nations;
	} //end constructor

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(command.getName().equalsIgnoreCase("createNationsBoard")){
			if(HelperFunctions.commandCheck(sender, command, label, args, nations, -1, true, false))
				return createNationsBoard(sender, command, label, args);
			
		} else if(command.getName().equalsIgnoreCase("createPedestal")){
			//TODO(moses)
			//Check for nations board before letting this command run
			if(HelperFunctions.commandCheck(sender, command, label, args, nations, 1, true, false))
					return createPedestal(sender, command, label, args);
		}
		
		return false;
	} //end onCommand
	
	private boolean createNationsBoard(CommandSender sender, Command command, String label, String[] args){
		
		return false;
	} //end createNationsBoard
	
	private boolean createPedestal(CommandSender sender, Command command, String label, String[] args){
			
		return false;
	} //end createPedestal

} //end SetupCommand class
