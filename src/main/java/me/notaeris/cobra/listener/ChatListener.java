package me.notaeris.cobra.listener;

import me.notaeris.cobra.CobraPlugin;
import me.notaeris.cobra.util.CC;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        for(String blockedMessage : CobraPlugin.get().getConfig().getStringList("filter.chat.blocked_messages")) {
            if(event.getMessage().contains(blockedMessage)) {
                event.setCancelled(true);
                player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("filter.chat.blocked_message")));
                CobraPlugin.get().getConfig().getStringList("filter.chat.message").forEach(string -> Bukkit.getOnlinePlayers().forEach(online -> {
                    if(online.hasPermission("cobra.staff")) {
                        online.sendMessage(CC.translate(string
                                .replace("%player%", player.getName())
                                .replace("%message%", event.getMessage())));
                    }
                }));
            }
        }

        if(!CobraPlugin.get().getAPI().getChat()) {
            if(!player.hasPermission("cobra.staff")) {
                event.setCancelled(true);
                player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.chat.mute.currently_enabled")));
            }
        }
    }
}
