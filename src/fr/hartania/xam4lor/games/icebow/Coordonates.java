package fr.hartania.xam4lor.games.icebow;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class Coordonates {
	public static Location getCoordonates(int nb) {
		/*
		 * Pour nb = {
		 * 	0 : spawn mini-jeu
		 * }
		 */
		
		if(nb == 0) {
			return new Location(Bukkit.getWorlds().get(0), -803, 8, -199);
		}
		
		else {
			return new Location(Bukkit.getWorlds().get(0), 0, 0, 0);
		}
	}
}
