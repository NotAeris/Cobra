package me.notaeris.cobra.command;

import me.notaeris.cobra.CobraPlugin;
import me.notaeris.cobra.util.CC;
import me.notaeris.cobra.util.command.Command;
import me.notaeris.cobra.util.command.CommandArgs;
import org.bukkit.entity.Player;

public class SpawnCommands {

    @Command(name = "setspawn", permission = "cobra.command.setspawn", playerOnly = true)
    public void execute(CommandArgs args) {
        Player player = args.getPlayer();

        player.getWorld().setSpawnLocation(player.getLocation().getBlockX(), player.getLocation().getBlockY(), player.getLocation().getBlockZ());
        player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.setspawn")));
    }

    @Command(name = "spawn", permission = "cobra.command.spawn", playerOnly = true)
    public void execute2(CommandArgs args) {
        Player player = args.getPlayer();

        player.teleport(player.getWorld().getSpawnLocation());
        player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.spawn")));
    }
}
