package me.drkmatr1984.customevents.listeners.blockListeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;

import me.drkmatr1984.customevents.interactEvents.PlayerInteractBaseClickEvent;
import me.drkmatr1984.customevents.interactEvents.PlayerInteractCrouchLeftClickEvent;
import me.drkmatr1984.customevents.interactEvents.PlayerInteractCrouchRightClickEvent;
import me.drkmatr1984.customevents.interactEvents.PlayerInteractLeftClickEvent;
import me.drkmatr1984.customevents.interactEvents.PlayerInteractRightClickEvent;
 
public class BlockInteractListener implements Listener {
	
	private final Plugin plugin;
	
	public BlockInteractListener(Plugin plugin){
		this.plugin = plugin;
	}
	
	@EventHandler(priority=EventPriority.LOW, ignoreCancelled=true)
    public void onPlayerInteract(PlayerInteractEvent event) {
    	Player player = event.getPlayer();
        PlayerInteractBaseClickEvent clickEvent;
        //MainHand Event Trigger
        if(event.getAction() == Action.LEFT_CLICK_BLOCK || event.getAction() == Action.LEFT_CLICK_AIR){     	
        	if(player.isSneaking()){
        		clickEvent = new PlayerInteractCrouchLeftClickEvent(this.plugin, event.getPlayer() ,event.getClickedBlock(), event.getBlockFace(), event.getItem(), event.getMaterial(), event.getAction());
            	Bukkit.getServer().getPluginManager().callEvent(clickEvent);
            	event.setCancelled(clickEvent.isCancelled());
        	}else{
        		clickEvent = new PlayerInteractLeftClickEvent(this.plugin, event.getPlayer() ,event.getClickedBlock(), event.getBlockFace(), event.getItem(), event.getMaterial(), event.getAction());
            	Bukkit.getServer().getPluginManager().callEvent(clickEvent);
            	event.setCancelled(clickEvent.isCancelled());
        	}
        	return;
        }
        //OffHand Event Trigger
        if(event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR){
        	if(player.isSneaking()){
        		clickEvent = new PlayerInteractCrouchRightClickEvent(this.plugin, event.getPlayer() ,event.getClickedBlock(), event.getBlockFace(), event.getItem(), event.getMaterial(), event.isCancelled(), event.getAction());
            	Bukkit.getServer().getPluginManager().callEvent(clickEvent);
            	event.setCancelled(clickEvent.isCancelled());
        	}else{
        		clickEvent = new PlayerInteractRightClickEvent(this.plugin, event.getPlayer() ,event.getClickedBlock(), event.getBlockFace(), event.getItem(), event.getMaterial(), event.isCancelled(), event.getAction());
            	Bukkit.getServer().getPluginManager().callEvent(clickEvent);
            	event.setCancelled(clickEvent.isCancelled());
        	}
		}
	}
}