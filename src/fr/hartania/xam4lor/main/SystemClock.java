package fr.hartania.xam4lor.main;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;

public class SystemClock {
	public SystemClock() {
		
		for(Entity e : Bukkit.getServer().getWorlds().get(0).getEntities()) {
			if(e instanceof Item) {
				if(!MainClass.isEnterCoords(e, -450, -3000, -335, -368, 400, -252)) {
					e.remove();
				}
			}
		}
	}
}
