package me.notaeris.cobra.command;

import me.notaeris.cobra.CobraPlugin;
import me.notaeris.cobra.util.CC;
import me.notaeris.cobra.util.command.Command;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import me.notaeris.cobra.util.command.CommandArgs;

public class TeleportCommands {

    @Command(name = "teleport", aliases = { "tp" }, permission = "cobra.command.teleport", playerOnly = true)
    public void execute(CommandArgs args) {
        CommandSender sender = args.getSender();
        Player player = args.getPlayer();

        if(args.length() == 0) {
            sender.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.teleport.usage"))
                    .replace("%command%", args.getLabel()));
        } else {
            Player target = Bukkit.getPlayer(args.getArgs(0));
            if(target == null) {
                sender.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("player_offline")));
            } else {
                player.teleport(target);
                player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.teleport.target"))
                        .replace("%target%", target.getName()));
            }
        }
    }

    @Command(name = "teleporthere", aliases = { "tphere" }, permission = "cobra.command.teleporthere", playerOnly = true)
    public void execute2(CommandArgs args) {
        CommandSender sender = args.getSender();
        Player player = args.getPlayer();

        if(args.length() == 0) {
            sender.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.teleport.usage"))
                    .replace("%command%", args.getLabel()));
        } else {
            Player target = Bukkit.getPlayer(args.getArgs(0));
            if(target == null) {
                sender.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("player_offline")));
            } else {
                target.teleport(player);
                target.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.teleport.here.target"))
                        .replace("%player%", player.getName()));
                player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.teleport.here.player"))
                        .replace("%target%", target.getName())
                        .replace("%player%", player.getName()));
            }
        }
    }
}
