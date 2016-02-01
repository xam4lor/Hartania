package fr.hartania.xam4lor.fileSystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import org.bukkit.Bukkit;

import fr.hartania.xam4lor.errorSystem.OnErrorSystem;
import fr.hartania.xam4lor.main.MainClass;

public class FileSystems {
	public static void deleteLine(String fileName, int lineNumber) {
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
	
	public static void ajouterLigne(String filename, String text) {
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
			new OnErrorSystem(e.toString());
			new OnErrorSystem(e.toString());
		} finally {
			try {
				bufWriter.close();
				fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
				new OnErrorSystem(e.toString());
			}
		}
    }
	
	public static int getLineOf(String fileName, String lineContenu) {
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
				Bukkit.getLogger().warning(MainClass.getServerName() + "Impossible de trouver le fichier dont l'emplacement est : " + fileName);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			new OnErrorSystem(e.toString());
		}
		
		return line_number;
	}
}
