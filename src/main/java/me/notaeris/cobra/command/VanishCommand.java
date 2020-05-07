package me.notaeris.cobra.command;

import me.notaeris.cobra.CobraPlugin;
import me.notaeris.cobra.util.CC;
import me.notaeris.cobra.util.command.Command;
import me.notaeris.cobra.util.command.CommandArgs;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class VanishCommand {

    public static final ArrayList<Player> vanish = new ArrayList<>();

    @Command(name = "vanish", aliases = { "v" }, permission = "cobra.command.vanish", playerOnly = true)
    public void execute(CommandArgs args) {
        Player player = args.getPlayer();

        if(vanish.contains(player)) {
            vanish.remove(player);
            this.setVanished(player, false);
            player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.vanish.disabled")));
        } else {
            vanish.add(player);
            setVanished(player, true);
            player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.vanish.enabled")));
        }
    }

    public static void setVanished(Player player, boolean isVanished) {
        if(isVanished) {
            vanish.add(player);
            for(Player players : Bukkit.getOnlinePlayers()) {
                if(players.hasPermission("cobra.command.vanish")) {
                    players.showPlayer(player);
                } else {
                    players.hidePlayer(player);
                }
            }
        }
        if(!isVanished) {
            vanish.remove(player);
            for(Player players : Bukkit.getOnlinePlayers()) {
                players.showPlayer(player);
            }
        }
    }
}
