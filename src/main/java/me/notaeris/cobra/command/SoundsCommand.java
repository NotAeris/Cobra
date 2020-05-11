package me.notaeris.cobra.command;

import me.notaeris.cobra.CobraPlugin;
import me.notaeris.cobra.util.CC;
import me.notaeris.cobra.util.command.Command;
import me.notaeris.cobra.util.command.CommandArgs;
import org.bukkit.entity.Player;

public class SoundsCommand {

    @Command(name = "sounds", playerOnly = true)
    public void execute(CommandArgs args) {
        Player player = args.getPlayer();

        if(CobraPlugin.get().getAPI().getSounds(player)) {
            CobraPlugin.get().getAPI().setSoundsDisabled(player);
            player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.message.sounds.enabled")));
        } else {
            CobraPlugin.get().getAPI().setSoundsEnabled(player);
            player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.message.sounds.disabled")));
        }
    }
}
