package fr.hartania.xam4lor.connection;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class SetParameters {
	public SetParameters(Player p) {
		p.setGameMode(GameMode.SURVIVAL);
		p.setHealth(20);
		p.setFoodLevel(21);
		p.setExhaustion(5F);
		
		p.getPlayer().teleport(new Location(Bukkit.getServer().getWorlds().get(0), -794, 5, -280));
	}
}
