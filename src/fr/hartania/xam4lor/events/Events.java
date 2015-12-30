package fr.hartania.xam4lor.events;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

import fr.hartania.xam4lor.connection.GiveCustomInventory;
import fr.hartania.xam4lor.connection.SetParameters;
import fr.hartania.xam4lor.games.TestCoordonates;
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
		ev.getPlayer().sendMessage(ChatColor.RED + m.getServerName() + ChatColor.GREEN + "Visite notre site WEB : " + ChatColor.BLUE + "http://xam4lor.890m.com");
		new GiveCustomInventory(ev.getPlayer());
		new SetParameters(ev.getPlayer());
		ev.getPlayer().getWorld().getBlockAt(-863, 4, -259).setType(Material.AIR);
		ev.getPlayer().getWorld().getBlockAt(-863, 4, -259).setType(Material.REDSTONE_BLOCK);
	}
	
	@EventHandler
	public void onPlayerTeleport(PlayerTeleportEvent ev) {
		new TestCoordonates(ev);
	}
	
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent ev) {
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
		try {
			if (ev.getInventory().getName().equals("- Menu -")) {
				ev.setCancelled(true);
				
				if(ev.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GOLD + "- SPAWN -")) {
					ev.getWhoClicked().teleport(new Location(Bukkit.getServer().getWorlds().get(0), -794, 5, -280));
					ev.getWhoClicked().closeInventory();
				}
				else if(ev.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.BLUE + "- ICE BOW -")) {
					ev.getWhoClicked().teleport(new Location(Bukkit.getServer().getWorlds().get(0), -803, 10, -199));
					ev.getWhoClicked().closeInventory();
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@EventHandler
	public void onWeatherChange(WeatherChangeEvent ev) {
		ev.setCancelled(true);
	}
}
