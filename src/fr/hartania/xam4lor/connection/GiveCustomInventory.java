package fr.hartania.xam4lor.connection;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GiveCustomInventory {
	public GiveCustomInventory(Player p) {
		p.getInventory().clear();
		p.getInventory().setArmorContents(new ItemStack[] {new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.AIR)});
		
		this.giveBoussole(p);
		
		if(p.isOp()) {
			this.giveOpTool(p);
		}
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
	
	private void giveOpTool(Player p) {
		ItemStack command_block = new ItemStack(Material.COMMAND, 1, (byte) 0);
		ItemMeta command_block_meta = command_block.getItemMeta();
		
		command_block_meta.setDisplayName(ChatColor.BLUE + "- Op Tools -");
		
		command_block.setItemMeta(command_block_meta);
		p.getInventory().addItem(command_block);
	}
	
	public static ItemStack getCommandBlock() {
		ItemStack command_block = new ItemStack(Material.COMMAND, 1, (byte) 0);
		ItemMeta command_block_meta = command_block.getItemMeta();
		
		command_block_meta.setDisplayName(ChatColor.BLUE + "- Op Tools -");
		
		command_block.setItemMeta(command_block_meta);
		
		return command_block;
	}
}
