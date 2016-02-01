package fr.hartania.xam4lor.menus;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.hartania.xam4lor.main.MainClass;

public class GadgetsGui {
	public GadgetsGui(Player p) {
		p.openInventory(this.createInv(p));
		p.sendMessage(ChatColor.RED + MainClass.getServerName() + "En développement");
		p.closeInventory();
	}
	
	public Inventory createInv(Player p) {
		Inventory iv = Bukkit.getServer().createInventory(p, 54, "- Gadgets -");
		
		ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1);
		ItemMeta glass_meta = glass.getItemMeta();
		glass_meta.setDisplayName(" ");
		glass.setItemMeta(glass_meta);
		
		for(int i = 0; i != 54; i++) {
			iv.setItem(i, glass);
		}
		
		return iv;
	}
}
