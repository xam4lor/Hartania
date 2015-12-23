package fr.hartania.xam4lor.connection;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GiveCustomInventory {
	@SuppressWarnings("unused")
	private static ItemStack boussole;
	
	public GiveCustomInventory(Player p) {
		p.getInventory().clear();
		p.getInventory().setArmorContents(new ItemStack[] {new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.AIR)});
		
		this.giveBoussole(p);
	}

	private void giveBoussole(Player p) {
		ItemStack boussole = new ItemStack(Material.COMPASS, 1, (byte) 0);
		ItemMeta boussole_meta = boussole.getItemMeta();
		
		boussole_meta.setDisplayName(ChatColor.BLUE + "- Menu -");
		
		boussole.setItemMeta(boussole_meta);
		p.getInventory().addItem(boussole);
	}

	public static ItemStack getBoussole() {
		ItemStack boussole = new ItemStack(Material.COMPASS, 1, (byte) 0);
		ItemMeta boussole_meta = boussole.getItemMeta();
		
		boussole_meta.setDisplayName(ChatColor.BLUE + "- Menu -");
		
		boussole.setItemMeta(boussole_meta);
		
		return boussole;
	}
}
