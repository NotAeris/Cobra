package me.notaeris.cobra.listener;

import me.notaeris.cobra.CobraPlugin;
import me.notaeris.cobra.command.StaffModeCommand;
import me.notaeris.cobra.command.VanishCommand;
import me.notaeris.cobra.util.CC;
import me.notaeris.cobra.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

public class StaffModeListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if(event.getItem() != null) {
            if(CC.translate(CobraPlugin.get().getConfig().getString("command.staffmode.item.vanish.name")).equalsIgnoreCase(player.getItemInHand().getItemMeta().getDisplayName())) {
                if(VanishCommand.vanish.contains(player)) {
                    VanishCommand.setVanished(player, false);
                    ItemStack vanish = new ItemBuilder(Material.getMaterial(CobraPlugin.get().getConfig().getString("command.staffmode.item.vanish.material")), CobraPlugin.get().getConfig().getInt("command.staffmode.item.vanish.amount"), (short) CobraPlugin.get().getConfig().getInt("command.staffmode.item.vanish.value"))
                            .setName(CC.translate(CobraPlugin.get().getConfig().getString("command.staffmode.item.vanish.name")))
                            .create();
                    player.getInventory().setItem(CobraPlugin.get().getConfig().getInt("command.staffmode.item.vanish.slot"), vanish);
                    player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.vanish.disabled")));
                } else {
                    VanishCommand.setVanished(player, true);
                    ItemStack vanish = new ItemBuilder(Material.getMaterial(CobraPlugin.get().getConfig().getString("command.staffmode.item.vanish.material")), CobraPlugin.get().getConfig().getInt("command.staffmode.item.vanish.amount"), (short) CobraPlugin.get().getConfig().getInt("command.staffmode.item.vanish.value2"))
                            .setName(CC.translate(CobraPlugin.get().getConfig().getString("command.staffmode.item.vanish.name")))
                            .create();
                    player.getInventory().setItem(CobraPlugin.get().getConfig().getInt("command.staffmode.item.vanish.slot"), vanish);
                    player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.vanish.enabled")));
                }
            }
        }
    }

    @EventHandler
    public void onInteractEntity(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        Player target = (Player) event.getRightClicked();

        player.performCommand("invsee " + target.getName());
        player.updateInventory();
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        Player player = (Player) event.getDamager();

        if(StaffModeCommand.staffmode.contains(player)) {
            event.setCancelled(true);
            player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("staffmode.you_cannot_do_this")));
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();

        if(StaffModeCommand.staffmode.contains(player)) {
            event.setCancelled(true);
            player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("staffmode.you_cannot_do_this")));
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();

        if(StaffModeCommand.staffmode.contains(player)) {
            event.setCancelled(true);
            player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("staffmode.you_cannot_do_this")));
        }
    }

    @EventHandler
    public void onPickupItem(PlayerPickupItemEvent event) {
        Player player = event.getPlayer();

        if(StaffModeCommand.staffmode.contains(player)) {
            event.setCancelled(true);
            player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("staffmode.you_cannot_do_this")));
        }
    }

    @EventHandler
    public void onDropItem(PlayerDropItemEvent event) {
        Player player = event.getPlayer();

        if(StaffModeCommand.staffmode.contains(player)) {
            event.setCancelled(true);
            player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("staffmode.you_cannot_do_this")));
        }
    }
}
