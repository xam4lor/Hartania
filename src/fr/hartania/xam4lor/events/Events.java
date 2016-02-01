package fr.hartania.xam4lor.events;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;

import fr.hartania.xam4lor.connection.GiveCustomInventory;
import fr.hartania.xam4lor.connection.SetParameters;
import fr.hartania.xam4lor.errorSystem.OnErrorSystem;
import fr.hartania.xam4lor.games.skywars.EndSkyWars;
import fr.hartania.xam4lor.grades.GradeSystem;
import fr.hartania.xam4lor.mails.MailSystem;
import fr.hartania.xam4lor.main.MainClass;
import fr.hartania.xam4lor.menus.DiamondGui;
import fr.hartania.xam4lor.menus.EmeraldGui;
import fr.hartania.xam4lor.menus.GadgetsGui;
import fr.hartania.xam4lor.scoreboards.DisplayScoreboard;

public class Events implements Listener {
	public Logger log = Logger.getLogger("Minecraft");
	MainClass m = null;
	
	public Events(MainClass m) {
		this.m = m;
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onBlockPlaceEvent(final BlockPlaceEvent ev) {		
		if(!ev.getPlayer().isOp() && !MainClass.isEnterCoords(ev.getPlayer(), -450, -3000, -335, -368, 400, -252)) {
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
        else {
        	for(Player pp : Bukkit.getOnlinePlayers()) {
        		if(MainClass.isNotifMsgOn(pp) && pp != pl) {
        			pp.playSound(pp.getPlayer().getLocation(), Sound.NOTE_BASS, 200, 1);
        		}
        	}
        }
    }
    
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerMove(PlayerMoveEvent ev) {
    	if(MainClass.plDeathsInSkywars.contains(ev.getPlayer().getName())) {
    		if(!MainClass.isEnterCoords(ev.getPlayer(), -450, -3000, -335, -368, 400, -252)) {
    			ev.getPlayer().teleport(new Location(Bukkit.getServer().getWorlds().get(0), -408, 60, -285));
    			ev.getPlayer().sendMessage(ChatColor.RED + MainClass.getServerName() + "Ne vous éloignez pas trop.");
    		}
    	}
    }
	
    @EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerJoin(PlayerJoinEvent ev) {
		if(ev.getPlayer().isOp()) {
			ev.getPlayer().setGameMode(GameMode.CREATIVE);
			ev.getPlayer().sendMessage(ChatColor.RED + MainClass.getServerName() + ChatColor.GREEN + "N'oublie pas de faire la commande '/actus list' pour t'informer de l'actualité !");
		}
		ev.setJoinMessage(ChatColor.RED + MainClass.getServerName() + ChatColor.GREEN + "Bienvenu(e) à " + ev.getPlayer().getDisplayName() + " sur " + ChatColor.UNDERLINE + "Hartania" + ChatColor.RESET + ChatColor.GREEN + " !!");
		ev.getPlayer().sendMessage(ChatColor.RED + MainClass.getServerName() + ChatColor.GREEN + "Visite notre site WEB : " + ChatColor.BLUE + "http://xam4lor.890m.com");
		
		new SetParameters(ev.getPlayer());
		new GiveCustomInventory(ev.getPlayer());
		
		ev.getPlayer().getWorld().getBlockAt(-866, 5, -261).setType(Material.AIR);
		ev.getPlayer().getWorld().getBlockAt(-866, 5, -261).setType(Material.REDSTONE_BLOCK);
		
		ev.getPlayer().setDisplayName(GradeSystem.getGradeColor(GradeSystem.getGrade(ev.getPlayer())) + "[" + GradeSystem.getGrade(ev.getPlayer()) + "] " + ev.getPlayer().getDisplayName() + ChatColor.RESET);
		
		try {
			new DisplayScoreboard(ev.getPlayer());
		}
		catch(Exception e) {
			e.printStackTrace();
			new OnErrorSystem(e.toString());
		}
		
		MainClass.updateScoreboardTick(ev.getPlayer());
		if(MailSystem.getMail(ev.getPlayer().getName()) != null) {
			ev.getPlayer().sendMessage(ChatColor.RED + MainClass.getServerName() + ChatColor.GREEN + "Vous avez un/des nouveau(x) mail(s) : ");
			ev.getPlayer().sendMessage(ChatColor.WHITE + MailSystem.getMail(ev.getPlayer().getName()));
		}
		
		if(!MainClass.nbPlConnexion.contains(ev.getPlayer().getUniqueId().toString())) {
			MainClass.nbPlConnexion.add(ev.getPlayer().getUniqueId().toString());
		}
	}
	
    @EventHandler(priority = EventPriority.LOWEST)
	public void onPlayerRespawn(PlayerRespawnEvent ev) {
		new SetParameters(ev.getPlayer());
		new GiveCustomInventory(ev.getPlayer());
	}
	
    @EventHandler(priority = EventPriority.LOWEST)
	public void onBlockBreakEvent(final BlockBreakEvent ev) {
		if(!ev.getPlayer().isOp() && !MainClass.isEnterCoords(ev.getPlayer(), -450, -3000, -335, -368, 400, -252)) {
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
			e.printStackTrace();
			new OnErrorSystem(e.toString());
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
    
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerQuitEvent(PlayerQuitEvent ev) {
    	if(MainClass.plDeathsInSkywars.contains(ev.getPlayer().getName())) {
    		MainClass.plDeathsInSkywars.remove(ev.getPlayer().getName());
    	}
    }
        
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityDamage(EntityDamageEvent ev) {
    	try {
    		Entity e = ev.getEntity();
    		
        	if(e instanceof Player) {
        		Player pl = ((Player) e).getPlayer();
        		
        		if(MainClass.isEnterCoords(pl, -450, -3000, -335, -368, 400, -252) && (pl.getHealth() - ev.getDamage()) <= 0) {
        			ev.setCancelled(true);
        			pl.setGameMode(GameMode.SPECTATOR);
        			
        			for (ItemStack is : pl.getInventory()) {
        				if (is != null) {
        					Bukkit.getServer().getWorlds().get(0).dropItem(pl.getLocation(), is);
        				}
        			}
        			
        			pl.sendMessage(ChatColor.RED + MainClass.getServerName() + ChatColor.GREEN + "Vous êtes mort et avez été mis en GameMode 3. Pour quitter la partie, faites '/return'.");
        			pl.getPlayer().teleport(new Location(Bukkit.getServer().getWorlds().get(0), -408, 60, -285));
        			pl.getInventory().clear();
        			pl.getInventory().setArmorContents(new ItemStack[] {new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.AIR)});
        			MainClass.plDeathsInSkywars.add(pl.getName());
        			
        			for(Player player : Bukkit.getOnlinePlayers()) {
        				if(MainClass.isEnterCoords(player, -450, -3000, -335, -368, 400, -252)) {
        					player.sendMessage(ChatColor.RED + MainClass.getServerName() + pl.getName()  + " est mort.");
        				}
        			}
        			
        			new EndSkyWars();
        		}
        	}
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		new OnErrorSystem(e.toString());
    	}
    	catch(NoSuchMethodError e) {
    		e.printStackTrace();
    		new OnErrorSystem(e.toString());
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
				else if(ev.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.BLUE + "- GADGETS -")) {
					ev.getWhoClicked().closeInventory();
					new GadgetsGui((Player) ev.getWhoClicked());
				}
			}
			
			else if (ev.getInventory().getName().equals("- Op Tools -")) {
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
			
			else if (ev.getInventory().getName().equals("- Gadgets -")) {
				ev.setCancelled(true);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			new OnErrorSystem(e.toString());
		}
	}
	
    @EventHandler(priority = EventPriority.LOWEST)
	public void onWeatherChange(WeatherChangeEvent ev) {
		ev.setCancelled(true);
	}
}
