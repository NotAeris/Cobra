package me.notaeris.cobra.command;

import me.notaeris.cobra.CobraPlugin;
import me.notaeris.cobra.util.CC;
import me.notaeris.cobra.util.command.Command;
import me.notaeris.cobra.util.command.CommandArgs;
import org.bukkit.entity.Player;

public class TogglePmCommand {

    @Command(name = "togglepm", aliases = { "tpm" }, playerOnly = true)
    public void execute(CommandArgs args) {
        Player player = args.getPlayer();

        if(CobraPlugin.get().getAPI().getTogglePm(player)) {
            CobraPlugin.get().getAPI().setTogglepmDisabled(player);
            player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.message.togglepm.enabled")));
        } else {
            CobraPlugin.get().getAPI().setTogglepmEnabled(player);
            player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.message.togglepm.disabled")));
        }
    }
}
