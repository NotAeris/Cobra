package me.notaeris.cobra.command;

import me.notaeris.cobra.CobraPlugin;
import me.notaeris.cobra.util.CC;
import me.notaeris.cobra.util.command.Command;
import me.notaeris.cobra.util.command.CommandArgs;
import org.bukkit.entity.Player;

public class RequestsCommand {

    @Command(name = "requests", permission = "cobra.command.requests")
    public void execute(CommandArgs args) {
        Player player = args.getPlayer();

        if(CobraPlugin.get().getAPI().isRequests()) {
            CobraPlugin.get().getAPI().setRequests(true);
            player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.requests.disabled")));
        } else {
            CobraPlugin.get().getAPI().setRequests(false);
            player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.requests.enabled")));
        }
    }
}
