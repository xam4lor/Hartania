package fr.hartania.xam4lor.grades;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import fr.hartania.xam4lor.main.MainClass;

public class SetGrade {

	public SetGrade(String playerName, String permission, Player commandExecutor) {
		this.removeModoIfModo(playerName);
		this.removeAdminIfAdmin(playerName);
		
		if(permission.equals("modo")) {
			this.setModo(playerName);
			commandExecutor.sendMessage(ChatColor.RED + MainClass.getServerName() + ChatColor.GREEN + "Le joueur " + playerName + " a bien été mis " + permission + " (veuillez relancer le serveur).");
		}
		else if(permission.equals("admin")) {
			this.setAdmin(playerName);
			commandExecutor.sendMessage(ChatColor.RED + MainClass.getServerName() + ChatColor.GREEN + "Le joueur " + playerName + " a bien été mis " + permission + " (veuillez relancer le serveur).");
		}
		else if(permission.equals("joueur")) {
			commandExecutor.sendMessage(ChatColor.RED + MainClass.getServerName() + ChatColor.GREEN + "Le joueur " + playerName + " a bien été mis " + permission + " (veuillez relancer le serveur).");
		}
		else {
			commandExecutor.sendMessage(ChatColor.RED + MainClass.getServerName() + ChatColor.GREEN + "Le grade " + permission + " n'existe pas (ceci est une erreur).");
		}
	}
	
	private void setModo(String playerName) {
		this.ajouterLigne("plugins/config/moderateurs.txt", playerName);
	}

	private void setAdmin(String playerName) {
		this.ajouterLigne("plugins/config/moderateurs.txt", playerName);
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
							deleteLine("plugins/config/moderateurs.txt", lineNumber);
						}
						
						lineNumber++;
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
				Bukkit.getLogger().warning(MainClass.getServerName() + "Impossible de trouver le fichier 'moderateurs.txt'");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
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
							deleteLine("plugins/config/administrateurs.txt", lineNumber);
						}
						
						lineNumber++;
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
				Bukkit.getLogger().warning(MainClass.getServerName() + "Impossible de trouver le fichier 'administrateurs.txt'");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void deleteLine(String fileName, int lineNumber) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
			StringBuffer sb = new StringBuffer();
			String line;
			int nbLinesRead = 0;
			
			while ((line = reader.readLine()) != null) {
				if (nbLinesRead != lineNumber) {
					sb.append(line + "\n");
				}
				nbLinesRead++;
			}
			reader.close();
			
			BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
			out.write(sb.toString());
			out.close();
		} catch (Exception e) {}
    }
	
	public void ajouterLigne(String filename, String text) {
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
