package fr.hartania.xam4lor.grades;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.bukkit.Bukkit;

import fr.hartania.xam4lor.errorSystem.OnErrorSystem;
import fr.hartania.xam4lor.main.MainClass;

public class GetModerateursPlayersByFile {
	public GetModerateursPlayersByFile() {
		File mutePlayersFile = new File("plugins/config/moderateurs.txt");
		
		try {
			if (mutePlayersFile.exists()) {
				BufferedReader br = null;
				
				try {
					br = new BufferedReader(new FileReader(mutePlayersFile));
					String line;
					
					while ((line = br.readLine()) != null) {
						if(line != "") {
							MainClass.setModerateurPlayer(line);
						}
					}
				}
				
				catch (Exception e) {
					e.printStackTrace();
					new OnErrorSystem(e.toString());
				} 
				
				finally {
					try { 
						if (br != null) br.close(); 
					}
					
					catch (Exception e) { 
						e.printStackTrace();
						new OnErrorSystem(e.toString());
					}
				}
			}
			else {
				Bukkit.getLogger().warning(MainClass.getServerName() + "Impossible de trouver le fichier 'moderateurs.txt'");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			new OnErrorSystem(e.toString());
		}
	}
}
