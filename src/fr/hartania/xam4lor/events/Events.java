package fr.hartania.xam4lor.events;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

import fr.hartania.xam4lor.connection.GiveCustomInventory;
import fr.hartania.xam4lor.connection.SetParameters;
import fr.hartania.xam4lor.grades.GradeSystem;
import fr.hartania.xam4lor.main.MainClass;
import fr.hartania.xam4lor.menus.DiamondGui;
import fr.hartania.xam4lor.menus.EmeraldGui;

public class Events implements Listener {
	public Logger log = Logger.getLogger("Minecraft");
	MainClass m = null;
	
	public Events(MainClass m) {
		this.m = m;
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onBlockPlaceEvent(final BlockPlaceEvent ev) {		
		if(!ev.getPlayer().isOp()) {
			ev.setCancelled(true);
		}
	}
	
    @EventHandler(priority = EventPriority.LOWEST)
    public void onAsyncPlayerChat(AsyncPlayerChatEvent ev) {
        Player pl = ev.getPlayer();
        
        if(MainClass.isPlayerMute(pl)) {
        	ev.setCancelled(true);
        	pl.sendMessage(ChatColor.RED + MainClass.getServerName() + "Vous êtes mute.");
        }
    }
	
    @EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerJoin(PlayerJoinEvent ev) {
		if(ev.getPlayer().isOp()) {
			ev.getPlayer().setGameMode(GameMode.CREATIVE);
		}
		ev.setJoinMessage(ChatColor.RED + MainClass.getServerName() + ChatColor.GREEN + "Bienvenu(e) à " + ev.getPlayer().getDisplayName() + " sur " + ChatColor.UNDERLINE + "Hartania" + ChatColor.RESET + ChatColor.GREEN + " !!");
		ev.getPlayer().sendMessage(ChatColor.RED + MainClass.getServerName() + ChatColor.GREEN + "Visite notre site WEB : " + ChatColor.BLUE + "http://xam4lor.890m.com");
		
		new SetParameters(ev.getPlayer());
		new GiveCustomInventory(ev.getPlayer());
		
		ev.getPlayer().getWorld().getBlockAt(-866, 5, -261).setType(Material.AIR);
		ev.getPlayer().getWorld().getBlockAt(-866, 5, -261).setType(Material.REDSTONE_BLOCK);
		
		ev.getPlayer().setDisplayName(GradeSystem.getGradeColor(GradeSystem.getGrade(ev.getPlayer())) + "[" + GradeSystem.getGrade(ev.getPlayer()) + "] " + ev.getPlayer().getDisplayName() + ChatColor.RESET);
	}
	
    @EventHandler(priority = EventPriority.LOWEST)
	public void onPlayerRespawn(PlayerRespawnEvent ev) {
		new SetParameters(ev.getPlayer());
		new GiveCustomInventory(ev.getPlayer());
	}
	
    @EventHandler(priority = EventPriority.LOWEST)
	public void onBlockBreakEvent(final BlockBreakEvent ev) {
		if(!ev.getPlayer().isOp()) {
			ev.setCancelled(true);
		}
	}
	
    @EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerDeath(PlayerDeathEvent ev) {
		try {
			ev.getEntity().getPlayer().sendMessage(ChatColor.RED + MainClass.getServerName() + ChatColor.GREEN + "Vous êtes mort.");
			ev.setDeathMessage("");
		}
		catch(Exception e) {
			this.log.warning(MainClass.getServerName() + "Erreur lors de l'évènement 'PlayerDeathEvent'");
		}
	}
	
    @EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerInteract(PlayerInteractEvent ev) {
		if(ev.getPlayer().getItemInHand().isSimilar(GiveCustomInventory.getEmerald())) {
			new EmeraldGui(ev.getPlayer());
		}
		else if(ev.getPlayer().getItemInHand().isSimilar(GiveCustomInventory.getDiamond())) {
			new DiamondGui(ev.getPlayer());
		}
	}
	
    @EventHandler(priority = EventPriority.HIGHEST)
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
				else if(ev.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.BLUE + "- ICE SHOT -")) {
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
					ev.getWhoClicked().teleport(new Location(Bukkit.getServer().getWorlds().get(0), -871, 35, 253));
					ev.getWhoClicked().closeInventory();
				}
				else if(ev.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.BLUE + "- SKYWARS -")) {
					ev.getWhoClicked().teleport(new Location(Bukkit.getServer().getWorlds().get(0), -205, 63, -301));
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
				else if(ev.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "COMMANDS BLOCKS")) {
					ev.getWhoClicked().teleport(new Location(Bukkit.getServer().getWorlds().get(0), -865, 6, -252));
					ev.getWhoClicked().closeInventory();
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
    @EventHandler(priority = EventPriority.LOWEST)
	public void onWeatherChange(WeatherChangeEvent ev) {
		ev.setCancelled(true);
	}
}
