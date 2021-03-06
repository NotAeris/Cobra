package me.notaeris.cobra.command;

import me.notaeris.cobra.CobraPlugin;
import me.notaeris.cobra.util.CC;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import me.notaeris.cobra.util.command.Command;
import me.notaeris.cobra.util.command.CommandArgs;

public class StaffChatCommand {

    @Command(name = "staffchat", aliases = { "sc" }, permission = "cobra.command.staffchat", playerOnly = true)
    public void execute(CommandArgs args) {
        Player player = args.getPlayer();
        String message = StringUtils.join(args.getArgs(), ' ', 0, args.length());

        if(args.length() == 0) {
            player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.staffchat.usage"))
                    .replace("%command%", args.getLabel()));
        } else {
            if(CobraPlugin.get().getAPI().getStaffChat(player)) {
                player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.staffchats.currently_disabled")));
            } else {
                CobraPlugin.get().getConfig().getStringList("command.staffchat.format").forEach(string -> Bukkit.getOnlinePlayers().forEach(target -> {
                    if(target.hasPermission("cobra.staff") && !CobraPlugin.get().getAPI().getStaffChat(target)) {
                        target.sendMessage(CC.translate(string
                                .replace("%server%", CobraPlugin.get().getConfig().getString("server_name"))
                                .replace("%player%", player.getName())
                                .replace("%message%", message)));
                    }
                }));
            }
        }
    }
}
