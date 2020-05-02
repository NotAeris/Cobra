package me.notaeris.cobra.command;

import me.notaeris.cobra.CobraPlugin;
import me.notaeris.cobra.util.CC;
import me.notaeris.cobra.util.command.Command;
import me.notaeris.cobra.util.command.CommandArgs;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ClearCommand {

    @Command(name = "clear", aliases = { "ci" }, permission = "cobra.command.clear", playerOnly = true)
    public void execute(CommandArgs args) {
        Player player = args.getPlayer();

        if(args.length() == 0) {
            player.getInventory().setArmorContents(null);
            player.getInventory().clear();
            player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.clear.player")));
        } else {
            Player target = Bukkit.getPlayer(args.getArgs(0));
            if(target == null) {
                player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("player_offline")));
            } else {
                target.getInventory().setArmorContents(null);
                target.getInventory().clear();
                target.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.clear.target"))
                        .replace("%target%", target.getName()));
                player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.clear.player")));
            }
        }
    }
}
