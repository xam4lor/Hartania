package fr.hartania.xam4lor.pubMessage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import fr.hartania.xam4lor.errorSystem.OnErrorSystem;
import fr.hartania.xam4lor.main.MainClass;

public class RandomPubMessage {
	public RandomPubMessage() {
		String[] pubMessages = this.getPubMessages();
		int randomMessageNumber = 1;
		
		try {
			if(pubMessages != null) {
				Random r = new Random();
				randomMessageNumber = 1 + r.nextInt(Integer.parseInt(pubMessages[0]) - 1);
				
				Bukkit.getServer().broadcastMessage(ChatColor.RED + MainClass.getServerName() + ChatColor.GREEN + pubMessages[randomMessageNumber]);
			}
			else {
				Bukkit.getLogger().warning(MainClass.getServerName() + "Le tableau à la ligne 14 de la class RandomPubMessage ne peut pas être vide.");
			}
		}
		catch(ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			new OnErrorSystem(e.toString());
		}
	}
	
	private String[] getPubMessages() {
		File pubMessageFile = new File("plugins/config/pubMessage.txt");
		String[] pubMessages = {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};
		
		try {
			if (pubMessageFile.exists()) {
				BufferedReader br = null;
				
				try {
					br = new BufferedReader(new FileReader(pubMessageFile));
					String line;
					int i = 1;
					
					while ((line = br.readLine()) != null) {
						pubMessages[i] = line.toString();
						i++;
					}
					
					pubMessages[0] = i + "";
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
				Bukkit.getLogger().warning(MainClass.getServerName() + "Impossible de trouver le fichier 'pubMessage.txt'");
			}
			return pubMessages;
		}
		catch(ArrayIndexOutOfBoundsException e) {
			Bukkit.getLogger().warning(MainClass.getServerName() + "Le nombre de messages de pub est trop important.");
			return null;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
