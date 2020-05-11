package me.notaeris.cobra.command;

import me.notaeris.cobra.CobraPlugin;
import me.notaeris.cobra.util.CC;
import me.notaeris.cobra.util.command.Command;
import me.notaeris.cobra.util.command.CommandArgs;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ChatCommands {

    @Command(name = "chat", permission = "cobra.command.clearchat")
    public void execute(CommandArgs args) {
        Player player = args.getPlayer();

        if(args.length() == 0) {
            CobraPlugin.get().getConfig().getStringList("command.chat.usage").forEach(string ->
                    player.sendMessage(CC.translate(string)));
        } else {
            if(args.getArgs(0).equalsIgnoreCase("clear")) {
                for(int i = 0; i < CobraPlugin.get().getConfig().getInt("command.chat.clear.lines"); i++) {
                    Bukkit.broadcastMessage("");
                }
                Bukkit.broadcastMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.chat.clear.message")));
            }
            if(args.getArgs(0).equalsIgnoreCase("mute")) {
                if(CobraPlugin.get().getAPI().getChat()) {
                    CobraPlugin.get().getAPI().setChatEnabled();
                    Bukkit.broadcastMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.chat.mute.disabled")));
                } else {
                    CobraPlugin.get().getAPI().setChatDisabled();
                    Bukkit.broadcastMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.chat.mute.enabled")));
                }
            }
        }
    }
}
