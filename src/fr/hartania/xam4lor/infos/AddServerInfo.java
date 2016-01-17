package fr.hartania.xam4lor.infos;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.Bukkit;

import fr.hartania.xam4lor.main.MainClass;

public class AddServerInfo {
	public Logger log = Bukkit.getServer().getLogger();
	
	public AddServerInfo(String message) {
		this.ajouterLigne("plugins/config/infos.txt", "");
		this.ajouterLigne("plugins/config/infos.txt", message);
		this.log.info(MainClass.getServerName() + "Cette actualité a été rajoutée : " + message);
	}
	
	private void ajouterLigne(String filename, String text) {
		BufferedWriter bufWriter = null;
		FileWriter fileWriter = null;
		
		try {
			fileWriter = new FileWriter(filename, true);
			bufWriter = new BufferedWriter(fileWriter);
			bufWriter.newLine();
			bufWriter.write(text);
			bufWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bufWriter.close();
				fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    }

}
