package me.notaeris.cobra.command;

import me.notaeris.cobra.CobraPlugin;
import me.notaeris.cobra.util.CC;
import me.notaeris.cobra.util.PlayerUtil;
import me.notaeris.cobra.util.command.Command;
import me.notaeris.cobra.util.command.CommandArgs;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class VanishCommand {

    private final ArrayList<Player> vanish = new ArrayList<>();

    @Command(name = "vanish", aliases = { "v" }, permission = "cobra.command.vanish", playerOnly = true)
    public void execute(CommandArgs args) {
        Player player = args.getPlayer();

        if(this.vanish.contains(player)) {
            this.vanish.remove(player);
            this.setVanished(player, false);
            player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.vanish.disabled")));
        } else {
            this.vanish.add(player);
            this.setVanished(player, true);
            player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.vanish.enabled")));
        }
    }

    public void setVanished(Player player, boolean isVanished) {
        if(isVanished) {
            this.vanish.add(player);
            for(Player players : PlayerUtil.getOnlinePlayers()) {
                if(players.hasPermission("cobra.command.vanish")) {
                    players.showPlayer(player);
                } else {
                    players.hidePlayer(player);
                }
            }
        }
        if(!isVanished) {
            this.vanish.remove(player);
            for(Player players : PlayerUtil.getOnlinePlayers()) {
                players.showPlayer(player);
            }
        }
    }
}
