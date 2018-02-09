package me.drkmatr1984.customevents.moveEvents;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public abstract class BaseMoveEvent extends Event implements Cancellable{
	private static final HandlerList handlerList = new HandlerList();
	private boolean cancelled = false;
	
	@Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList(){
        return handlerList;
    }
    
    @Override
	public boolean isCancelled() {
		return this.cancelled;
	}

	@Override
	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;		
	}
}