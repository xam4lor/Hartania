package fr.hartania.xam4lor.games.skywars;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.hartania.xam4lor.connection.GiveCustomInventory;
import fr.hartania.xam4lor.connection.SetParameters;
import fr.hartania.xam4lor.main.MainClass;

public class EndSkyWars {
	public EndSkyWars() {
		int plSkyWarsNb = 0;
		String plWinnerName = "";
		
		for(Player pl : Bukkit.getOnlinePlayers()) {
			if(MainClass.isEnterCoords(pl, -450, -3000, -335, -368, 400, -252) && pl.getGameMode() == GameMode.SURVIVAL) {
				plSkyWarsNb++;
				plWinnerName = pl.getName();
			}
		}
		
		if(plSkyWarsNb == 1) {
			for(Player pl : Bukkit.getOnlinePlayers()) {
				if(MainClass.isEnterCoords(pl, -450, -3000, -335, -368, 400, -252)) {
					pl.sendMessage(ChatColor.RED + MainClass.getServerName() + ChatColor.GREEN + ChatColor.BOLD + plWinnerName + ChatColor.RESET + ChatColor.GREEN + " gagne la partie !");
					
					MainClass.addPlayerMoney(pl, 20);
				}
			}
			
			finPartie();
		}
		else if(plSkyWarsNb == 0) {
			for(Player pl : Bukkit.getOnlinePlayers()) {
				if(MainClass.isEnterCoords(pl, -450, -3000, -335, -368, 400, -252)) {
					pl.sendMessage(ChatColor.RED + MainClass.getServerName() + ChatColor.GREEN + "CrossKill. Fin de la partie !");
				}
			}
			
			finPartie();
		}
	}
	
	private void finPartie() {
		MainClass.plDeathsInSkywars.clear();
		
		for(Player pl : Bukkit.getOnlinePlayers()) {
			if(MainClass.isEnterCoords(pl, -450, -3000, -335, -368, 400, -252)) {
    			pl.getInventory().clear();
    			pl.getInventory().setArmorContents(new ItemStack[] {new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.AIR)});
    			
				new SetParameters(pl);
				new GiveCustomInventory(pl);
				
				if(pl.isOp()) {
					pl.setGameMode(GameMode.CREATIVE);
				}
				else {
					pl.setGameMode(GameMode.SURVIVAL);
				}
				
				MainClass.plDeathsInSkywars.remove(pl.getName());
			}
		}
	}
}
