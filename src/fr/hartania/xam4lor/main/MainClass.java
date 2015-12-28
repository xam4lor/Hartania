package fr.hartania.xam4lor.main;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import fr.hartania.xam4lor.events.Events;

public class MainClass extends JavaPlugin {
	
	public Logger log = Logger.getLogger("Minecraft");
	public static World main_world = Bukkit.getServer().getWorld("world");
	
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new Events(this), this);
		this.log.info(this.getServerName() + "Plugin launched");
	}

	@Override
	public void onDisable() {
		this.log.info(this.getServerName() + "Plugin stopped");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) { //on teste les commandes
        if(sender instanceof Player){
            if(cmd.getName().equalsIgnoreCase("ht")) {
            }
        }
		return true;
	}
	
	public String getServerName() {
		return "[Hartania] ";
	}
}