package fr.hartania.xam4lor.games;

import java.util.logging.Logger;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;

import fr.hartania.xam4lor.games.icebow.IceBowCoordonates;

public class TestCoordonates {
	public Logger log = Logger.getLogger("Minecraft");
	
	public TestCoordonates(PlayerTeleportEvent ev) {
		Player p = ev.getPlayer();
		double X = ev.getTo().getX();
		double Z = ev.getTo().getZ();
		
		//ICE BOW
		if((-795.0 < X) && (X < -811.0) && (-191.0 < Z) && (Z < -207.0)) { //on teste les coordonnées en X et Z
			new IceBowCoordonates(p);
		}
	}
}
