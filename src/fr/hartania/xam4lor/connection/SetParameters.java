package fr.hartania.xam4lor.connection;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SetParameters {
	public SetParameters(Player p) {
		p.setHealth(20);
		p.setFoodLevel(21);
		p.setExhaustion(5F);
		p.getInventory().clear();
		p.getInventory().setArmorContents(new ItemStack[] {new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.AIR)});
		
		p.getPlayer().teleport(new Location(Bukkit.getServer().getWorlds().get(0), -794, 5, -280));
	}
}
