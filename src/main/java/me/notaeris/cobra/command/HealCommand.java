package me.notaeris.cobra.command;

import me.notaeris.cobra.CobraPlugin;
import me.notaeris.cobra.util.CC;
import me.notaeris.cobra.util.command.Command;
import me.notaeris.cobra.util.command.CommandArgs;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class HealCommand {

    @Command(name = "heal", permission = "cobra.command.heal", playerOnly = true)
    public void execute(CommandArgs args) {
        Player player = args.getPlayer();

        if(args.length() == 0) {
            player.setHealth(20.0);
            player.setFoodLevel(20);
            player.getActivePotionEffects().forEach(effect -> player.removePotionEffect(effect.getType()));
            player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.heal.player")));
        } else {
            Player target = Bukkit.getPlayer(args.getArgs(0));
            if(target == null) {
                player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("player_offline")));
            } else {
                target.setHealth(20.0);
                target.setFoodLevel(20);
                target.getActivePotionEffects().forEach(effect -> target.removePotionEffect(effect.getType()));
                target.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.heal.player")));
                player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.heal.target"))
                        .replace("%target%", target.getName()));
            }
        }
    }
}
