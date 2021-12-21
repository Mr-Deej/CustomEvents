package me.drkmatr1984.customevents.tasks;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import me.drkmatr1984.customevents.moveEvents.SignificantPlayerMoveEvent;

public class PlayerMoveEventTask extends BukkitRunnable {
	
	private final HashMap<Player, Location> oldLocale = new HashMap<>();
	
	@Override
	public void run() {
		Bukkit.getServer().getOnlinePlayers();
		if(!Bukkit.getServer().getOnlinePlayers().isEmpty()){
			for(Player player : Bukkit.getServer().getOnlinePlayers())
			{
				Location l = player.getLocation();				
				if(oldLocale.containsKey(player)){
					if(!isSameBlocks(oldLocale.get(player), l)){
						SignificantPlayerMoveEvent event = new SignificantPlayerMoveEvent(player, oldLocale.get(player), l);
						Bukkit.getServer().getPluginManager().callEvent(event);
						oldLocale.remove(player);
					    oldLocale.put(player, l);
					}
				}else{
					oldLocale.put(player, l);
				}
			}
		}
	}
	
	public boolean isSameBlocks(Location loc1, Location loc2) {
        if (loc1.getBlockX() != loc2.getBlockX()) return false;
        if (loc1.getBlockZ() != loc2.getBlockZ()) return false;
        if (loc1.getBlockY() != loc2.getBlockY()) return false;
		return loc1.getWorld() == loc2.getWorld();
	}
}