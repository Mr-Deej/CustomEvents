package me.drkmatr1984.customevents.interactEvents;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class PlayerInteractEntityRightClickEvent extends PlayerInteractEntityBaseClickEvent{
	
	public PlayerInteractEntityRightClickEvent(Plugin plugin, Player player, Entity clickedEntity){
		super(plugin, player, clickedEntity);
	}
}