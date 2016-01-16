package fr.hartania.xam4lor.connection;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GiveCustomInventory {
	public GiveCustomInventory(Player p) {
		this.giveEmerald(p);
		
		if(p.isOp()) {
			this.giveDiamond(p);
		}
	}

	private void giveEmerald(Player p) {
		ItemStack boussole = new ItemStack(Material.EMERALD, 1, (byte) 0);
		ItemMeta boussole_meta = boussole.getItemMeta();
		
		boussole_meta.setDisplayName(ChatColor.BLUE + "- Menu -");
		
		boussole.setItemMeta(boussole_meta);
		p.getInventory().addItem(boussole);
	}

	public static ItemStack getEmerald() {
		ItemStack boussole = new ItemStack(Material.EMERALD, 1, (byte) 0);
		ItemMeta boussole_meta = boussole.getItemMeta();
		
		boussole_meta.setDisplayName(ChatColor.BLUE + "- Menu -");
		
		boussole.setItemMeta(boussole_meta);
		
		return boussole;
	}
	
	private void giveDiamond(Player p) {
		ItemStack emerald = new ItemStack(Material.DIAMOND, 1, (byte) 0);
		ItemMeta emerald_meta = emerald.getItemMeta();
		
		emerald_meta.setDisplayName(ChatColor.BLUE + "- Op Tools -");
		
		emerald.setItemMeta(emerald_meta);
		p.getInventory().addItem(emerald);
	}
	
	public static ItemStack getDiamond() {
		ItemStack emerald = new ItemStack(Material.DIAMOND, 1, (byte) 0);
		ItemMeta emerald_meta = emerald.getItemMeta();
		
		emerald_meta.setDisplayName(ChatColor.BLUE + "- Op Tools -");
		
		emerald.setItemMeta(emerald_meta);
		
		return emerald;
	}
}
