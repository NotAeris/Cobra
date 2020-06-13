package me.notaeris.cobra.listener;

import me.notaeris.cobra.CobraPlugin;
import me.notaeris.cobra.profile.Profile;
import me.notaeris.cobra.util.CC;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Arrays;
import java.util.List;

public class PlayerListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.setJoinMessage(null);
        Player player = event.getPlayer();
        Profile profile = new Profile(player.getUniqueId());

        if(profile.getPlayer().hasPermission("cobra.staff")) {
            CobraPlugin.get().getConfig().getStringList("staff.connect").forEach(string -> Bukkit.getOnlinePlayers().forEach(target -> {
                if(target.hasPermission("cobra.staff")) {
                    target.sendMessage(CC.translate(string
                            .replace("%player%", profile.getPlayer().getName()))
                            .replace("%server%", CobraPlugin.get().getConfig().getString("server_name")));
                }
            }));
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        event.setQuitMessage(null);
        Profile profile = new Profile(event.getPlayer().getUniqueId());

        if(profile.getPlayer().hasPermission("cobra.staff")) {
            CobraPlugin.get().getConfig().getStringList("staff.disconnect").forEach(string -> Bukkit.getOnlinePlayers().forEach(target -> {
                if(target.hasPermission("cobra.staff")) {
                    target.sendMessage(CC.translate(string
                            .replace("%player%", profile.getPlayer().getName()))
                            .replace("%server%", CobraPlugin.get().getConfig().getString("server_name")));
                }
            }));
        }
    }

    @EventHandler
    public void onCommandProcess(PlayerCommandPreprocessEvent event) {
        for(String blockedCommand : CobraPlugin.get().getConfig().getStringList("filter.command.blocked_commands")) {
            if(event.getMessage().startsWith(blockedCommand)) {
                event.setCancelled(true);
                event.getPlayer().sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("filter.command.message")));
            }
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if(CC.translate(CobraPlugin.get().getConfig().getString("command.invsee.name")).equalsIgnoreCase(event.getInventory().getName())) {
            event.setCancelled(true);
        }
    }
}
