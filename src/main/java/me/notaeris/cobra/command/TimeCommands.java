package me.notaeris.cobra.command;

import me.notaeris.cobra.CobraPlugin;
import me.notaeris.cobra.util.CC;
import me.notaeris.cobra.util.command.Command;
import me.notaeris.cobra.util.command.CommandArgs;
import org.bukkit.entity.Player;

public class TimeCommands {

    @Command(name = "day", playerOnly = true)
    public void execute(CommandArgs args) {
        Player player = args.getPlayer();

        player.setPlayerTime(0, true);
        args.getSender().sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.day")));
    }

    @Command(name = "sunset", playerOnly = true)
    public void execute2(CommandArgs args) {
        Player player = args.getPlayer();

        player.setPlayerTime(12500, true);
        args.getSender().sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.sunset")));
    }

    @Command(name = "night", playerOnly = true)
    public void execute3(CommandArgs args) {
        Player player = args.getPlayer();

        player.setPlayerTime(14000, true);
        args.getSender().sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.night")));
    }
}
