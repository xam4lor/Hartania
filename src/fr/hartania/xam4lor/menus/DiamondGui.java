package fr.hartania.xam4lor.menus;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class DiamondGui {
	public DiamondGui(Player p) {
		p.openInventory(this.createInv(p));
	}
	
	public Inventory createInv(Player p) {
		Inventory iv = Bukkit.getServer().createInventory(p, 54, "- Op Tools -");
		
		ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1);
		ItemMeta glass_meta = glass.getItemMeta();
		glass_meta.setDisplayName(" ");
		glass.setItemMeta(glass_meta);
		
		for(int i = 0; i != 54; i++) {
			iv.setItem(i, glass);
		}
		
		ItemStack survival = new ItemStack(Material.STAINED_CLAY, 1, (byte) 14);
		ItemMeta survival_meta = survival.getItemMeta();
		survival_meta.setDisplayName(ChatColor.GREEN + "SURVIE");
		survival.setItemMeta(survival_meta);
		
		ItemStack creatif = new ItemStack(Material.STAINED_CLAY, 1, (byte) 11);
		ItemMeta creatif_meta = creatif.getItemMeta();
		creatif_meta.setDisplayName(ChatColor.GREEN + "CREATIF");
		creatif.setItemMeta(creatif_meta);
		
		ItemStack spectator = new ItemStack(Material.STAINED_CLAY, 1, (byte) 4);
		ItemMeta spectator_meta = spectator.getItemMeta();
		spectator_meta.setDisplayName(ChatColor.GREEN + "SPECTATEUR");
		spectator.setItemMeta(spectator_meta);
		
		ItemStack commands_block = new ItemStack(Material.COMMAND, 1, (byte) 4);
		ItemMeta commands_block_meta = commands_block.getItemMeta();
		commands_block_meta.setDisplayName(ChatColor.RED + "COMMANDS BLOCKS");
		commands_block.setItemMeta(commands_block_meta);
		
		iv.setItem(11, survival);
		iv.setItem(13, creatif);
		iv.setItem(15, spectator);
		iv.setItem(22, commands_block);
		
		return iv;
	}
}
