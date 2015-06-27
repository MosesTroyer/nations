package io.github.mosestroyer.nations.spells;

import io.github.mosestroyer.nations.Nations;

import org.bukkit.entity.Player;

public class Spellbook {

	//variables that each spell has unique; name, id, description, msgText, and tier.
	String name;
	String id;
	private String description;
	private String msgText; //Shows up as "Did the thing!"
	private int tier; 
	

	public boolean doSpell(Player p, Nations n) {
		return false; //Returns true if a spell is casted (overridden 100% of the time)
	}
	
	public String getName(){
		return name; //returns the name of the spell
	}
	public String getDescription(){
		return description; //returns description of the spell
	}
	
	public String getMsg(){
		return msgText; //returns msgText
	}
	public int getTier(){
		return tier; //returns tier
	}
	
	//we shouldn't call these ourselves or in any other code other than something that creates a 
	//physical spellbook. If values are changed, the spellbook may not work properly when it gets checked
	//during player interaction
	public void setName(String n){
		name = n; //sets name
	}
	public void setDescription(String s1){
		description = s1; //sets description
	}
	
	public void setMsg(String s2){
		msgText = s2; //sets msgText
	}
	public void setTier(int i1){
		tier = i1; //sets tier
	}
}
