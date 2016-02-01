package fr.hartania.xam4lor.scoreboards;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import fr.hartania.xam4lor.main.MainClass;

public class DisplayScoreboard {
	private static Map<UUID, Scoreboard> scoreboardMap = new HashMap<UUID, Scoreboard>();
	
	public DisplayScoreboard(Player pl) {
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		Scoreboard scoreboardCustom = manager.getNewScoreboard();
		scoreboardCustom.registerNewObjective("MainScoreboard", "dummy");
		
		scoreboardMap.put(pl.getUniqueId(), scoreboardCustom);
		pl.setScoreboard(scoreboardMap.get(pl.getUniqueId()));
	}

	public static void updateScoreboard(Player pl) {
		Scoreboard scoreboardCustom = scoreboardMap.get(pl.getUniqueId());
		scoreboardCustom.getObjective("MainScoreboard").unregister();
		
		Objective mainObjective = scoreboardCustom.registerNewObjective("MainScoreboard", "dummy");
		int money = 0;
		int kills = 0;
		String grade = "joueur";
		int joueurs = Bukkit.getOnlinePlayers().size();
		
		try {
			money = MainClass.getPlayerMoney(pl);
		}
		catch(Exception e) {}
		
		try {
			grade = MainClass.getPlayerGrade(pl);
		}
		catch(Exception e) {}
		
		try {
			kills = MainClass.getPlayerKills(pl);
		}
		catch(Exception e) {}
		
		mainObjective.setDisplaySlot(DisplaySlot.SIDEBAR);
		mainObjective.setDisplayName(ChatColor.RED + "------- " + ChatColor.GREEN + "HARTANIA" + ChatColor.RED + " -------");
		mainObjective.getScore(ChatColor.GREEN + "Bienvenu(e) sur Hartania !      ").setScore(9);
		mainObjective.getScore(" ").setScore(8);
		mainObjective.getScore(ChatColor.BLUE + "Joueur : " + ChatColor.BOLD + ChatColor.RED + pl.getName()).setScore(7);
		mainObjective.getScore(ChatColor.BLUE + "Monnaie : " + ChatColor.BOLD + ChatColor.RED + money).setScore(6);
		mainObjective.getScore(ChatColor.BLUE + "Kills : " + ChatColor.BOLD + ChatColor.RED + kills).setScore(5);
		mainObjective.getScore(ChatColor.BLUE + "Grade : " + ChatColor.BOLD + ChatColor.RED + grade).setScore(4);
		mainObjective.getScore(ChatColor.BLUE + "Joueurs connectés : " + ChatColor.BOLD + ChatColor.RED + joueurs).setScore(3);
		mainObjective.getScore(" ").setScore(2);
		mainObjective.getScore(ChatColor.YELLOW + "www.xam4lor.890m.com").setScore(1);
		mainObjective.getScore(ChatColor.RED + "-----------------------").setScore(0);
		
		scoreboardMap.put(pl.getUniqueId(), scoreboardCustom);
		
		pl.setScoreboard(scoreboardMap.get(pl.getUniqueId()));
	}
}
