
package com.theminerscene.tekkitlimits;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.Recipe;

public class tekkitlimits_Listener
  implements Listener
{
  static String prefix = ChatColor.GOLD + "[" + ChatColor.GREEN + "Tekkit Limits" + ChatColor.GOLD + "]" + ChatColor.BLACK;
  tekkitlimits plugin;

  @EventHandler(priority=EventPriority.NORMAL)
  public void onBlockPlace(BlockPlaceEvent event)
  {
    Player p = event.getPlayer();
    Block b = event.getBlock();
    int id = b.getTypeId();
    byte subid = b.getData();
    if (p.hasPermission("tekkitlimits.debug")) {
    //if(event.getPlayer().isOp()){
      p.sendMessage(prefix + ChatColor.RED + "DEBUG: Item id: " + id + " SubID: " + subid);
    }

    if ((p.hasPermission("tekkitlimits.place." + id + "." + subid)) && (!p.hasPermission("tekkitlimits.bypass"))) {
      p.sendMessage(prefix + ChatColor.RED + "This item is banned from TMS!");
      p.setItemInHand(null);
      event.setCancelled(true);
      b.setType(Material.AIR);
    }

    if (!p.hasPermission("tekkitlimits.ignore.gamemode")) {
      if (p.hasPermission("tekkitlimits.forcegamemode.survival"))
      {
        if (p.getGameMode() == GameMode.CREATIVE) {
          p.setGameMode(GameMode.SURVIVAL);
          p.sendMessage(prefix + ChatColor.RED + "Your gamemode has been changed to survival!");
        }
      }
      if ((p.hasPermission("tekkitlimits.forcegamemode.creative")) && (p.getGameMode() == GameMode.SURVIVAL)) {
        p.setGameMode(GameMode.CREATIVE);
        p.sendMessage(prefix + ChatColor.RED + "Your gamemode has been changed to creative!");
      }
    }
  }

  @EventHandler(priority=EventPriority.NORMAL)
  public void onItemdrop(PlayerDropItemEvent event) {
    Player p = event.getPlayer();
    ItemStack stack = event.getItemDrop().getItemStack();
    int id = stack.getTypeId();
    short subid = stack.getDurability();

    if ((p.hasPermission("tekkitlimits.drop." + id + "." + subid)) && (!p.hasPermission("tekkitlimits.bypass"))) {
      p.sendMessage(prefix + ChatColor.RED + "This item can not be dropped!");
      p.setItemInHand(null);
      event.setCancelled(true);
    }
  }

  @EventHandler(priority=EventPriority.NORMAL)
  public void onuseitem(PlayerInteractEvent event) {
    Player p = event.getPlayer();
    Block b = event.getClickedBlock();
    ItemStack stack = p.getItemInHand();

    if (stack != null) {
      int hand_id = stack.getTypeId();
      short hand_subid = stack.getDurability();

      if (p.hasPermission("tekkitlimits.debug")) {
        p.sendMessage(prefix + ChatColor.RED + "DEBUG: Item id: " + hand_id + " SubID: " + hand_subid);
      }

      if ((p.hasPermission("tekkitlimits.use." + hand_id + "." + hand_subid)) && (!p.hasPermission("tekkitlimits.bypass"))) {
        p.sendMessage(prefix + ChatColor.RED + "This item can not be used!");
        p.setItemInHand(null);
        event.setCancelled(true);
      }
    }

    if (b != null) {
      int id = b.getTypeId();
      short subid = b.getData();

      if (p.hasPermission("tekkitlimits.debug")) {
        p.sendMessage(prefix + ChatColor.RED + "DEBUG: Item id: " + id + " SubID: " + subid);
      }

      if ((p.hasPermission("tekkitlimits.interact." + id + "." + subid)) && (!p.hasPermission("tekkitlimits.bypass"))) {
        p.sendMessage(prefix + ChatColor.RED + "This item can not be interacted with!");
        p.setItemInHand(null);
        event.setCancelled(true);
        b.setType(Material.AIR);
      }
    }

    if (!p.hasPermission("tekkitlimits.ignore.gamemode")) {
      if (p.hasPermission("tekkitlimits.forcegamemode.survival"))
      {
        if (p.getGameMode() == GameMode.CREATIVE) {
          p.setGameMode(GameMode.SURVIVAL);
          p.sendMessage(prefix + ChatColor.RED + "Your gamemode has been changed to survival!");
        }
      }
      if ((p.hasPermission("tekkitlimits.forcegamemode.creative")) && (p.getGameMode() == GameMode.SURVIVAL)) {
        p.setGameMode(GameMode.CREATIVE);
        p.sendMessage(prefix + ChatColor.RED + "Your gamemode has been changed to creative!");
      }
    }
  }

  @EventHandler(priority=EventPriority.NORMAL)
  public void inventoryClick(InventoryClickEvent event) {
    Player p = (Player)event.getWhoClicked();
    int id = event.getCursor().getTypeId();
    short subid = event.getCursor().getDurability();

    if (p.hasPermission("tekkitlimits.debug")) {
      p.sendMessage(prefix + ChatColor.RED + "DEBUG: Item id: " + id + " SubID: " + subid);
    }

    if ((p.hasPermission("tekkitlimits.inventory." + id + "." + subid)) && (!p.hasPermission("tekkitlimits.bypass"))) {
      p.sendMessage(prefix + ChatColor.RED + "This item is invintory banned!");
      if (event.getSlot() == event.getWhoClicked().getInventory().getHeldItemSlot())
      {
        p.setItemInHand(null);
      }
      event.setCancelled(true);
    }

    if (!p.hasPermission("tekkitlimits.ignore.gamemode")) {
      if (p.hasPermission("tekkitlimits.forcegamemode.survival"))
      {
        if (p.getGameMode() == GameMode.CREATIVE) {
          p.setGameMode(GameMode.SURVIVAL);
          p.sendMessage(prefix + ChatColor.RED + "Your gamemode has been changed to survival!");
        }
      }
      if ((p.hasPermission("tekkitlimits.forcegamemode.creative")) && (p.getGameMode() == GameMode.SURVIVAL)) {
        p.setGameMode(GameMode.CREATIVE);
        p.sendMessage(prefix + ChatColor.RED + "Your gamemode has been changed to creative!");
      }
    }
  }

  @EventHandler(priority=EventPriority.NORMAL)
  public void playerPickupItem(PlayerPickupItemEvent event) {
    Player p = event.getPlayer();
    ItemStack stack = event.getItem().getItemStack();
    int id = stack.getTypeId();
    short subid = stack.getDurability();

    if (p.hasPermission("tekkitlimits.debug")) {
      p.sendMessage(prefix + ChatColor.RED + "DEBUG: Item id: " + id + " SubID: " + subid);
    }

    if ((p.hasPermission("tekkitlimits.pickup." + id + "." + subid)) && (!p.hasPermission("tekkitlimits.bypass"))) {
      p.sendMessage(prefix + ChatColor.RED + "This item can not be picked up!");
      event.setCancelled(true);
    }

    if (!p.hasPermission("tekkitlimits.ignore.gamemode")) {
      if (p.hasPermission("tekkitlimits.forcegamemode.survival"))
      {
        if (p.getGameMode() == GameMode.CREATIVE) {
          p.setGameMode(GameMode.SURVIVAL);
          p.sendMessage(prefix + ChatColor.RED + "Your gamemode has been changed to survival!");
        }
      }
      if ((p.hasPermission("tekkitlimits.forcegamemode.creative")) && (p.getGameMode() == GameMode.SURVIVAL)) {
        p.setGameMode(GameMode.CREATIVE);
        p.sendMessage(prefix + ChatColor.RED + "Your gamemode has been changed to creative!");
      }
    }
  }

  @EventHandler(priority=EventPriority.NORMAL)
  public void craft_item_event(PrepareItemCraftEvent event) {
    ItemStack stack = event.getRecipe().getResult();
    ItemStack output = new ItemStack(0, 0);
    int id = stack.getTypeId();
    short subid = stack.getDurability();
    for (HumanEntity he : event.getViewers()) {
      Player p = (Player)he;

      if (p.hasPermission("tekkitlimits.debug")) {
        p.sendMessage(prefix + ChatColor.RED + "DEBUG: Item id: " + id + " SubID: " + subid);
      }

      if ((p.hasPermission("tekkitlimits.craft." + id + "." + subid)) && (!p.hasPermission("tekkitlimits.bypass"))) {
        p.sendMessage(prefix + ChatColor.RED + "This item can not be crafted!");
        event.getInventory().setItem(0, output);
      }
    }
  }
}