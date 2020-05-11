package me.notaeris.cobra.command;

import me.notaeris.cobra.CobraPlugin;
import me.notaeris.cobra.util.CC;
import me.notaeris.cobra.util.command.Command;
import me.notaeris.cobra.util.command.CommandArgs;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class FreezeCommand {

    @Command(name = "freeze", aliases = { "ss" }, permission = "cobra.command.freeze", playerOnly = true)
    public void execute(CommandArgs args) {
        Player player = args.getPlayer();

        if(args.length() == 0) {
            player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.freeze.usage"))
                    .replace("%command%", args.getLabel()));
        } else {
            Player target = Bukkit.getPlayer(args.getArgs(0));
            if(target == null) {
                player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("player_offline")));
            } else {
                if(!CobraPlugin.get().getAPI().getFreeze(target)) {
                    CobraPlugin.get().getAPI().setFreezeEnabled(target);
                    target.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.freeze.target.frozen")));
                    player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.freeze.player.froze"))
                            .replace("%target%", target.getName()));
                } else {
                    CobraPlugin.get().getAPI().setFreezeDisabled(target);
                    target.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.freeze.target.unfrozen")));
                    player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.freeze.player.unfroze"))
                            .replace("%target%", target.getName()));
                }
            }
        }
    }
}
