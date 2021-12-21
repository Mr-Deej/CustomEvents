package me.drkmatr1984.customevents.listeners.entityListeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.plugin.Plugin;

import me.drkmatr1984.customevents.interactEvents.PlayerInteractEntityBaseClickEvent;
import me.drkmatr1984.customevents.interactEvents.PlayerInteractEntityCrouchLeftClickEvent;
import me.drkmatr1984.customevents.interactEvents.PlayerInteractEntityCrouchRightClickEvent;
import me.drkmatr1984.customevents.interactEvents.PlayerInteractEntityLeftClickEvent;
import me.drkmatr1984.customevents.interactEvents.PlayerInteractEntityRightClickEvent;

public class EntityInteractListener implements Listener
{
	private final Plugin plugin;
	
	public EntityInteractListener(Plugin plugin){
		this.plugin = plugin;
	}
	
	@EventHandler(priority=EventPriority.LOW, ignoreCancelled=true)
	public void onPlayerLeftClickEntity(EntityDamageByEntityEvent event) {
		if(event.getDamager() instanceof Player)
		{		
			if(event.getEntity() instanceof LivingEntity) {
				Player player = (Player) event.getDamager();
				PlayerInteractEntityBaseClickEvent clickEvent;
				if(player.isSneaking()) {
					clickEvent = null;
					clickEvent = new PlayerInteractEntityCrouchLeftClickEvent(plugin, player, event.getEntity(), false);
					Bukkit.getServer().getPluginManager().callEvent(clickEvent);
					if(clickEvent.isCancelled()) {
						event.setCancelled(true);
						return;
					}
					if(((PlayerInteractEntityCrouchLeftClickEvent) clickEvent).getDamageCancelled()) {
						event.setDamage(0.0D);
	  				  	event.setCancelled(true);
					}
				}else {
					clickEvent = new PlayerInteractEntityLeftClickEvent(plugin, player, event.getEntity(), false);
					Bukkit.getServer().getPluginManager().callEvent(clickEvent);
					if(clickEvent.isCancelled()) {
						event.setCancelled(true);
						return;
					}
					if(((PlayerInteractEntityLeftClickEvent) clickEvent).getDamageCancelled()) {
						event.setDamage(0.0D);
	  				  	event.setCancelled(true);
					}
				}							
			}
		}
	}
	
	@EventHandler(priority=EventPriority.LOW, ignoreCancelled=true)
	public void onPlayerRightClickEntity(PlayerInteractEntityEvent event){
		if(event.getHand().equals(EquipmentSlot.HAND)) {
			if(event.getRightClicked() instanceof LivingEntity) {
				PlayerInteractEntityBaseClickEvent clickEvent;
				Player player = event.getPlayer();
				if(player.isSneaking()) {
					clickEvent = new PlayerInteractEntityCrouchRightClickEvent(plugin, event.getPlayer(), event.getRightClicked());
					Bukkit.getServer().getPluginManager().callEvent(clickEvent);
					if(clickEvent.isCancelled()) {
						event.setCancelled(true);
					}
				}else {
					clickEvent = new PlayerInteractEntityRightClickEvent(plugin, event.getPlayer(), event.getRightClicked());
					Bukkit.getServer().getPluginManager().callEvent(clickEvent);
					if(clickEvent.isCancelled()) {
						event.setCancelled(true);
					}
				}				
			}
		}	
	}
}