package fr.hartania.xam4lor.grades;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import fr.hartania.xam4lor.main.MainClass;

public class GradeSystem {
	public static String getGrade(Player pl) {
		if(MainClass.isPlayerModerateur(pl)) {
			return "Modérateur";
		}
		else if(MainClass.isPlayerAdmin(pl)) {
			return "Administrateur";
		}
		else {
			return "Joueur";
		}
	}

	public static ChatColor getGradeColor(String grade) {
		if(grade == "Joueur") {
			return ChatColor.WHITE;
		}
		else if(grade == "Modérateur") {
			return ChatColor.GREEN;
		}
		else if(grade == "Administrateur") {
			return ChatColor.RED;
		}
		else {
			Bukkit.getLogger().warning("Le grade " + grade + " est irreconaissable (cf la classe GradeSystem, fonction getGradeColor())");
			return ChatColor.WHITE;
		}
	}
}
