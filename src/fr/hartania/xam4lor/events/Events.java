package fr.hartania.xam4lor.events;

import java.util.logging.Logger;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

import fr.hartania.xam4lor.main.MainClass;

public class Events implements Listener {
	public Logger log = Logger.getLogger("Minecraft");
	MainClass p = null;
	String [] Deads;
	int numberCaseDead = 0;
	String [] playerinteam = null;

	public Events(MainClass p) {
		this.p = p;
	}

	@EventHandler
	public void onBlockBreakEvent(final BlockBreakEvent ev) {
		if(!this.p.getConfig().getBoolean("partiebegin")) {
			ev.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onBlockPlaceEvent(final BlockPlaceEvent ev) {		
		if(!this.p.getConfig().getBoolean("partiebegin")) {
			ev.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent ev) {
		if (ev.getInventory().getName().equals("- Teams -")) {
			ev.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onEntityDamage(final EntityDamageEvent ev) {
		if (ev.getEntity() instanceof Player) {			
			if(!this.p.getConfig().getBoolean("partiebegin")) {
				ev.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onWeatherChange(WeatherChangeEvent ev) {
		if (!p.getConfig().getBoolean("weather_change")) {
			ev.setCancelled(true);
		}
	}
}
