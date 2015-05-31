package io.github.mosestroyer.nations.nation;

import org.bukkit.DyeColor;


//Object for Nation information storage
public class Nation {
	
	private String name;
	private String color;
	
	public Nation(){
		
	} //end Nation
	
	public Nation(String name, String color){
		setName(name);
		setColor(color);
	} //end Nation populated constructor
	
	public String getName(){
		return name;
	} //end getName
	
	public void setName(String name){
		this.name = name;
	} //end setName
	
	public String getColor(){
		return color;
	} //end getColor
	
	public void setColor(String color){
		this.color = color;
	} //end setColor
	
	public DyeColor getDyeColor(){
		return DyeColor.valueOf(color);
	} //get DyeColor
	

} //end Nation Class
