package me.notaeris.cobra.command;

import me.notaeris.cobra.CobraPlugin;
import me.notaeris.cobra.util.CC;
import me.notaeris.cobra.util.command.Command;
import me.notaeris.cobra.util.command.CommandArgs;
import org.bukkit.entity.Player;

public class ReportsCommand {

    @Command(name = "reports", permission = "cobra.command.reports", playerOnly = true)
    public void execute(CommandArgs args) {
        Player player = args.getPlayer();

        if(CobraPlugin.get().getAPI().getReports()) {
            CobraPlugin.get().getAPI().setReports(true);
            player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.reports.disabled")));
        } else {
            CobraPlugin.get().getAPI().setReports(false);
            player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.reports.enabled")));
        }
    }
}
