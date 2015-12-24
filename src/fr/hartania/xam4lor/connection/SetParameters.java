package fr.hartania.xam4lor.connection;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import fr.hartania.xam4lor.locations.MainLocations;

public class SetParameters {
	public SetParameters(Player p) {
		p.setGameMode(GameMode.SURVIVAL);
		p.setHealth(20);
		p.setFoodLevel(21);
		p.setExhaustion(5F);
		
		p.getPlayer().teleport(MainLocations.spawn);
	}
}
