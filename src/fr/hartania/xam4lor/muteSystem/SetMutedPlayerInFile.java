package fr.hartania.xam4lor.muteSystem;

import fr.hartania.xam4lor.fileSystem.FileSystems;

public class SetMutedPlayerInFile {
	public SetMutedPlayerInFile(String pl_name) {
		FileSystems.ajouterLigne("plugins/config/mutePlayers.txt", pl_name);
	}
}
