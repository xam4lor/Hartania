package fr.hartania.xam4lor.games.icebow;

import java.util.Collection;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class IceBowCoordonates {
	public Logger log = Logger.getLogger("Minecraft");
	public Collection<? extends Player> ps = Bukkit.getOnlinePlayers();
	
	@SuppressWarnings("null")
	public IceBowCoordonates(Player p) {
		double X = p.getLocation().getX();
		double Z = p.getLocation().getZ();
		Collection<Player> playersWaiting = null;
		
		for(Player pp : ps) {
			if((-795.0 < X) && (X < -811.0) && (-191.0 < Z) && (Z < -207.0)) { //on teste les coordonnées en X et Z
				playersWaiting.add(pp.getPlayer());
			}
		}
		
		if(playersWaiting.size() == 2) {
			this.log.info("Partie IceBowCoordonates begin");
		}
	}
}
