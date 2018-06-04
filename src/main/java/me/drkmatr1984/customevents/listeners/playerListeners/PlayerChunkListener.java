package me.drkmatr1984.customevents.listeners.playerListeners;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import me.drkmatr1984.customevents.moveEvents.PlayerMovedChunkEvent;
import me.drkmatr1984.customevents.moveEvents.SignificantPlayerMoveEvent;

public class PlayerChunkListener implements Listener
{
	
	public Plugin plugin;
	
	public PlayerChunkListener(Plugin plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler(priority=EventPriority.LOW, ignoreCancelled=true)
	public void onPlayerMove(SignificantPlayerMoveEvent event) {
		Location to = event.getTo();
        Location from = event.getFrom();
        if (to.getBlockX() == from.getBlockX() && to.getBlockZ() == from.getBlockZ()) {
            return;
        }
        Chunk toChunk = to.getChunk();
        Chunk fromChunk = from.getChunk();
        if (toChunk.getX() == fromChunk.getX() && toChunk.getZ() == fromChunk.getZ()) {
            return;
        }
        PlayerMovedChunkEvent chunkEvent = new PlayerMovedChunkEvent(event.getPlayer(), from, to, fromChunk, toChunk);
        Bukkit.getServer().getPluginManager().callEvent(chunkEvent);
        if(chunkEvent.isCancelled()) {
        	event.setCancelled(true);
        }
	}
	
}