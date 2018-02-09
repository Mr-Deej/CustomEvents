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
	private EntityDamageByEntityEvent event;
	private boolean cancelled = false;
	private double damage = 0.0;
	private DamageCause cause = null;
	private Player damaged = null;
	private Player damaging = null;
	
	private Map<DamageModifier, Double> damageMap;
	
	public PlayerDamageByPlayerEvent(EntityDamageByEntityEvent event) {
		super((Player) event.getDamager(), event.getEntity(), event.getCause(), event.getDamage());
		this.event = event;
		damageMap = new HashMap<DamageModifier, Double>();
		for(DamageModifier dM : DamageModifier.values()) {
			try {
				damageMap.put(dM, this.event.getDamage(dM));
			}catch(IllegalArgumentException e) {
				damageMap.put(dM, 0.0);
			} 			
		}
		this.damage = this.event.getDamage();
		this.cause = this.event.getCause();
		this.damaged = (Player) this.event.getEntity();
		this.damaging = (Player) this.event.getDamager();
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