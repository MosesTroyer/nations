package io.github.mosestroyer.nations.util;

import io.github.mosestroyer.nations.Nations;
import io.github.mosestroyer.nations.playerActions.PlayerCommand;
import io.github.mosestroyer.nations.setup.SetupCommand;

public class CommandRegistration {
	
	public static void registerCommands(Nations nations){
		
		//setup commands
		nations.getCommand("createNationsBoard").setExecutor(new SetupCommand(nations));
		nations.getCommand("createPedestal").setExecutor(new SetupCommand(nations));
		nations.getCommand("createNation").setExecutor(new SetupCommand(nations));
		nations.getCommand("showNations").setExecutor(new SetupCommand(nations));
		nations.getCommand("removeNation").setExecutor(new SetupCommand(nations));
		
		//playerActions commands
		nations.getCommand("joinNation").setExecutor(new PlayerCommand(nations));
		nations.getCommand("leaveNation").setExecutor(new PlayerCommand(nations));
		//nations.getCommand("setClass").setExecutor(new PlayerCommand(nations));
		
		//Spells Commands
		//nations.getCommand("givespellbook").setExecutor(new SpellsCommand(nations));
		
	} //end registerCommands

} //end CommandRegistration
