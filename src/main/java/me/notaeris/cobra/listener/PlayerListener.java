package me.notaeris.cobra.listener;

import me.notaeris.cobra.CobraPlugin;
import me.notaeris.cobra.util.CC;
import me.notaeris.cobra.util.PlayerUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.setJoinMessage(null);
        Player player = event.getPlayer();

        CobraPlugin.get().getConfig().getStringList("staff.connect").forEach(string -> PlayerUtil.getOnlinePlayers().forEach(target -> {
            if(target.hasPermission("cobra.staff")) {
                target.sendMessage(CC.translate(string
                        .replace("%server%", CobraPlugin.get().getConfig().getString("server_name")))
                        .replace("%player%", player.getName()));
            }
        }));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        event.setQuitMessage(null);
        Player player = event.getPlayer();

        CobraPlugin.get().getConfig().getStringList("staff.disconnect").forEach(string -> PlayerUtil.getOnlinePlayers().forEach(target -> {
            if(target.hasPermission("cobra.staff")) {
                target.sendMessage(CC.translate(string
                        .replace("%server%", CobraPlugin.get().getConfig().getString("server_name")))
                        .replace("%player%", player.getName()));
            }
        }));
    }
}
