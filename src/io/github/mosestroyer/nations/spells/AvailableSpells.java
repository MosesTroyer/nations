package io.github.mosestroyer.nations.spells;

import io.github.mosestroyer.nations.spells.spellbooks.ArrowStorm;
import io.github.mosestroyer.nations.spells.spellbooks.DigTunnel;
import io.github.mosestroyer.nations.spells.spellbooks.FireBall;
import io.github.mosestroyer.nations.spells.spellbooks.GiveSpellbook;
import io.github.mosestroyer.nations.spells.spellbooks.GrowCrops;
import io.github.mosestroyer.nations.spells.spellbooks.Heal;
import io.github.mosestroyer.nations.spells.spellbooks.LightningStrike;
import io.github.mosestroyer.nations.spells.spellbooks.Suicide;
import io.github.mosestroyer.nations.spells.spellbooks.TeleportSpawn;
import io.github.mosestroyer.nations.spells.spellbooks.Transmute;
import io.github.mosestroyer.nations.spells.spellbooks.WaterToLava;

public class AvailableSpells {
	static Spellbook sb[];

	
	public static Spellbook[] getSpells(){
		sb = new Spellbook[11]; //Change depending on # of spells
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
		return sb;
	}
}
