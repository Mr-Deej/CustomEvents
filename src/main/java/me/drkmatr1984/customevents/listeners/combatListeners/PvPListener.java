package me.drkmatr1984.customevents.listeners.combatListeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import me.drkmatr1984.customevents.pvpEvents.PlayerDamageByPlayerEvent;

public class PvPListener implements Listener{
	
	@EventHandler(priority=EventPriority.LOWEST, ignoreCancelled=true)
	public void onPvPDamage(EntityDamageByEntityEvent event)
	{
		if(event.getEntity() instanceof Player) {
			if(event.getDamager() instanceof Player) {
				PlayerDamageByPlayerEvent pvpEvent = new PlayerDamageByPlayerEvent(event);
				pvpEvent.callEvent();
				if(pvpEvent.isCancelled()) {
					event.setDamage(0.0);
					event.setCancelled(true);
				}
			}
		}
	}
}