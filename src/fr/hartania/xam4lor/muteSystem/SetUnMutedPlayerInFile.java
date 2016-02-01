package fr.hartania.xam4lor.muteSystem;

import org.bukkit.Bukkit;

import fr.hartania.xam4lor.fileSystem.FileSystems;

public class SetUnMutedPlayerInFile {
	public SetUnMutedPlayerInFile(String pl_name) {
		int line = FileSystems.getLineOf("plugins/config/mutePlayers.txt", pl_name);
		
		if(line != 0) {
			FileSystems.deleteLine("plugins/config/mutePlayers.txt", line);
		}
		else {
			Bukkit.getLogger().info("Le joueur " + pl_name + " n'est pas mute (ceci n'affectera pas le programme).");
		}
	}
}

