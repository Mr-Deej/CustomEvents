package me.drkmatr1984.customevents.interactEvents;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class PlayerInteractEntityLeftClickEvent extends PlayerInteractEntityBaseClickEvent{
	private boolean cancelDamage = false;
	
	public PlayerInteractEntityLeftClickEvent(Plugin plugin, Player player, Entity clickedEntity, boolean cancelDamage){
		super(plugin, player, clickedEntity);
	}
	
	public void setDamageCancelled(boolean cancelled) {
		this.cancelDamage = cancelled;
	}
	
	public boolean getDamageCancelled() {
		return this.cancelDamage;
	}
}