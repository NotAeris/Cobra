package me.notaeris.cobra.command;

import me.notaeris.cobra.CobraPlugin;
import me.notaeris.cobra.util.CC;
import me.notaeris.cobra.util.command.Command;
import me.notaeris.cobra.util.command.CommandArgs;
import org.bukkit.Bukkit;

import java.util.stream.IntStream;

public class ChatCommands {

    @Command(name = "chat", permission = "cobra.command.clearchat", playerOnly = true)
    public void execute(CommandArgs args) {
        if(args.length() == 0) {
            CobraPlugin.get().getConfig().getStringList("command.chat.usage").forEach(string ->
                    args.getPlayer().sendMessage(CC.translate(string)));
        } else {
            if(args.getArgs(0).equalsIgnoreCase("clear")) {
                IntStream.range(0, CobraPlugin.get().getConfig().getInt("command.chat.clear.lines")).forEach(i -> Bukkit.getOnlinePlayers().parallelStream().filter(player -> !player.hasPermission("cobra.staff")).forEach(player -> player.sendMessage("")));
                Bukkit.broadcastMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.chat.clear.message")));
            }
            if(args.getArgs(0).equalsIgnoreCase("mute")) {
                if(CobraPlugin.get().getAPI().getChat()) {
                    CobraPlugin.get().getAPI().setChat(true);
                    Bukkit.broadcastMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.chat.mute.disabled")));
                } else {
                    CobraPlugin.get().getAPI().setChat(false);
                    Bukkit.broadcastMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.chat.mute.enabled")));
                }
            }
        }
    }
}
