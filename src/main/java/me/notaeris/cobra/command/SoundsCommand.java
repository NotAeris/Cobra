package me.notaeris.cobra.command;

import me.notaeris.cobra.CobraPlugin;
import me.notaeris.cobra.util.CC;
import me.notaeris.cobra.util.command.Command;
import me.notaeris.cobra.util.command.CommandArgs;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class SoundsCommand {

    public static ArrayList<Player> sounds = new ArrayList<>();

    @Command(name = "sounds", playerOnly = true)
    public void execute(CommandArgs args) {
        Player player = args.getPlayer();

        if(sounds.contains(player)) {
            sounds.remove(player);
            player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.message.sounds.enabled")));
        } else {
            sounds.add(player);
            player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.message.sounds.disabled")));
        }
    }
}
