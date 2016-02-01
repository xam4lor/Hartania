package fr.hartania.xam4lor.main;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import fr.hartania.xam4lor.connection.GiveCustomInventory;
import fr.hartania.xam4lor.connection.SetParameters;
import fr.hartania.xam4lor.errorSystem.OnErrorSystem;
import fr.hartania.xam4lor.events.Events;
import fr.hartania.xam4lor.fly.FlySysteme;
import fr.hartania.xam4lor.grades.GetAdminsPlayersByFile;
import fr.hartania.xam4lor.grades.GetModerateursPlayersByFile;
import fr.hartania.xam4lor.grades.SetGrade;
import fr.hartania.xam4lor.infos.AddServerInfo;
import fr.hartania.xam4lor.infos.SayServerInfos;
import fr.hartania.xam4lor.lookup.LookupPlayer;
import fr.hartania.xam4lor.mails.MailSystem;
import fr.hartania.xam4lor.muteSystem.GetMutedPlayersByFile;
import fr.hartania.xam4lor.muteSystem.SetMutedPlayerInFile;
import fr.hartania.xam4lor.muteSystem.SetUnMutedPlayerInFile;
import fr.hartania.xam4lor.pubMessage.RandomPubMessage;
import fr.hartania.xam4lor.reseau.Inet;
import fr.hartania.xam4lor.scoreboards.DisplayScoreboard;

public class MainClass extends JavaPlugin {
	
	public Logger log = Logger.getLogger("Minecraft");
	public static World main_world = Bukkit.getServer().getWorld("world");
	private static ArrayList<String> muteListName = new ArrayList<>();
	public static ArrayList<String> moderatorsListName = new ArrayList<>();
	public static ArrayList<String> adminsListName = new ArrayList<>();
	public static ArrayList<String> notifOnListName = new ArrayList<>();
	public static ArrayList<String> nbPlConnexion = new ArrayList<>();
	public static ArrayList<String> plDeathsInSkywars = new ArrayList<>();
	
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new Events(this), this);
		notifOnListName.clear();
		this.log.info(MainClass.getServerName() + "If this is not the correct default world, please contact the developper. World: " + Bukkit.getServer().getWorlds().get(0));
		SetPubMessage();
		
		try {
			new GetMutedPlayersByFile();
			this.log.info(MainClass.getServerName() + "Liste des players mute bien configurée.");
		}
		catch(Exception e) {
			e.printStackTrace();
			new OnErrorSystem(e.toString());
		}
		
		try {
			new GetModerateursPlayersByFile();
			
			this.log.info(MainClass.getServerName() + "Liste des modérateurs bien configurée.");
		}
		catch(Exception e) {
			this.log.info(MainClass.getServerName() + "Erreur lors de la configuration des joueurs modérateurs.");
			e.printStackTrace();
			new OnErrorSystem(e.toString());
		}
		
		try {
			new GetAdminsPlayersByFile();
			
			this.log.info(MainClass.getServerName() + "Liste des administrateurs bien configurée.");
		}
		catch(Exception e) {
			this.log.info(MainClass.getServerName() + "Erreur lors de la configuration des joueurs administrateurs.");
			e.printStackTrace();
			new OnErrorSystem(e.toString());
		}
		
		try {
			BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
			scheduler.scheduleSyncRepeatingTask(Bukkit.getServer().getPluginManager().getPlugin("Hartania-Plugin"), new Runnable() {
				@Override
				public void run() {
					new SystemClock();
				}
			}, 0L, 1L); //tous les 1 ticks (1200 tick = 1 min)
			
			this.log.info(MainClass.getServerName() + "Clock lancée.");
		}
		catch(Exception e) {
			this.log.info(MainClass.getServerName() + "Erreur lors de la clock.");
			e.printStackTrace();
			new OnErrorSystem(e.toString());
		}
		
		new Inet();
		
		this.log.info(MainClass.getServerName() + "Plugin launched");
	}

	@Override
	public void onDisable() {
		this.log.info(MainClass.getServerName() + "Plugin stopped");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) { //on teste les commandes
		//commande ht
		if(cmd.getName().equalsIgnoreCase("ht")) {
			if(sender instanceof Player) {
				Player pl = ((Player) sender).getPlayer();
				
				if(args.length == 0) {
					pl.sendMessage(ChatColor.RED + getCommandHtSyntaxe());
				}
				else if(args[0].equalsIgnoreCase("giveItems")) {
					new GiveCustomInventory(pl);
    			}
    			else if (!pl.isOp()) {
    				pl.sendMessage(ChatColor.RED + getServerName() + "Vous n'êtes pas un opérateur !");
    			}
    			else {
    				pl.sendMessage(ChatColor.RED + getCommandHtSyntaxe());
    			}
            }
	        else {
				if(args.length == 0) {
					sender.sendMessage(getCommandHtSyntaxe());
				}
				else if(args[0].equalsIgnoreCase("giveItems")) {
					try {
						if(args[1] != null) {
							new GiveCustomInventory(Bukkit.getPlayer(args[1]));
							sender.sendMessage("Les items ont bien été givés à " + Bukkit.getPlayer(args[1]).getDisplayName() + ".");
						}
						else {
							sender.sendMessage(getCommandHtGiveItemsSyntaxe());
						}
					}
					catch(Exception e) {
						sender.sendMessage(getCommandHtGiveItemsSyntaxe());
					}

				}
				else {
					sender.sendMessage(getCommandHtSyntaxe());
				}
	        }
        }
		
		//commande fly
		else if(cmd.getName().equalsIgnoreCase("fly")) {
			if(sender instanceof Player) {
				Player pl = ((Player) sender).getPlayer();
				
				if(args.length == 0) {
					pl.sendMessage(ChatColor.RED + getCommandFlySyntaxe());
				}
    			else if (!pl.isOp()) {
    				pl.sendMessage(ChatColor.RED + getServerName() + "Vous n'êtes pas un opérateur !");
    			}
				else if(args[0].equalsIgnoreCase("1")) {
					new FlySysteme(1, pl);
    			}
				else if(args[0].equalsIgnoreCase("0")) {
					new FlySysteme(0, pl);
    			}
				else if(args[0].equalsIgnoreCase("on")) {
					new FlySysteme(1, pl);
    			}
				else if(args[0].equalsIgnoreCase("off")) {
					new FlySysteme(0, pl);
    			}
    			else {
    				pl.sendMessage(ChatColor.RED + getCommandFlySyntaxe());
    			}
			}
			else {
				sender.sendMessage("Il faut être un joueur.");
			}
		}
		
		//commande grade
		else if(cmd.getName().equalsIgnoreCase("grade")) {
			if(sender instanceof Player) {
				Player pl = ((Player) sender).getPlayer();
				
				try {
					if(args.length == 0) {
						pl.sendMessage(ChatColor.RED + getCommandGradeSyntaxe());
					}
	    			else if (!pl.isOp()) {
	    				pl.sendMessage(ChatColor.RED + getServerName() + "Vous n'êtes pas un opérateur !");
	    			}
	    			else {
	    				boolean gradeCorrect = false;
	    				String[] grades = {"modo","admin","joueur"};
	    				
	    				for(int i = 0; i != grades.length; i++) {
	    					if(grades[i].equals(args[1])) {
	    						gradeCorrect = true;
	    					}
	    				}
	    				
	    				if(gradeCorrect) {
	    					Bukkit.getPlayer(args[0]).sendMessage(ChatColor.RED + getServerName() + "Vous avez été changé de grade. Veuillez vous déconnecter puis vous reconnecter, merci.");
	    					new SetGrade(args[0], args[1], pl);
	    				}
	    				else {
	    					pl.sendMessage(ChatColor.RED + getServerName() + "Ce grade n'existe pas. Liste des grades : 'modo', 'admin', 'joueur'.");
	    				}
	    			}
				}
				catch(Exception e) {
					pl.sendMessage(ChatColor.RED + getCommandGradeSyntaxe());
				}
			}
			else {
				sender.sendMessage("Il faut être un joueur.");
			}
		}
		
		//messages privés
		else if(cmd.getName().equalsIgnoreCase("msg") || cmd.getName().equalsIgnoreCase("m")) {
			if(sender instanceof Player) {
				Player pl = ((Player) sender).getPlayer();
				Player player_choose;
				String message = "";
				boolean boucle_value = true;
				int i = 2;
				
				try {
					if(args[0] != null && args.length != 0) {
						message = message + args[1];
						
						while(boucle_value) {
							try {
								message = message + " " + args[i];
							}
							catch(Exception e) {
								boucle_value = false;
							}
							
							i++;
						}
						
						player_choose = Bukkit.getPlayer(args[0]);
						player_choose.sendMessage(ChatColor.RED + MainClass.getServerName() + ChatColor.GREEN + ChatColor.ITALIC + "Message de " + pl.getDisplayName() + " ---> " + ChatColor.RESET + message);
						pl.sendMessage(ChatColor.RED + MainClass.getServerName() + ChatColor.GREEN + ChatColor.ITALIC + "Message bien envoyé à " + player_choose.getDisplayName() + ": " + ChatColor.RESET + message);
						
			        	for(Player pp : Bukkit.getOnlinePlayers()) {
			        		if(MainClass.isNotifMsgOn(pp) && pp != sender) {
			        			pp.playSound(pp.getPlayer().getLocation(), Sound.NOTE_PIANO, 200, 1);
			        		}
			        	}
					}
					else {
						pl.sendMessage(ChatColor.RED + getCommandMsgSyntaxe());
					}
				}
				catch(Exception e) {
					pl.sendMessage(ChatColor.RED + getCommandMsgSyntaxe());
				}
			}
			else {
				sender.sendMessage("Il faut être un joueur.");
			}
		}
		
		//commande global
		else if(cmd.getName().equalsIgnoreCase("global")) {
			String message = "";
			boolean boucle_value = true;
			int i = 1;
			
			if(sender.isOp()) {
				try {
					if(args.length != 0) {
						message = message + args[0];
						
						while(boucle_value) {
							try {
								message = message + " " + args[i];
							}
							catch(Exception e) {
								boucle_value = false;
							}
							
							i++;
						}
						
						Bukkit.getServer().broadcastMessage(ChatColor.RED + MainClass.getServerName2() + ChatColor.GREEN + message);
						
			        	for(Player pp : Bukkit.getOnlinePlayers()) {
			        		if(MainClass.isNotifMsgOn(pp) && pp != sender) {
			        			pp.playSound(pp.getPlayer().getLocation(), Sound.NOTE_PIANO, 200, 1);
			        		}
			        	}
					}
					else {
						sender.sendMessage(ChatColor.RED + getCommandGlobalSyntaxe());
					}
				}
				catch(Exception e) {
					sender.sendMessage(ChatColor.RED + getCommandGlobalSyntaxe());
				}
			}
			else {
				sender.sendMessage(ChatColor.RED + getServerName() + "Vous n'êtes pas un opérateur !");
			}
		}
		
		//actualités
		else if(cmd.getName().equalsIgnoreCase("actus")) {
			try {
				if(args.length == 0) {
					sender.sendMessage(ChatColor.RED + getCommandActusSyntaxe());
				}
				else if(!sender.isOp()) {
					sender.sendMessage(ChatColor.RED + MainClass.getServerName() + "Vous n'êtes pas un opérateur.");
				}
				else if(args[0].equals("list")) {
					new SayServerInfos(sender);
				}
				else if(args[0].equals("add")) {
					String message = "";
					boolean condition_boucle = true;
					int i = 1;
					
					try {
						while(condition_boucle) {
							try {
								message += (args[i] + " ");
								i++;
							}
							catch(ArrayIndexOutOfBoundsException e) {
								condition_boucle = false;
							}
						}
					}
					catch(Exception e) {
						sender.sendMessage(ChatColor.RED + getCommandServerInfosAddSyntaxe());
					}
					
					if(message != "") {
						new AddServerInfo(message);
						sender.sendMessage(ChatColor.RED + MainClass.getServerName() + ChatColor.GREEN + "Cette actualité a bien été rajoutée : " + ChatColor.RESET + message);
					}
					else {
						sender.sendMessage(ChatColor.RED + getCommandServerInfosAddSyntaxe());
					}
				}
				else {
					sender.sendMessage(ChatColor.RED + getCommandActusSyntaxe());
				}
			}
			catch(Exception e) {
				sender.sendMessage(ChatColor.RED + getCommandActusSyntaxe());
			}
		}
		
		//mute un joueur
		else if(cmd.getName().equalsIgnoreCase("mute")) {
			if(sender instanceof Player) {
				Player pl = ((Player) sender).getPlayer();
				
				if(args.length == 0) {
					pl.sendMessage(ChatColor.RED + getCommandMuteSyntaxe());
				}
				else if(pl.isOp()) {
					try {
						setMutePlayer(args[0]);
						new SetMutedPlayerInFile(args[0]);
						pl.sendMessage(ChatColor.RED + MainClass.getServerName() + ChatColor.GREEN + "Le joueur " + args[0] + " a bien été mute.");
					}
					catch(Exception e) {
						e.printStackTrace();
					}
				}
				else {
					sender.sendMessage(ChatColor.RED + MainClass.getServerName() + "Vous n'êtes pas un opérateur.");
				}
			}
			else {
				sender.sendMessage(MainClass.getServerName() + "Il faut être un joueur.");
			}
		}
		
		//unmute un joueur
		else if(cmd.getName().equalsIgnoreCase("unmute")) {
			if(sender instanceof Player) {
				Player pl = ((Player) sender).getPlayer();
				
				if(args.length == 0) {
					pl.sendMessage(ChatColor.RED + getCommandUnMuteSyntaxe());
				}
				else if(pl.isOp()) {
					try {
						unmutePlayer(args[0]);
						new SetUnMutedPlayerInFile(args[0]);
						pl.sendMessage(ChatColor.RED + MainClass.getServerName() + ChatColor.GREEN + "Le joueur " + args[0] + " a bien été unmute.");
					}
					catch(Exception e) {
						e.printStackTrace();
					}
				}
				else {
					sender.sendMessage(ChatColor.RED + MainClass.getServerName() + "Vous n'êtes pas un opérateur.");
				}
			}
			else {
				sender.sendMessage(MainClass.getServerName() + "Il faut être un joueur.");
			}
		}
		
		//monnaie d'un joueur
		else if(cmd.getName().equalsIgnoreCase("money")) {
			if(sender instanceof Player) {
				Player pl = ((Player) sender).getPlayer();
				
				if(args.length == 0) {
					pl.sendMessage(ChatColor.RED + MainClass.getServerName() + ChatColor.GREEN + "Vous avez " + ChatColor.BOLD + Bukkit.getServer().getScoreboardManager().getMainScoreboard().getObjective("Money").getScore(pl.getName()).getScore() + ChatColor.RESET + ChatColor.GREEN + " de monnaie.");
				}
				else if(pl.isOp()) {
					try {
						pl.sendMessage(ChatColor.RED + MainClass.getServerName() + ChatColor.GREEN + Bukkit.getServer().getPlayer(args[0]).getName() + " a " + ChatColor.BOLD + Bukkit.getServer().getScoreboardManager().getMainScoreboard().getObjective("Money").getScore(Bukkit.getServer().getPlayer(args[0]).getName()).getScore() + ChatColor.RESET + ChatColor.GREEN + " de monnaie.");
					}
					catch(Exception e) {
						pl.sendMessage(ChatColor.RED + MainClass.getServerName() + args[0] + " n'est pas en ligne.");
					}
				}
				else {
					pl.sendMessage(ChatColor.RED + MainClass.getServerName() + ChatColor.GREEN + "Vous avez " + ChatColor.BOLD + Bukkit.getServer().getScoreboardManager().getMainScoreboard().getObjective("Money").getScore(pl.getName()).getScore() + ChatColor.RESET + ChatColor.GREEN + " de monnaie " + ChatColor.RED + "(il faut être un opérateur pour voir la monnaie d'un joueur).");
				}
			}
			else {
				sender.sendMessage(MainClass.getServerName() + "Il faut être un joueur.");
			}
		}
		
		//lookup un joueur
		else if(cmd.getName().equalsIgnoreCase("lookup")) {
			if(sender instanceof Player) {
				Player pl = ((Player) sender).getPlayer();
				
				if(!pl.isOp()) {
					pl.sendMessage(ChatColor.RED + MainClass.getServerName() + "Vous n'êtes pas un opérateur.");
				}
				else if(args.length == 0) {
					pl.sendMessage(ChatColor.RED + this.getCommandLookupSyntaxe());
				}
				else {
					try {
						boolean isPlOnline = false;
						String playerToLook = args[0];
						
						for(Player pp : Bukkit.getOnlinePlayers()) {
							if(pp.getName().equals(playerToLook)) {
								isPlOnline = true;
							}
						}
						
						if(!isPlOnline) {
							pl.sendMessage(ChatColor.RED + MainClass.getServerName() + playerToLook + " n'est pas en ligne.");
						}
						else {
							new LookupPlayer(pl, Bukkit.getPlayer(playerToLook));
						}
					}
					catch(Exception e) {
						pl.sendMessage(ChatColor.RED + this.getCommandLookupSyntaxe());
					}
				}
			}
			else {
				sender.sendMessage(MainClass.getServerName() + "Il faut être un joueur.");
			}
		}
		
		//lookup un joueur
		else if(cmd.getName().equalsIgnoreCase("sound-msg")) {
			if(sender instanceof Player) {
				Player pl = ((Player) sender).getPlayer();
				
				if(args.length == 0) {
					pl.sendMessage(ChatColor.RED + this.getCommandSoundMsgSyntaxe());
				}
				else if(args[0].equals("1") || args[0].equals("0") || args[0].equals("on") || args[0].equals("off")) {
					if(args[0].equals("1") || args[0].equals("on")) {
						notifOnListName.add(pl.getName());
						pl.sendMessage(ChatColor.RED + MainClass.getServerName() + ChatColor.GREEN + "Le son à chaque message est désormais sur " + ChatColor.BOLD + "on.");
					}
					else {
						notifOnListName.remove(pl.getName());
						pl.sendMessage(ChatColor.RED + MainClass.getServerName() + ChatColor.GREEN + "Le son à chaque message est désormais sur " + ChatColor.BOLD + "off.");
					}
				}
				else {
					pl.sendMessage(ChatColor.RED + this.getCommandSoundMsgSyntaxe());
				}
			}
			else {
				sender.sendMessage(MainClass.getServerName() + "Il faut être un joueur.");
			}
		}
		
		//lookup un joueur
		else if(cmd.getName().equalsIgnoreCase("clear-chat")) {
			if(sender instanceof Player) {
				Player pl = ((Player) sender).getPlayer();
				
				for (int i = 0; i < 100; i++) {
					pl.sendMessage(" ");
				}
			}
			else {
				if(args.length == 0) {
					sender.sendMessage(ChatColor.RED + this.getCommandChatCmdBlockSyntaxe());
				}
				else {
					try {
						Player pl = Bukkit.getPlayer(args[0]);
						sender.sendMessage(ChatColor.RED + MainClass.getServerName() + ChatColor.GREEN + "Le chat du joueur " + pl.getName() + " a bien été clear.");
						for (int i = 0; i < 100; i++) {
							pl.sendMessage(" ");
						}
					}
					catch(Exception e) {
						sender.sendMessage(ChatColor.RED + MainClass.getServerName() + args[0] + " n'est pas en ligne.");
					}
				}
			}
		}
		
		//mails
		else if(cmd.getName().equalsIgnoreCase("mail")) {
			try {
				if(args.length == 0) {
					sender.sendMessage(ChatColor.RED + getCommandMailSyntaxe());
				}
				else if(args[0] != null && args[1] != null) {
					String message = "";
					boolean condition_boucle = true;
					int i = 1;
					String playerToSend = args[0];
					
					try {
						while(condition_boucle) {
							try {
								message += (args[i] + " ");
								i++;
							}
							catch(ArrayIndexOutOfBoundsException e) {
								condition_boucle = false;
							}
						}
					}
					catch(Exception e) {
						sender.sendMessage(ChatColor.RED + getCommandMailSyntaxe());
					}
					
					if(message != "") {
						try {
							MailSystem.sendMail(playerToSend, message,sender.getName());
							sender.sendMessage(ChatColor.RED + MainClass.getServerName() + ChatColor.GREEN + "Mail bien envoyé à " + playerToSend  + " : " + ChatColor.RESET + message);
						}
						catch(Exception e) {
							sender.sendMessage(ChatColor.RED + MainClass.getServerName() + "Erreur dans l'envoi du mail");
						}
					}
					else {
						sender.sendMessage(ChatColor.RED + getCommandMailSyntaxe());
					}
				}
				else {
					sender.sendMessage(ChatColor.RED + getCommandMailSyntaxe());
				}
			}
			catch(Exception e) {
				sender.sendMessage(ChatColor.RED + getCommandMailSyntaxe());
			}
		}
		
		//lookup un joueur
		else if(cmd.getName().equalsIgnoreCase("return")) {
			if(sender instanceof Player) {
				Player pl = ((Player) sender).getPlayer();
				
				if(MainClass.plDeathsInSkywars.contains(pl.getName())) {
					new SetParameters(pl);
					new GiveCustomInventory(pl);
					
					if(pl.isOp()) {
						pl.setGameMode(GameMode.CREATIVE);
					}
					
					MainClass.plDeathsInSkywars.remove(pl.getName());
				}
			}
		}
		
		return true;
	}

	public void SetPubMessage() {
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
		
		scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
			@Override
			public void run() {
				new RandomPubMessage();
			}
		}, 0L, 6000L); //tous les 6000 ticks (1200 tick = 1 min)
	}

	private String getCommandFlySyntaxe() {
		return "Syntaxe : /fly <1/0/on/off>";
	}
	
	private String getCommandSoundMsgSyntaxe() {
		return "Syntaxe : /sound-msg <1/0/on/off>";
	}
	
	private String getCommandServerInfosAddSyntaxe() {
		return "Syntaxe : /actus add <texte>";
	}
	
	private String getCommandMuteSyntaxe() {
		return "Syntaxe : /mute <player>";
	}
	
	private String getCommandUnMuteSyntaxe() {
		return "Syntaxe : /unmute <player>";
	}
	
	private String getCommandMailSyntaxe() {
		return "Syntaxe : /mail <player> <message>";
	}
	
	private String getCommandLookupSyntaxe() {
		return "Syntaxe : /lookup <player>";
	}
	
	private String getCommandActusSyntaxe() {
		return "Syntaxe : /actus <list/add>";
	}
	
	private String getCommandGlobalSyntaxe() {
		return "Syntaxe : /global <message>";
	}

	private String getCommandGradeSyntaxe() {
		return "Syntaxe : /grade <pseudo> <modo/admin/joueur>";
	}
	
	private String getCommandMsgSyntaxe() {
		return "Syntaxe : /msg <pseudo> <message> ou /m <pseudo> <message>";
	}
	private String getCommandChatCmdBlockSyntaxe() {
		return "Syntaxe : /clear-chat <player>";
	}

	private String getCommandHtGiveItemsSyntaxe() {
		return "Syntaxe : /ht giveItems <@p/@r/@a/pseudo>";
	}

	private String getCommandHtSyntaxe() {
		return "Syntaxe : /ht <giveItems>";
	}

	public static String getServerName() {
		return "[Hartania] ";
	}
	
	public static String getServerName2() {
		return "[Hartania Global-Message] ";
	}

	public static boolean isPlayerMute(Player pl) {
		String pl_name = pl.getName();
		
		if(MainClass.muteListName.contains(pl_name)) {
			return true;
		}
		else {
			return false;
		}
	}

	public static void setMutePlayer(String player_name) { //ajoute un joueur mute dans la Collection<> mutePlayers
		MainClass.muteListName.add(player_name);
	}
	
	public static void unmutePlayer(String player_name) { //ajoute un joueur mute dans la Collection<> mutePlayers
		MainClass.muteListName.remove(player_name);
	}
	
	public static boolean isPlayerModerateur(Player pl) {
		String pl_name = pl.getName();
		
		if(MainClass.moderatorsListName.contains(pl_name)) {
			return true;
		}
		else {
			return false;
		}
	}

	public static void setModerateurPlayer(String player_name) { //ajoute un joueur dans la Collection<> moderatorsListName
		MainClass.moderatorsListName.add(player_name);
	}
	
	public static void unModerateurPlayer(String player_name) { //ajoute un joueur dans la Collection<> moderatorsListName
		MainClass.adminsListName.remove(player_name);
	}
	
	public static boolean isPlayerAdmin(Player pl) {
		String pl_name = pl.getName();
		
		if(MainClass.adminsListName.contains(pl_name)) {
			return true;
		}
		else {
			return false;
		}
	}

	public static void setAdminPlayer(String player_name) { //ajoute un joueur dans la Collection<> adminsListName
		MainClass.adminsListName.add(player_name);
	}
	
	public static void unAdminPlayer(String player_name) { //ajoute un joueur dans la Collection<> adminsListName
		MainClass.adminsListName.remove(player_name);
	}
	
	public static String getPlayerGrade(Player pl) {
		if(MainClass.isPlayerAdmin(pl)) {
			return "administrateur";
		}
		else if(MainClass.isPlayerModerateur(pl)) {
			return "modérateur";
		}
		else {
			return "joueur";
		}
	}
	
	public static int getPlayerMoney(Player pl) {
		return Bukkit.getServer().getScoreboardManager().getMainScoreboard().getObjective("Money").getScore(pl.getName()).getScore();
	}
	
	public static void addPlayerMoney(Player pl, int money) {
		Bukkit.getServer().getScoreboardManager().getMainScoreboard().getObjective("Money").getScore(pl.getName()).setScore(Bukkit.getServer().getScoreboardManager().getMainScoreboard().getObjective("Money").getScore(pl.getName()).getScore() + money);
	}
	
	public static int getPlayerKills(Player pl) {
		return Bukkit.getServer().getScoreboardManager().getMainScoreboard().getObjective("Kill").getScore(pl.getName()).getScore();
	}

	public static void updateScoreboardTick(final Player pl) {
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
		scheduler.scheduleSyncRepeatingTask(Bukkit.getServer().getPluginManager().getPlugin("Hartania-Plugin"), new Runnable() {
			@Override
			public void run() {
				DisplayScoreboard.updateScoreboard(pl);
			}
		}, 0L, 30L); //tous les 10 ticks (1200 tick = 1 min)
		
	}

	public static boolean isNotifMsgOn(Player pl) {
		if(notifOnListName.contains(pl.getName())) {
			return true;
		}
		else {
			return false;
		}
	}

	// a_min doit être la coordonnée plus petite et a_base2 la plus grande (de chaque côté)
	public static boolean isEnterCoords(Player pl, int x_min, int y_min, int z_min, int x_max, int y_max, int z_max) {
		if(
				((int) pl.getLocation().getX() >= x_min) 
				&& ((int) pl.getLocation().getY() >= y_min) 
				&& ((int) pl.getLocation().getZ() >= z_min) 
				&& ((int) pl.getLocation().getX() <= x_max) 
				&& ((int) pl.getLocation().getY() <= y_max) 
				&& ((int) pl.getLocation().getZ() <= z_max)
			)
		{
			return true;
		}
		else {
			return false;
		}
	}
	
	// a_min doit être la coordonnée plus petite et a_base2 la plus grande (de chaque côté)
	public static boolean isEnterCoords(Entity e, int x_min, int y_min, int z_min, int x_max, int y_max, int z_max) {
		if(
				((int) e.getLocation().getX() >= x_min) 
				&& ((int) e.getLocation().getY() >= y_min) 
				&& ((int) e.getLocation().getZ() >= z_min) 
				&& ((int) e.getLocation().getX() <= x_max) 
				&& ((int) e.getLocation().getY() <= y_max) 
				&& ((int) e.getLocation().getZ() <= z_max)
			)
		{
			return true;
		}
		else {
			return false;
		}
	}
}