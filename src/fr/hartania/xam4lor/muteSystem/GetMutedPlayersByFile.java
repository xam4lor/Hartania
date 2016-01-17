package fr.hartania.xam4lor.muteSystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.bukkit.Bukkit;

import fr.hartania.xam4lor.main.MainClass;

public class GetMutedPlayersByFile {
	public GetMutedPlayersByFile() {
		File mutePlayersFile = new File("plugins/config/mutePlayers.txt");
		
		try {
			if (mutePlayersFile.exists()) {
				BufferedReader br = null;
				
				try {
					br = new BufferedReader(new FileReader(mutePlayersFile));
					String line;
					
					while ((line = br.readLine()) != null) {
						if(line != "") {
							MainClass.setMutePlayer(line);
						}
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
				Bukkit.getLogger().warning(MainClass.getServerName() + "Impossible de trouver le fichier 'mutePlayers.txt'");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
