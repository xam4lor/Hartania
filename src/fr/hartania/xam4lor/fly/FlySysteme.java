package fr.hartania.xam4lor.fly;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import fr.hartania.xam4lor.main.MainClass;

public class FlySysteme {
	public FlySysteme(Integer arg, Player pl) {
		if(arg == 0) {
			pl.setAllowFlight(false);
			pl.sendMessage(ChatColor.RED + MainClass.getServerName() + ChatColor.GREEN + "Fly " + ChatColor.BOLD + "d�sactiv�" + ChatColor.RESET +  ChatColor.GREEN + ".");
		}
		else if(arg == 1) {
			pl.setAllowFlight(true);
			pl.sendMessage(ChatColor.RED + MainClass.getServerName() + ChatColor.GREEN + "Fly " + ChatColor.BOLD + "activ�" + ChatColor.RESET +  ChatColor.GREEN + ".");
		}
	}
}
