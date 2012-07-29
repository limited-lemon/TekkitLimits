package com.theminerscene.tekkitlimits;

import java.util.logging.Logger;
import org.bukkit.Server;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class tekkitlimits extends JavaPlugin
{
  Logger log = Logger.getLogger("Minecraft");

  public void onDisable() { this.log.info("Disabled TekkitLimits!"); }

  public void onEnable()
  {	this.log.info("TekkitLimits by TMS-Dev Enabled!");
    getServer().getPluginManager().registerEvents(
      new tekkitlimits_Listener(), this);
  }
}