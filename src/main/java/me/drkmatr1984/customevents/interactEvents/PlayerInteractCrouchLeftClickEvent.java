package me.drkmatr1984.customevents.interactEvents;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class PlayerInteractCrouchLeftClickEvent extends PlayerInteractBaseClickEvent{
	
	public PlayerInteractCrouchLeftClickEvent(Plugin plugin, Player player, Block clickedBlock, BlockFace blockFace, ItemStack itemStack, Material material, Action action){
		super(plugin, player, clickedBlock, blockFace, itemStack, material, action);
	}
}