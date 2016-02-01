package fr.hartania.xam4lor.infos;

import java.util.logging.Logger;

import org.bukkit.Bukkit;

import fr.hartania.xam4lor.fileSystem.FileSystems;
import fr.hartania.xam4lor.main.MainClass;

public class AddServerInfo {
	public Logger log = Bukkit.getServer().getLogger();
	
	public AddServerInfo(String message) {
		FileSystems.ajouterLigne("plugins/config/infos.txt", "");
		FileSystems.ajouterLigne("plugins/config/infos.txt", message);
		this.log.info(MainClass.getServerName() + "Cette actualité a été rajoutée : " + message);
	}
}
