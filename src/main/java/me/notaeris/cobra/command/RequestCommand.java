package me.notaeris.cobra.command;

import me.notaeris.cobra.CobraPlugin;
import me.notaeris.cobra.util.CC;
import me.notaeris.cobra.util.command.Command;
import me.notaeris.cobra.util.command.CommandArgs;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class RequestCommand {

    @Command(name = "request", aliases = { "helpop" }, playerOnly = true)
    public void execute(CommandArgs args) {
        Player player = args.getPlayer();
        String message = StringUtils.join(args.getArgs(), ' ', 0, args.length());

        if(args.length() == 0) {
            player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.request.usage"))
                    .replace("%command%", args.getLabel()));
        } else {
            if(CobraPlugin.get().getAPI().isRequests()) {
                if(CobraPlugin.get().getCooldown().hasCooldown(player.getUniqueId(), "request")) {
                    player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.request.cooldown.message")));
                } else {
                    CobraPlugin.get().getConfig().getStringList("command.request.format").forEach(string -> Bukkit.getOnlinePlayers().forEach(target -> {
                        if(target.hasPermission("cobra.staff")) {
                            target.sendMessage(CC.translate(string
                                    .replace("%server%", CobraPlugin.get().getConfig().getString("server_name")))
                                    .replace("%player%", player.getName())
                                    .replace("%message%", message));
                        }
                    }));
                    player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.request.sent")));
                    CobraPlugin.get().getCooldown().addCooldown(player.getUniqueId(), "request", CobraPlugin.get().getConfig().getInt("command.request.cooldown.time"));
                }
            } else {
                player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.requests.currently_disabled")));
            }
        }
    }
}
