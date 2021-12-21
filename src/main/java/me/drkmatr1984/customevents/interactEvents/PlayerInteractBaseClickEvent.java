package me.drkmatr1984.customevents.interactEvents;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class PlayerInteractBaseClickEvent extends Event implements Cancellable{

	private static final HandlerList handlerList = new HandlerList();
	private boolean cancelled = false;
	private final Plugin plugin;
	private final Player player;
	private final Block clickedBlock;
	private final BlockFace blockFace;
	private final ItemStack itemStack;
	private final Material material;
	private final Action action;
	
	public PlayerInteractBaseClickEvent(Plugin plugin, Player player, Block clickedBlock, BlockFace blockFace, ItemStack itemStack, Material material, Action action) {
		this.plugin = plugin;
		this.player = player;
		this.clickedBlock = clickedBlock;
		this.blockFace = blockFace;
		this.itemStack = itemStack;
		this.material = material;
		this.action = action;
	}
	
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

	public Plugin getPlugin() {
		return this.plugin;
	}
	
	public Player getPlayer(){
		return this.player;
	}
	
	public Block getClickedBlock(){
		return this.clickedBlock;
	}
	
	public BlockFace getBlockFace(){
		return this.blockFace;
	}
	
	public ItemStack getItem(){
		return this.itemStack;
	}
	
	public Material getType(){
		return this.material;
	}
	
	public ItemStack getItemInMainHand(){
		ItemStack stack = this.player.getInventory().getItemInMainHand();
		if(!hasItemInMainHand()){
			return new ItemStack(Material.AIR, 1);
		}
		return stack;
	}
	
	public ItemStack getItemInOffHand(){
		ItemStack stack = this.player.getInventory().getItemInOffHand();
		if(!hasItemInOffHand()){
			return new ItemStack(Material.AIR, 1);
		}
		return stack;
	}
	
	public boolean hasItemInMainHand(){
		ItemStack stack = this.player.getInventory().getItemInMainHand();
		return stack.getType() != Material.AIR;
	}
	
	public boolean hasItemInOffHand(){
		ItemStack stack = this.player.getInventory().getItemInOffHand();
		return stack.getType() != Material.AIR;
	}
	
	public Location getClickedBlockLocation(){
		return this.clickedBlock.getLocation();
	}
	
	public Action getAction(){
		return this.action;
	}
}