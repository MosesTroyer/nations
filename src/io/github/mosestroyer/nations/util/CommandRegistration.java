package io.github.mosestroyer.nations.util;

import io.github.mosestroyer.nations.Nations;
import io.github.mosestroyer.nations.setup.SetupCommand;

public class CommandRegistration {
	
	public static void registerCommands(Nations nations){
		
		//setup commands
		nations.getCommand("createPedestal").setExecutor(new SetupCommand(nations));
		
		
		
		
	} //end registerCommands

} //end CommandRegistration
