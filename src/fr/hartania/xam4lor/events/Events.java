package fr.hartania.xam4lor.events;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
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
import fr.hartania.xam4lor.menus.DiamondGui;
import fr.hartania.xam4lor.menus.EmeraldGui;

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
		if(ev.getPlayer().isOp()) {
			ev.getPlayer().setGameMode(GameMode.CREATIVE);
		}
		ev.setJoinMessage(ChatColor.RED + m.getServerName() + ChatColor.GREEN + "Bienvenu(e) à " + ev.getPlayer().getName() + " sur " + ChatColor.UNDERLINE + "Hartania" + ChatColor.RESET + ChatColor.GREEN + " !!");
		ev.getPlayer().sendMessage(ChatColor.RED + m.getServerName() + ChatColor.GREEN + "Visite notre site WEB : " + ChatColor.BLUE + "http://xam4lor.890m.com");
		new GiveCustomInventory(ev.getPlayer());
		new SetParameters(ev.getPlayer());
		ev.getPlayer().getWorld().getBlockAt(-866, 5, -261).setType(Material.AIR);
		ev.getPlayer().getWorld().getBlockAt(-866, 5, -261).setType(Material.REDSTONE_BLOCK);
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
			new EmeraldGui(ev.getPlayer());
		}
		else if(ev.getPlayer().getItemInHand().isSimilar(GiveCustomInventory.getCommandBlock())) {
			new DiamondGui(ev.getPlayer());
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
				else if(ev.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.BLUE + "- MUSEE ARMORSTAND -")) {
					ev.getWhoClicked().teleport(new Location(Bukkit.getServer().getWorlds().get(0), -832, 5, -172));
					ev.getWhoClicked().closeInventory();
				}
				else if(ev.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.BLUE + "- ICE BOW -")) {
					ev.getWhoClicked().teleport(new Location(Bukkit.getServer().getWorlds().get(0), -803, 10, -199));
					ev.getWhoClicked().closeInventory();
				}
				else if(ev.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.BLUE + "- PVP ARENA -")) {
					ev.getWhoClicked().teleport(new Location(Bukkit.getServer().getWorlds().get(0), -812, 5, -384));
					ev.getWhoClicked().closeInventory();
				}
				else if(ev.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.BLUE + "- BOW SHOT -")) {
					ev.getWhoClicked().teleport(new Location(Bukkit.getServer().getWorlds().get(0), -857, 6, -210));
					ev.getWhoClicked().closeInventory();
				}
				else if(ev.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.BLUE + "- JUMP -")) {
					ev.getWhoClicked().teleport(new Location(Bukkit.getServer().getWorlds().get(0), -871, 35, 235));
					ev.getWhoClicked().closeInventory();
				}
				else if(ev.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.BLUE + "- SKYWARS -")) {
					ev.getWhoClicked().closeInventory();
				}
			}
			
			if (ev.getInventory().getName().equals("- Op Tools -")) {
				ev.setCancelled(true);
				
				if(ev.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "SURVIE")) {
					ev.getWhoClicked().setGameMode(GameMode.SURVIVAL);
					ev.getWhoClicked().closeInventory();
				}
				else if(ev.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "CREATIF")) {
					ev.getWhoClicked().setGameMode(GameMode.CREATIVE);
					ev.getWhoClicked().closeInventory();
				}
				else if(ev.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "SPECTATEUR")) {
					ev.getWhoClicked().setGameMode(GameMode.SPECTATOR);
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
