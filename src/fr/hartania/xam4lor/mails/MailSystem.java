package fr.hartania.xam4lor.mails;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.bukkit.Bukkit;

import fr.hartania.xam4lor.errorSystem.OnErrorSystem;
import fr.hartania.xam4lor.fileSystem.FileSystems;
import fr.hartania.xam4lor.main.MainClass;

public class MailSystem {
	public static void sendMail(String destName, String message, String sender) {
		FileSystems.ajouterLigne("plugins/config/mails.txt", (destName + "§" + sender + "§" + message));
	}
	
	public static String getMail(String destName) {
		File mailFile = new File("plugins/config/mails.txt");
		String mail = "";
		String[] lineSearchExact = {"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};
		int i = 0;
		
		try {
			if (mailFile.exists()) {
				BufferedReader br = null;
				
				try {
					br = new BufferedReader(new FileReader(mailFile));
					String line;
					
					while ((line = br.readLine()) != null) {
						try {
							String[] lineElement = line.split("§");
							String playerName = lineElement[0];
							String playerSender = lineElement[1];
							
							if(playerName.equals(destName)) {
								mail += " - de ";
								mail += playerSender;
								mail += " : ";
								mail += lineElement[2];
								mail += System.lineSeparator();
								
								lineSearchExact[i] = lineElement[0] + "§" + lineElement[1] + "§" + lineElement[2];
								i++;
							}
						}
						catch(Exception e) {}
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
				Bukkit.getLogger().warning(MainClass.getServerName() + "Impossible de trouver le fichier 'mails.txt'");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			new OnErrorSystem(e.toString());
		}
		
		
		for(int ii = 0; ii != lineSearchExact.length; ii++) {
			if(lineSearchExact[ii] != "") {
				//deleteLine("plugins/config/mails.txt", getLineOf("plugins/config/mails.txt", lineSearchExact[ii]));
			}
		}
		
		if(mail == "") {
			return null;
		}
		else {
			return mail;
		}
	}
}
