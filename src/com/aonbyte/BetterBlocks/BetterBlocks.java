package com.aonbyte.BetterBlocks;

import java.util.logging.Logger;

import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;

public class BetterBlocks extends JavaPlugin {
	
	public static BetterBlocks plugin;
	public Configuration config;
	public final Logger logger = Logger.getLogger("Minecraft");
	public final ServerBlockListener blockListener = new ServerBlockListener(this);
	
	public void onEnable() {
		//Enabled message
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + " version "+ pdfFile.getVersion() +" is now enabled!");
		
		//Register listeners to appropiate class
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Event.Type.BLOCK_BREAK, this.blockListener, Event.Priority.Normal, this);
		
		//Set Config Defaults
		config = getConfiguration();
		config.load();
		ConfigVariables.breakStairs = config.getBoolean("Stairs", true);
		ConfigVariables.breakWebs = config.getBoolean("Webs", true);
		ConfigVariables.breakBookshelves = config.getBoolean("Bookshelves", true);
		config.save();
	}
	
	public void onDisable() {
		//Disabled message
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + " version " + pdfFile.getVersion() + " is now disabled!");
	}



}
