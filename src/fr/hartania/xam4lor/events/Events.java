package fr.hartania.xam4lor.events;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

import fr.hartania.xam4lor.connection.GiveCustomInventory;
import fr.hartania.xam4lor.connection.SetParameters;
import fr.hartania.xam4lor.main.MainClass;
import fr.hartania.xam4lor.menus.BoussoleGui;

public class Events implements Listener {
	public Logger log = Logger.getLogger("Minecraft");
	MainClass m = null;

	public Events(MainClass m) {
		this.m = m;
	}
	
	@EventHandler
	public void onBlockPlaceEvent(final BlockPlaceEvent ev) {		
		if(!ev.getPlayer().isOp()) {
			ev.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent ev) {
		ev.setJoinMessage(ChatColor.RED + m.getServerName() + ChatColor.GREEN + "Bienvenu(e) à " + ev.getPlayer().getName() + " sur " + ChatColor.UNDERLINE + "Hartania" + ChatColor.RESET + ChatColor.GREEN + " !!");
		new GiveCustomInventory(ev.getPlayer());
		new SetParameters(ev.getPlayer());
	}
	
	@EventHandler
	public void onBlockBreakEvent(final BlockBreakEvent ev) {
		if(!ev.getPlayer().isOp()) {
			ev.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent ev) {
		if(ev.getPlayer().getItemInHand().isSimilar(GiveCustomInventory.getBoussole())) {
			new BoussoleGui(ev.getPlayer());
		}
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent ev) {
		if (ev.getInventory().getName().equals("- Menu -")) {
			if(ev.getCurrentItem().getItemMeta().getDisplayName().equals("- SPAWN -")) {
				Location l = ev.getWhoClicked().getWorld().getSpawnLocation().add(0, 1, 0);
				ev.getWhoClicked().teleport(l);
				ev.getWhoClicked().closeInventory();
			}
			else {
				this.log.info(ev.getCurrentItem().getItemMeta().getDisplayName());
			}
			
			ev.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onWeatherChange(WeatherChangeEvent ev) {
		ev.setCancelled(true);
	}
}
