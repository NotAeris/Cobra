package me.notaeris.cobra.listener;

import me.notaeris.cobra.CobraPlugin;
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
import org.bukkit.inventory.ItemStack;

public class StaffModeListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if(event.getItem() != null) {
            if(CC.translate(CobraPlugin.get().getConfig().getString("staffmode.item.vanish.name")).equalsIgnoreCase(player.getItemInHand().getItemMeta().getDisplayName())) {
                if(CobraPlugin.get().getAPI().getVanish(player)) {
                    CobraPlugin.get().getAPI().setVanished(player, false);
                    ItemStack vanish = new ItemBuilder(Material.getMaterial(CobraPlugin.get().getConfig().getString("staffmode.item.vanish.material")), CobraPlugin.get().getConfig().getInt("staffmode.item.vanish.amount"), (short) CobraPlugin.get().getConfig().getInt("staffmode.item.vanish.value"))
                            .setName(CC.translate(CobraPlugin.get().getConfig().getString("staffmode.item.vanish.name")))
                            .create();
                    player.getInventory().setItem(CobraPlugin.get().getConfig().getInt("staffmode.item.vanish.slot"), vanish);
                    player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.vanish.disabled")));
                } else {
                    CobraPlugin.get().getAPI().setVanished(player, true);
                    ItemStack vanish = new ItemBuilder(Material.getMaterial(CobraPlugin.get().getConfig().getString("staffmode.item.vanish.material")), CobraPlugin.get().getConfig().getInt("staffmode.item.vanish.amount"), (short) CobraPlugin.get().getConfig().getInt("staffmode.item.vanish.value2"))
                            .setName(CC.translate(CobraPlugin.get().getConfig().getString("staffmode.item.vanish.name")))
                            .create();
                    player.getInventory().setItem(CobraPlugin.get().getConfig().getInt("staffmode.item.vanish.slot"), vanish);
                    player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.vanish.enabled")));
                }
            }
        }
    }

    @EventHandler
    public void onInteractEntity(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        Player target = (Player) event.getRightClicked();

        if(!(player.getInventory().getItem(CobraPlugin.get().getConfig().getInt("staffmode.item.inspector.slot")).getItemMeta().getDisplayName() == null)) {
            if(CC.translate(CobraPlugin.get().getConfig().getString("staffmode.item.inspector.name")).equalsIgnoreCase(player.getItemInHand().getItemMeta().getDisplayName())) {
                player.performCommand("invsee " + target.getName());
                player.updateInventory();
            }
        }
        if(!(player.getInventory().getItem(CobraPlugin.get().getConfig().getInt("staffmode.item.inspector.slot")).getItemMeta().getDisplayName() == null)) {
            if(CC.translate(CobraPlugin.get().getConfig().getString("staffmode.item.freezer.name")).equalsIgnoreCase(player.getItemInHand().getItemMeta().getDisplayName())) {
                player.performCommand("freeze " + target.getName());
            }
        }
    }

    @EventHandler
    public void onDamageByEntity(EntityDamageByEntityEvent event) {
        if(event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();

            if(CobraPlugin.get().getAPI().getStaffMode(player)) {
                event.setCancelled(true);
                player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("staffmode.you_cannot_do_this")));
            }
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();

        if(CobraPlugin.get().getAPI().getStaffMode(player)) {
            event.setCancelled(true);
            player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("staffmode.you_cannot_do_this")));
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();

        if(CobraPlugin.get().getAPI().getStaffMode(player)) {
            event.setCancelled(true);
            player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("staffmode.you_cannot_do_this")));
        }
    }

    @EventHandler
    public void onDropItem(PlayerDropItemEvent event) {
        Player player = event.getPlayer();

        if(CobraPlugin.get().getAPI().getStaffMode(player)) {
            event.setCancelled(true);
            player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("staffmode.you_cannot_do_this")));
        }
    }
}
