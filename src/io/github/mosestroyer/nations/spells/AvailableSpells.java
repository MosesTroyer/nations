package io.github.mosestroyer.nations.spells;

import io.github.mosestroyer.nations.spells.spellbooks.ArrowStorm;
import io.github.mosestroyer.nations.spells.spellbooks.Crush;
import io.github.mosestroyer.nations.spells.spellbooks.DigTunnel;
import io.github.mosestroyer.nations.spells.spellbooks.FireBall;
import io.github.mosestroyer.nations.spells.spellbooks.GetExp;
import io.github.mosestroyer.nations.spells.spellbooks.GiveSpellbook;
import io.github.mosestroyer.nations.spells.spellbooks.GrowCrops;
import io.github.mosestroyer.nations.spells.spellbooks.Heal;
import io.github.mosestroyer.nations.spells.spellbooks.LavaToWater;
import io.github.mosestroyer.nations.spells.spellbooks.LightningStrike;
import io.github.mosestroyer.nations.spells.spellbooks.Nourish;
import io.github.mosestroyer.nations.spells.spellbooks.Suicide;
import io.github.mosestroyer.nations.spells.spellbooks.TeleportSpawn;
import io.github.mosestroyer.nations.spells.spellbooks.Transmute;
import io.github.mosestroyer.nations.spells.spellbooks.WaterToLava;

/*
 * Creates a list of spells so that we can reference a "book" to take specific
 * spells from. They are stored in a Spellbook arry, since all spells inherit Spellbook.
 * 
 */

public class AvailableSpells {
	static Spellbook sb[];

	
	public static Spellbook[] getSpells(){
		sb = new Spellbook[15]; //Change depending on # of spells
		sb[0] = new ArrowStorm();
		sb[1] = new DigTunnel();
		sb[2] = new FireBall();
		sb[3] = new GiveSpellbook();
		sb[4] = new GrowCrops();
		sb[5] = new Heal();
		sb[6] = new Suicide();
		sb[7] = new TeleportSpawn();
		sb[8] = new Transmute();
		sb[9] = new WaterToLava();
		sb[10] = new LightningStrike();
		sb[11] = new Nourish();
		sb[12] = new GetExp();
		sb[13] = new Crush();
		sb[14] = new LavaToWater();
		return sb; //returns the book
	}
	
	public static Spellbook[] getApprovedSpells() {
		sb = new Spellbook[13]; //Change depending on # of spells
		sb[0] = new ArrowStorm();
		sb[1] = new FireBall();
		sb[2] = new GrowCrops();
		sb[3] = new Heal();
		sb[4] = new Suicide();
		sb[5] = new TeleportSpawn();
		sb[6] = new Transmute();
		sb[7] = new WaterToLava();
		sb[8] = new LightningStrike();
		sb[9] = new Nourish();
		sb[10] = new GetExp();
		sb[11] = new Crush();
		sb[12] = new LavaToWater();
		return sb; //returns the book
	} //end getApprovedSpells
}
