package io.github.mosestroyer.nations.classes;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public abstract class CharacterClass implements Listener{

	public abstract String getName();
	
	@EventHandler
	public abstract void listen();
}
