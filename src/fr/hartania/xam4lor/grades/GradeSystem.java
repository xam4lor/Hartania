package fr.hartania.xam4lor.grades;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import fr.hartania.xam4lor.main.MainClass;

public class GradeSystem {
	public static String getGrade(Player pl) {
		String pseudo = pl.getName();
		
		if(GradeSystem.isModo(pseudo)) {
			return "Modérateur";
		}
		else if(GradeSystem.isAdmin(pseudo)) {
			return "Administrateur";
		}
		else {
			return "Joueur";
		}
	}
	
	private static boolean isModo(String pseudo) {
		String[] modos = GradeSystem.getModos();
		boolean isModo = false;
		
		for(int i = 0; i != modos.length; i++) {
			if(pseudo.equals(modos[i])) {
				isModo = true;
			}
		}
		
		return isModo;
	}

	private static boolean isAdmin(String pseudo) {
		String[] admins = GradeSystem.getAdmins();
		boolean isAdmin = false;
		
		for(int i = 0; i != admins.length; i++) {
			if(pseudo.equals(admins[i])) {
				isAdmin = true;
			}
		}
		
		return isAdmin;
	}

	public static ChatColor getGradeColor(String grade) {
		if(grade == "Joueur") {
			return ChatColor.WHITE;
		}
		else if(grade == "Modérateur") {
			return ChatColor.GREEN;
		}
		else if(grade == "Administrateur") {
			return ChatColor.RED;
		}
		else {
			Bukkit.getLogger().warning("Le grade " + grade + " est irreconaissable (cf la classe GradeSystem, fonction getGradeColor())");
			return ChatColor.WHITE;
		}
	}
	
	private static String[] getModos() {
		File modosFile = new File("plugins/config/moderateurs.txt");
		String[] modos = {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};
		
		try {
			if (modosFile.exists()) {
				BufferedReader br = null;
				
				try {
					br = new BufferedReader(new FileReader(modosFile));
					String line;
					int i = 0;
					
					while ((line = br.readLine()) != null) {
						if(line != "") {
							modos[i] = line.toString();
							i++;
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
				Bukkit.getLogger().warning(MainClass.getServerName() + "Impossible de trouver le fichier 'moderateurs.txt'");
			}
			return modos;
		}
		catch(ArrayIndexOutOfBoundsException e) {
			Bukkit.getLogger().warning(MainClass.getServerName() + "Le nombre de messages de moderateurs est trop important.");
			return null;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private static String[] getAdmins() {
		File adminsFile = new File("plugins/config/administrateurs.txt");
		String[] admins = {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};
		
		try {
			if (adminsFile.exists()) {
				BufferedReader br = null;
				
				try {
					br = new BufferedReader(new FileReader(adminsFile));
					String line;
					int i = 0;
					
					while ((line = br.readLine()) != null) {
						if(line != "") {
							admins[i] = line.toString();
							i++;
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
				Bukkit.getLogger().warning(MainClass.getServerName() + "Impossible de trouver le fichier 'administrateurs.txt'");
			}
			return admins;
		}
		catch(ArrayIndexOutOfBoundsException e) {
			Bukkit.getLogger().warning(MainClass.getServerName() + "Le nombre de messages d'admins est trop important.");
			return null;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
