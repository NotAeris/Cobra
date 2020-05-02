package me.notaeris.cobra.command;

import me.notaeris.cobra.CobraPlugin;
import me.notaeris.cobra.util.CC;
import org.bukkit.Bukkit;
import me.notaeris.cobra.util.command.Command;
import me.notaeris.cobra.util.command.CommandArgs;

public class ChatCommands {

    public static boolean chatToggled;

    @Command(name = "chat", permission = "cobra.command.clearchat")
    public void execute(CommandArgs args) {
        if(args.length() == 0) {
            CobraPlugin.get().getConfig().getStringList("command.chat.usage").forEach(string ->
                    args.getPlayer().sendMessage(CC.translate(string)));
        } else {
            if(args.getArgs(0).equalsIgnoreCase("clear")) {
                for(int i = 0; i < CobraPlugin.get().getConfig().getInt("command.chat.clear.lines"); i++) {
                    Bukkit.broadcastMessage("");
                }
                Bukkit.broadcastMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.chat.clear.message")));
            }
            if(args.getArgs(0).equalsIgnoreCase("mute")) {
                if(!chatToggled) {
                    chatToggled = true;
                    Bukkit.broadcastMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.chat.mute.disabled")));
                } else {
                    chatToggled = false;
                    Bukkit.broadcastMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.chat.mute.enabled")));
                }
            }
        }
    }
}
