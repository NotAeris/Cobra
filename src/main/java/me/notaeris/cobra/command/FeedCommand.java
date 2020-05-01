package me.notaeris.cobra.command;

import me.notaeris.cobra.CobraPlugin;
import me.notaeris.cobra.util.CC;
import me.notaeris.cobra.util.command.Command;
import me.notaeris.cobra.util.command.CommandArgs;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FeedCommand {

    @Command(name = "feed", aliases = { "eat" }, permission = "cobra.command.feed", playerOnly = true)
    public void execute(CommandArgs args) {
        CommandSender sender = args.getSender();
        Player player = args.getPlayer();

        if(args.length() == 0) {
            player.setFoodLevel(20);
            sender.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.feed.player")));
        } else {
            Player target = Bukkit.getPlayer(args.getArgs(0));
            if(target == null) {
                sender.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("player_offline")));
            } else {
                target.setFoodLevel(20);
                target.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.feed.player")));
                sender.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.feed.target"))
                        .replace("%target%", target.getName()));
            }
        }
    }
}
