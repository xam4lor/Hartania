package fr.hartania.xam4lor.muteSystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;

import org.bukkit.Bukkit;

import fr.hartania.xam4lor.main.MainClass;

public class SetUnMutedPlayerInFile {
	public SetUnMutedPlayerInFile(String pl_name) {
		int line = this.getLineOf("plugins/config/mutePlayers.txt", pl_name);
		
		if(line != 0) {
			this.deleteLine("plugins/config/mutePlayers.txt", line);
		}
		else {
			Bukkit.getLogger().info("Le joueur " + pl_name + " n'est pas mute (ceci n'affectera pas le programme).");
		}
	}
	
	private int getLineOf(String fileName, String lineContenu) {
		File file = new File(fileName);
		int line_number = 0;
		
		try {
			if (file.exists()) {
				BufferedReader br = null;
				
				try {
					br = new BufferedReader(new FileReader(file));
					String line;
					int i = 0;
					
					while ((line = br.readLine()) != null) {
						if(line.equals(lineContenu)) {
							line_number = i;
						}
						
						i++;
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
		
		return line_number;
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
}

