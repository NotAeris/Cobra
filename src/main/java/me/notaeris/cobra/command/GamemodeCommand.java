package me.notaeris.cobra.command;

import me.notaeris.cobra.CobraPlugin;
import me.notaeris.cobra.util.CC;
import me.notaeris.cobra.util.command.Command;
import me.notaeris.cobra.util.command.CommandArgs;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class GamemodeCommand {

    @Command(name = "gamemode", aliases = { "gm" }, permission = "cobra.command.gamemode", playerOnly = true)
    public void execute(CommandArgs args) {
        Player player = args.getPlayer();

        if(args.length() == 0) {
            player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.gamemode.usage"))
                    .replace("%command%", args.getLabel()));
        } else {
            if(args.getArgs(0).equalsIgnoreCase("survival") || args.getArgs(0).equalsIgnoreCase("s") || args.getArgs(0).equalsIgnoreCase("0")) {
                player.setGameMode(GameMode.SURVIVAL);
                player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.gamemode.updated"))
                        .replace("%player%", player.getName())
                        .replace("%gamemode%", player.getGameMode().toString().toUpperCase()));
            }
            if(args.getArgs(0).equalsIgnoreCase("creative") || args.getArgs(0).equalsIgnoreCase("c") || args.getArgs(0).equalsIgnoreCase("1")) {
                player.setGameMode(GameMode.CREATIVE);
                player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.gamemode.updated"))
                        .replace("%player%", player.getName())
                        .replace("%gamemode%", player.getGameMode().toString().toUpperCase()));
            }
            if(args.getArgs(0).equalsIgnoreCase("adventure") || args.getArgs(0).equalsIgnoreCase("a") || args.getArgs(0).equalsIgnoreCase("2")) {
                player.setGameMode(GameMode.ADVENTURE);
                player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.gamemode.updated"))
                        .replace("%player%", player.getName())
                        .replace("%gamemode%", player.getGameMode().toString().toUpperCase()));
            }
        }
    }
}
