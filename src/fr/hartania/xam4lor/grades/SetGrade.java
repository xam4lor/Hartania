package fr.hartania.xam4lor.grades;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import fr.hartania.xam4lor.errorSystem.OnErrorSystem;
import fr.hartania.xam4lor.fileSystem.FileSystems;
import fr.hartania.xam4lor.main.MainClass;

public class SetGrade {

	public SetGrade(String playerName, String permission, Player commandExecutor) {
		this.removeModoIfModo(playerName);
		this.removeAdminIfAdmin(playerName);
		MainClass.unModerateurPlayer(playerName);
		MainClass.unAdminPlayer(playerName);
		
		if(permission.equals("modo")) {
			MainClass.setModerateurPlayer(playerName);
			this.setModo(playerName);
			commandExecutor.sendMessage(ChatColor.RED + MainClass.getServerName() + ChatColor.GREEN + "Le joueur " + playerName + " a bien été mis " + permission + " (veuillez dire au joueur de se deco/reco).");
		}
		else if(permission.equals("admin")) {
			MainClass.setAdminPlayer(playerName);
			this.setAdmin(playerName);
			commandExecutor.sendMessage(ChatColor.RED + MainClass.getServerName() + ChatColor.GREEN + "Le joueur " + playerName + " a bien été mis " + permission + " (veuillez dire au joueur de se deco/reco).");
		}
		else if(permission.equals("joueur")) {
			commandExecutor.sendMessage(ChatColor.RED + MainClass.getServerName() + ChatColor.GREEN + "Le joueur " + playerName + " a bien été mis " + permission + " (veuillez dire au joueur de se deco/reco).");
		}
		else {
			commandExecutor.sendMessage(ChatColor.RED + MainClass.getServerName() + ChatColor.GREEN + "Le grade " + permission + " n'existe pas (ceci est une erreur).");
		}
	}
	
	private void setModo(String playerName) {
		FileSystems.ajouterLigne("plugins/config/moderateurs.txt", playerName);
	}

	private void setAdmin(String playerName) {
		FileSystems.ajouterLigne("plugins/config/administrateurs.txt", playerName);
	}
	
	private void removeModoIfModo(String playerName) {
		File modosFile = new File("plugins/config/moderateurs.txt");
		
		try {
			if (modosFile.exists()) {
				BufferedReader br = null;
				
				try {
					br = new BufferedReader(new FileReader(modosFile));
					String line;
					int lineNumber = 0;
					
					while ((line = br.readLine()) != null) {
						if(line.equals(playerName)) {
							FileSystems.deleteLine("plugins/config/moderateurs.txt", lineNumber);
						}
						
						lineNumber++;
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
	
	private void removeAdminIfAdmin(String playerName) {
		File adminsFile = new File("plugins/config/administrateurs.txt");
		
		try {
			if (adminsFile.exists()) {
				BufferedReader br = null;
				
				try {
					br = new BufferedReader(new FileReader(adminsFile));
					String line;
					int lineNumber = 0;
					
					while ((line = br.readLine()) != null) {
						if(line.equals(playerName)) {
							FileSystems.deleteLine("plugins/config/administrateurs.txt", lineNumber);
						}
						
						lineNumber++;
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
				Bukkit.getLogger().warning(MainClass.getServerName() + "Impossible de trouver le fichier 'administrateurs.txt'");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			new OnErrorSystem(e.toString());
		}
	}
}
