package io.github.mosestroyer.nations.classes;

public class AvailableClasses {

	public CharacterClass[] getClasses(){
		CharacterClass[] classes = new CharacterClass[13];
		classes[0] = new Acrobat();
		classes[1] = new Alchemist();
		classes[2] = new Archer();
		classes[3] = new Blacksmith();
		classes[4] = new King();
		classes[5] = new Knight();
		classes[6] = new Lumberjack();
		classes[7] = new Mage();
		classes[8] = new Miner();
		classes[9] = new Peasant();
		classes[10] = new Pyromaniac();
		classes[11] = new Swimmer();
		classes[12] = new Warrior();

		return classes;
	}
}
