package me.notaeris.cobra.listener;

import me.notaeris.cobra.CobraPlugin;
import me.notaeris.cobra.command.FreezeCommand;
import me.notaeris.cobra.util.CC;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class FreezeListener implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if(FreezeCommand.frozen.contains(player)) {
            event.setTo(event.getFrom());
            CobraPlugin.get().getConfig().getStringList("command.freeze.message").forEach(string -> player.sendMessage(CC.translate(string)));
        }
    }

    @EventHandler
    public void onDropItem(PlayerDropItemEvent event) {
        if(FreezeCommand.frozen.contains(event.getPlayer())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPickupItem(PlayerPickupItemEvent event) {
        if(FreezeCommand.frozen.contains(event.getPlayer())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();

        if(FreezeCommand.frozen.contains(player)) {
            event.setCancelled(true);
            CobraPlugin.get().getConfig().getStringList("command.freeze.message").forEach(string -> player.sendMessage(CC.translate(string)));
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();

        if(FreezeCommand.frozen.contains(player)) {
            event.setCancelled(true);
            CobraPlugin.get().getConfig().getStringList("command.freeze.message").forEach(string -> player.sendMessage(CC.translate(string)));
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        Entity entity = event.getEntity();

        if(entity instanceof Player && FreezeCommand.frozen.contains(entity)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onDamageByEntity(EntityDamageByEntityEvent event) {
        if(event.getDamager() instanceof Player) {
            Entity entity = event.getEntity();
            Player damager = (Player) event.getDamager();

            if(FreezeCommand.frozen.contains(entity)) {
                event.setCancelled(true);
                damager.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.freeze.target.is_frozen")));
            }
        }
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        Player player = (Player) event.getEntity();

        if(FreezeCommand.frozen.contains(player)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if(FreezeCommand.frozen.contains(player)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        if(FreezeCommand.frozen.contains(player)) {
            CobraPlugin.get().getConfig().getStringList("command.freeze.quit_message").forEach(string -> Bukkit.getOnlinePlayers().forEach(target -> {
                if(target.hasPermission("cobra.staff")) {
                    target.sendMessage(CC.translate(string
                            .replace("%player%", player.getName())));
                }
            }));
        }
    }
}
