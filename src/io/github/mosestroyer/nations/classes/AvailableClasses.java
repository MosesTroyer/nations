package io.github.mosestroyer.nations.classes;

public class AvailableClasses {

	CharacterClass[] getAvailableClasses(){
		
		CharacterClass[] classes = new CharacterClass[13]; //Change number depending on how many classes!
		
		classes[0] = new King();
		classes[1] = new Peasant();
		classes[2] = new Archer();
		classes[3] = new Warrior();
		classes[4] = new Acrobat();
		classes[5] = new Alchemist();
		classes[6] = new Blacksmith();
		classes[7] = new Knight();
		classes[8] = new Lumberjack();
		classes[9] = new Mage();
		classes[10] = new Miner();
		classes[11] = new Pyromaniac();
		classes[12] = new Swimmer();

		
		
		return classes;
		
	}
	
	
}
