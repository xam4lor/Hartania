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

public class MainClass extends JavaPlugin {
	
	public Logger log = Logger.getLogger("Minecraft");
	public static World main_world = Bukkit.getServer().getWorld("world");
	
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new Events(this), this);
		this.log.info(this.getServerName() + "Plugin launched");
		this.log.info(this.getServerName() + "If this is not the correct default world, please contact the developper. World: " + Bukkit.getServer().getWorlds().get(0));
	}

	@Override
	public void onDisable() {
		this.log.info(this.getServerName() + "Plugin stopped");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) { //on teste les commandes
        if(sender instanceof Player){
        	Player pl = ((Player) sender).getPlayer();
        	
            if(cmd.getName().equalsIgnoreCase("ht")) {
    			if (!pl.isOp()) {
    				pl.sendMessage(ChatColor.RED + getServerName() + "Vous n'êtes pas un opérateur !");
    			}
    			else if (args.length == 0) {
    				pl.sendMessage(ChatColor.RED + getCommandHtSyntaxe());
    			}
    			
    			else if(args[0].equalsIgnoreCase("giveItems")) {
    				new GiveCustomInventory(pl);
    			}
            }
        }
		return true;
	}
	
	private String getCommandHtSyntaxe() {
		return "Syntaxe : /ht <giveItems>";
	}

	public String getServerName() {
		return "[Hartania] ";
	}
}