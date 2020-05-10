package me.notaeris.cobra.command;

import me.notaeris.cobra.CobraPlugin;
import me.notaeris.cobra.util.CC;
import me.notaeris.cobra.util.command.Command;
import me.notaeris.cobra.util.command.CommandArgs;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TogglePmCommand {

    public static List<Player> togglepm = new ArrayList<>();

    @Command(name = "togglepm", aliases = { "tpm" }, playerOnly = true)
    public void execute(CommandArgs args) {
        Player player = args.getPlayer();

        if(CobraPlugin.get().getAPI().getTogglePm(player)) {
            togglepm.remove(player);
            player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.message.togglepm.enabled")));
        } else {
            togglepm.add(player);
            player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.message.togglepm.disabled")));
        }
    }
}
