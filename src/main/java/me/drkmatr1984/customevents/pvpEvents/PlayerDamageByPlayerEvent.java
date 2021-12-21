package me.drkmatr1984.customevents.pvpEvents;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

@SuppressWarnings("deprecation")
public class PlayerDamageByPlayerEvent extends EntityDamageByEntityEvent implements Cancellable
{
	private static final HandlerList handlerList = new HandlerList();
	private boolean cancelled = false;
	private double damage = 0.0;
	private DamageCause cause = null;
	private Player damaged = null;
	private Player damaging = null;
	
	private final Map<DamageModifier, Double> damageMap;
	
	public PlayerDamageByPlayerEvent(EntityDamageByEntityEvent event) {
		super(event.getDamager(), event.getEntity(), event.getCause(), event.getDamage());
		damageMap = new HashMap<DamageModifier, Double>();
		for(DamageModifier dM : DamageModifier.values()) {
			try {
				damageMap.put(dM, event.getDamage(dM));
			}catch(IllegalArgumentException e) {
				damageMap.put(dM, 0.0);
			} 			
		}
		this.damage = event.getDamage();
		this.cause = event.getCause();
		this.damaged = (Player) event.getEntity();
		this.damaging = (Player) event.getDamager();
	}
	
	public Player getDamagedPlayer() {
		return this.damaged;
	}
	
	public Player getDamagingPlayer() {
		return this.damaging;
	}
	
	public double getDamage(DamageModifier damageModifier) {
		return this.damageMap.get(damageModifier);
	}
	
	public double getDamage() {
		return this.damage;
	}
	
	public double getOriginalDamage(DamageModifier damageModifier) {
		return this.damageMap.get(damageModifier);
	}
	
	public DamageCause getCause() {
		return this.cause;
	}
	
	public void setDamage(double damage) {
		this.damage = damage;
	}
	
	public void setDamage(DamageModifier damageModifier, double damage) {
		this.damageMap.put(damageModifier, damage);
	}
	
	@Override
	public boolean isCancelled() {
		return this.cancelled;
	}

	@Override
	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;		
	}

	@Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList(){
        return handlerList;
    }
	
}