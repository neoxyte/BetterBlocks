package com.aonbyte.BetterBlocks;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.inventory.ItemStack;

public class ServerBlockListener extends BlockListener {
	public static BetterBlocks plugin;
	
	public ServerBlockListener (BetterBlocks instance) {
		plugin = instance; 
	}
	
	public void onBlockBreak(BlockBreakEvent event) {
		Block brokenBlock = event.getBlock();
		Location spot = brokenBlock.getLocation();
		Player player = event.getPlayer();
		ItemStack playerTool = player.getItemInHand();
		Material tool = playerTool.getType();
		
		//BookShelf
		if(ConfigVariables.breakBookshelves) {
			if (brokenBlock.getType().equals(Material.BOOKSHELF) && isHatchet(tool)){
				dropBlock(brokenBlock, spot);
			}
		}
		//Spiderweb
		if(ConfigVariables.breakWebs) {
			if (brokenBlock.getType().equals(Material.WEB) && isSword(tool)) {
				dropBlock(brokenBlock, spot);
			}
		}
		//Stairs
		if(ConfigVariables.breakStairs) {
			//Wood
			if (brokenBlock.getType().equals(Material.WOOD_STAIRS) && isHatchet(tool)) {
				dropBlock(brokenBlock, spot);
			}
			//Cobble
			if (brokenBlock.getType().equals(Material.COBBLESTONE_STAIRS) && isPickaxe(tool)) {
				dropBlock(brokenBlock, spot);
			}
			//Brick
			if (brokenBlock.getType().equals(Material.BRICK_STAIRS) && isPickaxe(tool)) {
				dropBlock(brokenBlock, spot);
			}
			//Stone
			if (brokenBlock.getType().equals(Material.SMOOTH_STAIRS) && isPickaxe(tool)) {
				dropBlock(brokenBlock, spot);
			}
		}
	}
	
	public void dropBlock(Block block, Location spot) {
		World world = block.getWorld();
		Material blockType = block.getType();
		ItemStack blockStack = new ItemStack(blockType, 1);
		block.setType(Material.AIR);
		world.dropItemNaturally(spot, blockStack);
		
	}
	
	public boolean isHatchet(Material tool) {
		switch (tool) {
			case WOOD_AXE: return true;
			case STONE_AXE: return true;
			case IRON_AXE: return true;
			case GOLD_AXE: return true;
			case DIAMOND_AXE: return true;
			default: return false;
		}
	}
	
	public boolean isPickaxe (Material tool) {
		switch (tool) {
			case WOOD_PICKAXE: return true;
			case STONE_PICKAXE: return true;
			case IRON_PICKAXE: return true;
			case GOLD_PICKAXE: return true;
			case DIAMOND_PICKAXE: return true;
			default: return false;
		}
	}
	
	public boolean isSword (Material tool) {
		switch (tool) {
			case WOOD_SWORD: return true;
			case STONE_SWORD: return true;
			case IRON_SWORD: return true;
			case GOLD_SWORD: return true;
			case DIAMOND_SWORD: return true;
			default: return false;
		}
	}
}
