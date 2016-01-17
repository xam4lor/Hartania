package fr.hartania.xam4lor.muteSystem;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SetMutedPlayerInFile {
	public SetMutedPlayerInFile(String pl_name) {
		this.ajouterLigne("plugins/config/mutePlayers.txt", pl_name);
	}
	
	private void ajouterLigne(String filename, String text) {
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
