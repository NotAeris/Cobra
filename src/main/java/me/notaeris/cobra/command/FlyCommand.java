package me.notaeris.cobra.command;

import me.notaeris.cobra.CobraPlugin;
import me.notaeris.cobra.util.CC;
import me.notaeris.cobra.util.command.Command;
import me.notaeris.cobra.util.command.CommandArgs;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class FlyCommand {

    private final ArrayList<Player> fly = new ArrayList<>();

    @Command(name = "fly", aliases = { "flight" }, permission = "cobra.command.fly", playerOnly = true)
    public void execute(CommandArgs args) {
        Player player = args.getPlayer();

        if(this.fly.contains(player)) {
            this.fly.remove(player);
            player.setAllowFlight(false);
            player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.fly.disabled")));
        } else {
            this.fly.add(player);
            player.setAllowFlight(true);
            player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.fly.enabled")));
        }
    }
}
