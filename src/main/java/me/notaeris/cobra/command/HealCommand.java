package me.notaeris.cobra.command;

import me.notaeris.cobra.CobraPlugin;
import me.notaeris.cobra.util.CC;
import me.notaeris.cobra.util.command.Command;
import me.notaeris.cobra.util.command.CommandArgs;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCommand {

    @Command(name = "heal", permission = "cobra.command.heal", playerOnly = true)
    public void execute(CommandArgs args) {
        CommandSender sender = args.getSender();
        Player player = args.getPlayer();

        if(args.length() == 0) {
            player.setHealth(20.0);
            player.setFoodLevel(20);
            sender.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.heal.player")));
        } else {
            Player target = Bukkit.getPlayer(args.getArgs(0));
            if(target == null) {
                sender.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("player_offline")));
            } else {
                target.setHealth(20.0);
                target.setFoodLevel(20);
                target.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.heal.player")));
                sender.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.heal.target"))
                        .replace("%target%", target.getName()));
            }
        }
    }
}
