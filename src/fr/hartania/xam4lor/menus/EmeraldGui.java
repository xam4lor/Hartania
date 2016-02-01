package fr.hartania.xam4lor.menus;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EmeraldGui {
	public EmeraldGui(Player p) {
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
		
		ItemStack game1 = new ItemStack(Material.BOW, 1);
		ItemMeta game1_meta = game1.getItemMeta();
		game1_meta.setDisplayName(ChatColor.BLUE + "- ICE SHOT -");
		game1.setItemMeta(game1_meta);
		
		iv.setItem(13, game1);
		
		ItemStack musee = new ItemStack(Material.ARMOR_STAND, 1);
		ItemMeta musee_meta = musee.getItemMeta();
		musee_meta.setDisplayName(ChatColor.BLUE + "- MUSEE ARMORSTAND -");
		musee.setItemMeta(musee_meta);
		
		iv.setItem(11, musee);
		
		ItemStack pvp = new ItemStack(Material.DIAMOND_SWORD, 1);
		ItemMeta pvp_meta = pvp.getItemMeta();
		pvp_meta.setDisplayName(ChatColor.BLUE + "- PVP ARENA -");
		pvp.setItemMeta(pvp_meta);
		
		iv.setItem(15, pvp);
		
		ItemStack game2 = new ItemStack(Material.ARROW, 1);
		ItemMeta game2_meta = game2.getItemMeta();
		game2_meta.setDisplayName(ChatColor.BLUE + "- BOW SHOT -");
		game2.setItemMeta(game2_meta);
		
		iv.setItem(20, game2);
		
		ItemStack jump = new ItemStack(Material.SLIME_BLOCK, 1);
		ItemMeta jump_meta = jump.getItemMeta();
		jump_meta.setDisplayName(ChatColor.BLUE + "- JUMP -");
		jump.setItemMeta(jump_meta);
		
		iv.setItem(22, jump);
		
		ItemStack skywars = new ItemStack(Material.FEATHER, 1);
		ItemMeta skywars_meta = skywars.getItemMeta();
		skywars_meta.setDisplayName(ChatColor.BLUE + "- SKYWARS -");
		skywars.setItemMeta(skywars_meta);
		
		iv.setItem(24, skywars);
		
		ItemStack gadget = new ItemStack(Material.NETHER_STAR, 1);
		ItemMeta gadget_meta = gadget.getItemMeta();
		gadget_meta.setDisplayName(ChatColor.BLUE + "- GADGETS -");
		gadget.setItemMeta(gadget_meta);
		
		iv.setItem(40, gadget);
		//-------------------------------
		
		for(int i = 0; i != 54; i++) {
			if(i != 4 && i != 11 && i != 13 && i != 15 && i != 20 && i != 22 && i != 24 && i != 40) {
				iv.setItem(i, glass);
			}
		}
		
		return iv;
	}
}
