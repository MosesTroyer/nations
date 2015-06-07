package io.github.mosestroyer.nations.spells;

import io.github.mosestroyer.nations.Nations;

import org.bukkit.entity.Player;

public class Spellbook {

	String name;
	String id;
	private String description;
	private String msgText;
	private int tier;
	
	//TODO
	//Create spellbook parent object

	public void doSpell(Player p, Nations n) {
		// TODO Auto-generated method stub		
	}

	public void doSpell(Player p, Nations n, int s) {
		// TODO Auto-generated method stub		
	}
	
	public String getName(){
		return name;
	}
	public String getId(){
		return id;
	}
	public String getDescription(){
		return description;
	}
	
	public String getMsg(){
		return msgText;
	}
	public int getTier(){
		return tier;
	}
	public void setName(String n){
		name = n;
	}
	public void setId(String i){
		id = i;
	}
	public void setDescription(String s1){
		description = s1;
	}
	
	public void setMsg(String s2){
		msgText = s2;
	}
	public void setTier(int i1){
		tier = i1;
	}
}
