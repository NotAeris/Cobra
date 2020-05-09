package me.notaeris.cobra.listener;

import me.notaeris.cobra.CobraPlugin;
import me.notaeris.cobra.command.InvseeCommand;
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
                            .replace("%server%", CobraPlugin.get().getConfig().getString("server_name")))
                            .replace("%player%", player.getName()));
                }
            }));
        }
        if(player.getInventory().getItem(CobraPlugin.get().getConfig().getInt("command.staffmode.item.teleporter.slot")) == null
                || player.getInventory().getItem(CobraPlugin.get().getConfig().getInt("command.staffmode.item.inspector.slot")).getItemMeta().getDisplayName() == null
                || player.getInventory().getItem(CobraPlugin.get().getConfig().getInt("command.staffmode.item.freezer.slot")).getItemMeta().getDisplayName() == null
                || player.getInventory().getItem(CobraPlugin.get().getConfig().getInt("command.staffmode.item.vanish.slot")).getItemMeta().getDisplayName() == null) {
            return;
        } else {
            if(CC.translate(CobraPlugin.get().getConfig().getString("command.staffmode.item.teleporter.name")).equalsIgnoreCase(player.getInventory().getItem(CobraPlugin.get().getConfig().getInt("command.staffmode.item.teleporter.slot")).getItemMeta().getDisplayName())
                    || CC.translate(CobraPlugin.get().getConfig().getString("command.staffmode.item.inspector.name")).equalsIgnoreCase(player.getInventory().getItem(CobraPlugin.get().getConfig().getInt("command.staffmode.item.inspector.slot")).getItemMeta().getDisplayName())
                    || CC.translate(CobraPlugin.get().getConfig().getString("command.staffmode.item.freezer.name")).equalsIgnoreCase(player.getInventory().getItem(CobraPlugin.get().getConfig().getInt("command.staffmode.item.freezer.slot")).getItemMeta().getDisplayName())
                    || CC.translate(CobraPlugin.get().getConfig().getString("command.staffmode.item.vanish.name")).equalsIgnoreCase(player.getInventory().getItem(CobraPlugin.get().getConfig().getInt("command.staffmode.item.vanish.slot")).getItemMeta().getDisplayName())) {
                player.getInventory().clear();
                player.setGameMode(GameMode.SURVIVAL);
            }
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
                            .replace("%server%", CobraPlugin.get().getConfig().getString("server_name")))
                            .replace("%player%", profile.getPlayer().getName()));
                }
            }));
        }
    }

    private final List<String> BLOCKED_COMMANDS = Arrays.asList(
            "/me",
            "/bukkit:me",
            "/minecraft:me",
            "//calc"
    );

    @EventHandler
    public void onCommandProcess(PlayerCommandPreprocessEvent event) {
        for(String blockedCommand : BLOCKED_COMMANDS) {
            if(event.getMessage().startsWith(blockedCommand)) {
                event.setCancelled(true);
                event.getPlayer().sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("cannot_execute_command")));
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
