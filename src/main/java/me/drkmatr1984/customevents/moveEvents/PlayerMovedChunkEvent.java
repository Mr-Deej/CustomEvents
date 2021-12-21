package me.drkmatr1984.customevents.moveEvents;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class PlayerMovedChunkEvent extends BaseMoveEvent{
	
	private final Player player;
	private final Location from;
	private final Location to;
	private final Chunk toChunk;
    private final Chunk fromChunk;
	
	public PlayerMovedChunkEvent(Player player, Location from, Location to, Chunk toChunk, Chunk fromChunk){
		this.player = player;
		this.from = from;
		this.to = to;
		this.toChunk = toChunk;
		this.fromChunk = fromChunk;
	}
	
	public Player getPlayer(){
		return this.player;
	}
	
	public Location getFrom(){
		return this.from;
	}
	
	public Location getTo(){
		return this.to;
	}
	
	public Block getBlockFrom(){
		return this.from.getBlock();
	}
	
	public Block getBlockTo(){
		return this.to.getBlock();
	}
	
	public Chunk getFromChunk() {
		return this.fromChunk;
	}
	
	public Chunk getToChunk() {
		return this.toChunk;
	}
	
}