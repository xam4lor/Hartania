package fr.hartania.xam4lor.menus;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BoussoleGui {
	public BoussoleGui(Player p) {
		p.openInventory(this.createInv(p));
	}
	
	public Inventory createInv(Player p) {
		Inventory iv = Bukkit.getServer().createInventory(p, 54, "- Menu -");
		
		ItemStack spawn = new ItemStack(Material.DIAMOND, 1);
		ItemMeta spawn_meta = spawn.getItemMeta();
		spawn_meta.setDisplayName(ChatColor.GOLD + "- SPAWN -");
		spawn.setItemMeta(spawn_meta);
		
		iv.setItem(4, spawn);
		
		ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1);
		ItemMeta glass_meta = spawn.getItemMeta();
		glass_meta.setDisplayName(ChatColor.GREEN + " ");
		glass.setItemMeta(glass_meta);
		
		for(int i = 0; i != 9; i++) {
			if(i != 4) {
				iv.setItem(i, glass);
			}
		}
		
		ItemStack game1 = new ItemStack(Material.BOW, 1);
		ItemMeta game1_meta = game1.getItemMeta();
		game1_meta.setDisplayName(ChatColor.BLUE + "- ICE BOW -");
		game1.setItemMeta(game1_meta);
		
		iv.setItem(13, game1);
		
		for(int i = 9; i != 18; i++) {
			if(i != 13) {
				iv.setItem(i, glass);
			}
		}
		
		return iv;
	}
}
