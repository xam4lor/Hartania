package fr.hartania.xam4lor.errorSystem;

import java.text.DateFormat;
import java.util.Date;

import org.bukkit.Bukkit;

import fr.hartania.xam4lor.fileSystem.FileSystems;

public class OnErrorSystem {
	public OnErrorSystem(String error) {
		String dateString = "";
		Date aujourdhui = new Date();
		
		DateFormat fullDateFormat = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL);
		dateString = fullDateFormat.format(aujourdhui);
		
		FileSystems.ajouterLigne("plugins/config/error_log/errors.txt", ("[" + dateString + "] : " + error));
		Bukkit.getServer().getLogger().warning(error);
		Bukkit.getServer().getLogger().warning("Rapport d'erreur précédent ajouté dans le dossier errors.txt.");
	}
}
