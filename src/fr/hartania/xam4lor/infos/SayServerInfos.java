package fr.hartania.xam4lor.infos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import fr.hartania.xam4lor.main.MainClass;

public class SayServerInfos {
	public SayServerInfos(Player pl) {
		String infos = this.getInfos();
		
		pl.sendMessage(ChatColor.RED + MainClass.getServerName() + ChatColor.GREEN + infos);
	}
	
	private String getInfos() {
		File infosFile = new File("plugins/config/infos.txt");
		String infos = "";
		
		try {
			if (infosFile.exists()) {
				BufferedReader br = null;
				
				try {
					br = new BufferedReader(new FileReader(infosFile));
					String line;
					
					while ((line = br.readLine()) != null) {
						infos += line;
						infos += System.getProperty("line.separator");
						infos += System.getProperty("line.separator");
					}
				}
				
				catch (Exception e) {
					e.printStackTrace();
				} 
				
				finally {
					try { 
						if (br != null) br.close(); 
					}
					
					catch (Exception e) { 
						e.printStackTrace(); 
					}
				}
			}
			else {
				Bukkit.getLogger().warning(MainClass.getServerName() + "Impossible de trouver le fichier 'infos.txt'");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return infos;
	}
}
