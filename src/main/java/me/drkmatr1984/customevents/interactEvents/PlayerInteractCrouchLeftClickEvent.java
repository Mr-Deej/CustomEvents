package me.drkmatr1984.customevents.interactEvents;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class PlayerInteractCrouchLeftClickEvent extends PlayerInteractBaseClickEvent{
	
	private static final HandlerList handlerList = new HandlerList();
	private boolean cancelled = false;
	private Plugin plugin;
	private Player player;
	private Block clickedBlock;
	private BlockFace blockFace;
	private ItemStack itemStack;
	private Material material;
	private Action action;
	
	public PlayerInteractCrouchLeftClickEvent(Plugin plugin, Player player, Block clickedBlock, BlockFace blockFace, ItemStack itemStack, Material material, boolean cancelled, Action action){
		this.plugin = plugin;
		this.player = player;
		this.clickedBlock = clickedBlock;
		this.blockFace = blockFace;
		this.itemStack = itemStack;
		this.material = material;
		this.cancelled = cancelled;
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
		if(stack == null || stack.getType() == Material.AIR){
			return false;
		}
		return true;
	}
	
	public boolean hasItemInOffHand(){
		ItemStack stack = this.player.getInventory().getItemInOffHand();
		if(stack == null || stack.getType() == Material.AIR){
			return false;
		}
		return true;
	}
	
	public Location getClickedBlockLocation(){
		return this.clickedBlock.getLocation();
	}
	
	public Action getAction(){
		return this.action;
	}
}