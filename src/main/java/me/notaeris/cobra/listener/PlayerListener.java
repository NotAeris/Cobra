package me.notaeris.cobra.listener;

import me.notaeris.cobra.CobraPlugin;
import me.notaeris.cobra.profile.Profile;
import me.notaeris.cobra.util.CC;
import me.notaeris.cobra.util.PlayerUtil;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.setJoinMessage(null);
        Player player = event.getPlayer();
        Profile profile = new Profile(player.getUniqueId());

        if(profile.getPlayer().hasPermission("cobra.staff")) {
            CobraPlugin.get().getConfig().getStringList("staff.connect").forEach(string -> PlayerUtil.getOnlinePlayers().forEach(target -> {
                if(target.hasPermission("cobra.staff")) {
                    target.sendMessage(CC.translate(string
                            .replace("%server%", CobraPlugin.get().getConfig().getString("server_name")))
                            .replace("%player%", player.getName()));
                }
            }));
        }
        if(CC.translate(CobraPlugin.get().getConfig().getString("command.staffmode.item.teleporter.name")).equalsIgnoreCase(player.getInventory().getItem(0).getItemMeta().getDisplayName())
                || CC.translate(CobraPlugin.get().getConfig().getString("command.staffmode.item.inspector.name")).equalsIgnoreCase(player.getInventory().getItem(1).getItemMeta().getDisplayName())
                || CC.translate(CobraPlugin.get().getConfig().getString("command.staffmode.item.vanish.name")).equalsIgnoreCase(player.getInventory().getItem(8).getItemMeta().getDisplayName())) {
            player.getInventory().clear();
            player.setGameMode(GameMode.SURVIVAL);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        event.setQuitMessage(null);
        Profile profile = new Profile(event.getPlayer().getUniqueId());

        if(profile.getPlayer().hasPermission("cobra.staff")) {
            CobraPlugin.get().getConfig().getStringList("staff.disconnect").forEach(string -> PlayerUtil.getOnlinePlayers().forEach(target -> {
                if(target.hasPermission("cobra.staff")) {
                    target.sendMessage(CC.translate(string
                            .replace("%server%", CobraPlugin.get().getConfig().getString("server_name")))
                            .replace("%player%", profile.getPlayer().getName()));
                }
            }));
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if(CC.translate(CobraPlugin.get().getConfig().getString("invsee.name")).equals(event.getInventory().getName())) {
            event.setCancelled(true);
        }
    }
}
