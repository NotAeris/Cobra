package me.notaeris.cobra.command;

import me.notaeris.cobra.CobraPlugin;
import me.notaeris.cobra.util.CC;
import me.notaeris.cobra.util.command.Command;
import me.notaeris.cobra.util.command.CommandArgs;
import org.bukkit.entity.Player;

public class FlyCommand {

    @Command(name = "fly", aliases = { "flight" }, permission = "cobra.command.fly", playerOnly = true)
    public void execute(CommandArgs args) {
        Player player = args.getPlayer();

        if(CobraPlugin.get().getAPI().getFly(player)) {
            CobraPlugin.get().getAPI().setFlyDisabled(player);
            player.setAllowFlight(false);
            player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.fly.disabled")));
        } else {
            CobraPlugin.get().getAPI().setFlyEnabled(player);
            player.setAllowFlight(true);
            player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.fly.enabled")));
        }
    }
}
