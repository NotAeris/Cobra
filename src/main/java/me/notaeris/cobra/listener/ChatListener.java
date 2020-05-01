package me.notaeris.cobra.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import me.notaeris.cobra.CobraPlugin;
import me.notaeris.cobra.command.ChatCommands;
import me.notaeris.cobra.util.CC;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();


        if(!ChatCommands.chatToggled) {
            event.setCancelled(false);
        } else {
            if(player.hasPermission("cobra.staff")) {
                event.setCancelled(false);
            } else {
                event.setCancelled(true);
                player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.chat.mute.currently_enabled")));
            }
        }
    }
}
