package fr.hartania.xam4lor.main;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import fr.hartania.xam4lor.connection.GiveCustomInventory;
import fr.hartania.xam4lor.events.Events;
import fr.hartania.xam4lor.fly.FlySysteme;

public class MainClass extends JavaPlugin {
	
	public Logger log = Logger.getLogger("Minecraft");
	public static World main_world = Bukkit.getServer().getWorld("world");
	
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new Events(this), this);
		this.log.info(MainClass.getServerName() + "If this is not the correct default world, please contact the developper. World: " + Bukkit.getServer().getWorlds().get(0));
		
		this.log.info(MainClass.getServerName() + "Plugin launched");
	}

	@Override
	public void onDisable() {
		this.log.info(MainClass.getServerName() + "Plugin stopped");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) { //on teste les commandes
		//commande ht
		if(cmd.getName().equalsIgnoreCase("ht")) {
			if(sender instanceof Player) {
				Player pl = ((Player) sender).getPlayer();
				
				if(args.length == 0) {
					pl.sendMessage(ChatColor.RED + getCommandHtSyntaxe());
				}
				else if(args[0].equalsIgnoreCase("giveItems")) {
					new GiveCustomInventory(pl);
    			}
    			else if (!pl.isOp()) {
    				pl.sendMessage(ChatColor.RED + getServerName() + "Vous n'�tes pas un op�rateur !");
    			}
    			else {
    				pl.sendMessage(ChatColor.RED + getCommandHtSyntaxe());
    			}
            }
	        else {
				if(args.length == 0) {
					sender.sendMessage(getCommandHtSyntaxe());
				}
				else if(args[0].equalsIgnoreCase("giveItems")) {
					try {
						if(args[1] != null) {
							new GiveCustomInventory(Bukkit.getPlayer(args[1]));
							sender.sendMessage("Les items ont bien �t� giv�s � " + Bukkit.getPlayer(args[1]).getCustomName() + ".");
						}
						else {
							sender.sendMessage(getCommandHtGiveItemsSyntaxe());
						}
					}
					catch(Exception e) {
						sender.sendMessage(getCommandHtGiveItemsSyntaxe());
					}

				}
				else {
					sender.sendMessage(getCommandHtSyntaxe());
				}
	        }
        }
		
		//commande fly
		else if(cmd.getName().equalsIgnoreCase("fly")) {
			if(sender instanceof Player) {
				Player pl = ((Player) sender).getPlayer();
				
				if(args.length == 0) {
					pl.sendMessage(ChatColor.RED + getCommandFlySyntaxe());
				}
    			else if (!pl.isOp()) {
    				pl.sendMessage(ChatColor.RED + getServerName() + "Vous n'�tes pas un op�rateur !");
    			}
				else if(args[0].equalsIgnoreCase("1")) {
					new FlySysteme(1, pl);
    			}
				else if(args[0].equalsIgnoreCase("0")) {
					new FlySysteme(0, pl);
    			}
				else if(args[0].equalsIgnoreCase("on")) {
					new FlySysteme(1, pl);
    			}
				else if(args[0].equalsIgnoreCase("off")) {
					new FlySysteme(0, pl);
    			}
    			else {
    				pl.sendMessage(ChatColor.RED + getCommandFlySyntaxe());
    			}
			}
			else {
				sender.sendMessage("Il faut �tre un joueur.");
			}
		}
		
		//messages priv�s
		else if(cmd.getName().equalsIgnoreCase("msg")) {
			if(sender instanceof Player) {
				Player pl = ((Player) sender).getPlayer();
				Player player_choose;
				String message = "";
				boolean boucle_value = true;
				int i = 2;
				
				try {
					if(args[0] != null && args.length != 0) {
						message = message + args[1];
						
						while(boucle_value) {
							try {
								message = message + " " + args[i];
							}
							catch(Exception e) {
								boucle_value = false;
							}
							
							i++;
						}
						
						player_choose = Bukkit.getPlayer(args[0]);
						player_choose.sendMessage(ChatColor.RED + MainClass.getServerName() + ChatColor.GREEN + ChatColor.ITALIC + "Message de " + pl.getDisplayName() + " ---> " + ChatColor.RESET + message);
						pl.sendMessage(ChatColor.RED + MainClass.getServerName() + ChatColor.GREEN + ChatColor.ITALIC + "Message bien envoy� � " + player_choose.getDisplayName() + ": " + ChatColor.RESET + message);
					}
					else {
						pl.sendMessage(ChatColor.RED + getCommandMsgSyntaxe());
					}
				}
				catch(Exception e) {
					pl.sendMessage(ChatColor.RED + getCommandMsgSyntaxe());
				}
			}
			else {
				sender.sendMessage("Il faut �tre un joueur.");
			}
		}
		
		else if(cmd.getName().equalsIgnoreCase("msg-begin")) {
			/*
			if(sender instanceof Player) {
				Player pl = ((Player) sender).getPlayer();
				
				if(args.length == 0) {
					pl.sendMessage(ChatColor.RED + getCommandMsgBeginSyntaxe());
				}
    			else {
    				ConversationSystem.convBegin(pl, Bukkit.getPlayer(args[0]));
    			}
			}*/
		}
		
		return true;
	}
	
	@SuppressWarnings("unused")
	private String getCommandMsgBeginSyntaxe() {
		return "Syntaxe : /msg-begin <pseudo>";
	}

	private String getCommandFlySyntaxe() {
		return "Syntaxe : /fly <1/0/on/off>";
	}
	
	private String getCommandMsgSyntaxe() {
		return "Syntaxe : /msg <pseudo> <message>";
	}

	private String getCommandHtGiveItemsSyntaxe() {
		return "Syntaxe : /ht giveItems <@p/@r/@a/pseudo>";
	}

	private String getCommandHtSyntaxe() {
		return "Syntaxe : /ht <giveItems>";
	}

	public static String getServerName() {
		return "[Hartania] ";
	}
}