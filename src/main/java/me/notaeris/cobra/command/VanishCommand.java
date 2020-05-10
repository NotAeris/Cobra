package me.notaeris.cobra.command;

import me.notaeris.cobra.CobraPlugin;
import me.notaeris.cobra.util.CC;
import me.notaeris.cobra.util.command.Command;
import me.notaeris.cobra.util.command.CommandArgs;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class VanishCommand {

    public static ArrayList<Player> vanish = new ArrayList<>();

    @Command(name = "vanish", aliases = {"v"}, permission = "cobra.command.vanish", playerOnly = true)
    public void execute(CommandArgs args) {
        Player player = args.getPlayer();

        if(CobraPlugin.get().getAPI().getVanish(player)) {
            vanish.remove(player);
            CobraPlugin.get().getAPI().setVanished(player, false);
            player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.vanish.disabled")));
        } else {
            vanish.add(player);
            CobraPlugin.get().getAPI().setVanished(player, true);
            player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.vanish.enabled")));
        }
    }
}
