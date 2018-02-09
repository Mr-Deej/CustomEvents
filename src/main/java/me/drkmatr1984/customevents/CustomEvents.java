package me.drkmatr1984.customevents;

import org.bukkit.plugin.Plugin;

import me.drkmatr1984.customevents.listeners.blockListeners.BlockInteractListener;
import me.drkmatr1984.customevents.listeners.combatListeners.PvPListener;
import me.drkmatr1984.customevents.tasks.PlayerMoveEventTask;

public class CustomEvents{
	
	private Plugin plugin;
	private boolean blockIntrctLstnersEnbled;
	private boolean useSignificantMoveEvent;
	private boolean usePvPEvents;
	
	
	public CustomEvents(Plugin plugin, boolean interactListenersEnabled, boolean significantMoveEventEnabled, boolean usePvPEvents){
		this.plugin = plugin;
		this.blockIntrctLstnersEnbled = interactListenersEnabled;
		this.useSignificantMoveEvent = significantMoveEventEnabled;
		this.usePvPEvents = usePvPEvents;
	}
	
	public void initializeLib()
	{
		if(blockIntrctLstnersEnbled){
			BlockInteractListener listener = new BlockInteractListener(this.plugin);
			this.plugin.getServer().getPluginManager().registerEvents(listener, this.plugin);
		}
		if(useSignificantMoveEvent){
			PlayerMoveEventTask task = new PlayerMoveEventTask();
			task.runTaskTimer(plugin, 20, 3);
		}
		if(usePvPEvents){
			PvPListener listener = new PvPListener();
			this.plugin.getServer().getPluginManager().registerEvents(listener, this.plugin);
		}
	}
}