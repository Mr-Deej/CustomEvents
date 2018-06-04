package me.drkmatr1984.customevents;

import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import me.drkmatr1984.customevents.listeners.blockListeners.BlockInteractListener;
import me.drkmatr1984.customevents.listeners.combatListeners.PvPListener;
import me.drkmatr1984.customevents.listeners.entityListeners.EntityInteractListener;
import me.drkmatr1984.customevents.listeners.playerListeners.PlayerChunkListener;
import me.drkmatr1984.customevents.tasks.PlayerMoveEventTask;

public class CustomEvents{
	
	private Plugin plugin;
	private boolean blockIntrctLstnersEnbled;
	private boolean entityIntrctLstnersEnbled;
	private boolean useSignificantMoveEvent;
	private boolean usePlayerMovedChunkEvent;
	private boolean usePvPEvents;
	
	
	public CustomEvents(Plugin plugin, boolean interactListenersEnabled, boolean entityIntrctLstnersEnbled, boolean significantMoveEventEnabled, boolean playerMovedChunkEventEnabled, boolean usePvPEvents){
		this.plugin = plugin;
		this.blockIntrctLstnersEnbled = interactListenersEnabled;
		this.entityIntrctLstnersEnbled = entityIntrctLstnersEnbled;
		this.useSignificantMoveEvent = significantMoveEventEnabled;
		this.usePlayerMovedChunkEvent = playerMovedChunkEventEnabled;
		this.usePvPEvents = usePvPEvents;
	}
	
	public void initializeLib()
	{
		if(blockIntrctLstnersEnbled){
			BlockInteractListener listener = new BlockInteractListener(this.plugin);
			registerCustomEvent(listener, this.plugin);
		}
		if(entityIntrctLstnersEnbled) {
			EntityInteractListener listener = new EntityInteractListener(this.plugin);
			registerCustomEvent(listener, this.plugin);
		}
		// INFO: SignificantMoveEvent does not detect head movements/subtle mouse movements
		if(useSignificantMoveEvent){ //WARNING! May cause lag, but less than Bukkit built in 
			//PlayerMoveEvent
			PlayerMoveEventTask task = new PlayerMoveEventTask();
			task.runTaskTimer(plugin, 20, 3);
		}
		if(this.usePlayerMovedChunkEvent) { //WARNING! Will use the significantMoveEvent,
			//which may cause lag
			PlayerMoveEventTask task = new PlayerMoveEventTask();
			task.runTaskTimer(plugin, 20, 3);
			PlayerChunkListener listener = new PlayerChunkListener(plugin);
			registerCustomEvent(listener, this.plugin);
		}
		if(usePvPEvents){
			PvPListener listener = new PvPListener();
			registerCustomEvent(listener, this.plugin);
		}
	}
	
	private void registerCustomEvent(Listener listener, Plugin plugin) {
		plugin.getServer().getPluginManager().registerEvents(listener, plugin);
	}
}