package fr.hartania.xam4lor.connection;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class SetParameters {
	public SetParameters(Player p) {
		p.setGameMode(GameMode.SURVIVAL);
		p.setHealth(20);
		p.setFoodLevel(21);
		p.setExhaustion(5F);
		
		Location l = p.getWorld().getSpawnLocation().add(0, 1, 0);
		p.getPlayer().teleport(l);
	}
}
