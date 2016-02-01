package fr.hartania.xam4lor.lookup;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import fr.hartania.xam4lor.main.MainClass;

public class LookupPlayer {
	public Logger log = Bukkit.getServer().getLogger();
	
	public LookupPlayer(Player sender, Player pl) {
		int money = 0;
		int kills = 0;
		
		try {
			money = MainClass.getPlayerMoney(pl);
		} catch(Exception e) {}
		
		try {
			kills = MainClass.getPlayerKills(pl);
		} catch(Exception e) {}
		
		pl.sendMessage(
			ChatColor.RED + " ------ INFORMATIONS SUR " + pl.getName().toUpperCase() + " ------ "
		);
		pl.sendMessage(
			ChatColor.BLUE + "Pseudo : " + ChatColor.RED + pl.getName()
		);
		pl.sendMessage(
			ChatColor.BLUE + "UUID : " + ChatColor.RED + pl.getUniqueId()
		);
		pl.sendMessage(
			ChatColor.BLUE + "ID : " + ChatColor.RED + pl.getEntityId()
		);
		pl.sendMessage(
			ChatColor.BLUE + "Monnaie : " + ChatColor.RED + money
		);
		pl.sendMessage(
			ChatColor.BLUE + "Kills : " + ChatColor.RED + kills
		);
		pl.sendMessage(
			ChatColor.BLUE + "Grade : " + ChatColor.RED + MainClass.getPlayerGrade(pl)
		);
		pl.sendMessage(
			ChatColor.BLUE + "Operateur : " + ChatColor.RED + pl.isOp()
		);
		pl.sendMessage(
			ChatColor.RED + " ------ INFORMATIONS SUR " + pl.getName().toUpperCase() + " ------ "
		);
	}
}
