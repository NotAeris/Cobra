package me.notaeris.cobra.command;

import me.notaeris.cobra.CobraPlugin;
import me.notaeris.cobra.util.CC;
import me.notaeris.cobra.util.command.Command;
import me.notaeris.cobra.util.command.CommandArgs;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.stream.Collectors;

public class ListCommand {

    @Command(name = "list", aliases= { "online", "who" })
    public void execute(CommandArgs args) {
        CobraPlugin.get().getConfig().getStringList("command.list").forEach(string -> args.getSender().sendMessage(CC.translate(string
                .replace("%online_players%", Integer.toString(Bukkit.getOnlinePlayers().size())))
                .replace("%max_players%", Integer.toString(Bukkit.getMaxPlayers()))
                .replace("%playerNames%", Bukkit.getOnlinePlayers().parallelStream().map(Player::getName).collect(Collectors.joining(", ")))));
    }
}
