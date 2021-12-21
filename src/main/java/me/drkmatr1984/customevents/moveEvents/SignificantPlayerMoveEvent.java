package me.drkmatr1984.customevents.moveEvents;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class SignificantPlayerMoveEvent extends BaseMoveEvent{
	
	private final Player player;
	private final Location from;
	private final Location to;
	
	public SignificantPlayerMoveEvent(Player player, Location from, Location to){
		this.player = player;
		this.from = from;
		this.to = to;
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
}