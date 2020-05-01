package me.notaeris.cobra.command;

import me.notaeris.cobra.CobraPlugin;
import me.notaeris.cobra.util.CC;
import me.notaeris.cobra.util.Cooldown;
import me.notaeris.cobra.util.PlayerUtil;
import me.notaeris.cobra.util.command.Command;
import me.notaeris.cobra.util.command.CommandArgs;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReportCommand {

    @Command(name = "report", playerOnly = true)
    public void execute(CommandArgs args) {
        CommandSender sender = args.getSender();
        Player player = args.getPlayer();
        String message = StringUtils.join(args.getArgs(), ' ', 1, args.length());

        if(args.length() <= 1) {
            sender.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.report.usage"))
                    .replace("%command%", args.getLabel()));
        } else {
            Player target = Bukkit.getPlayer(args.getArgs(0));
            if(target == null) {
                sender.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("player_offline")));
            } else {
                if(Cooldown.hasCooldown(player.getUniqueId(), "report")) {
                    sender.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.report.cooldown.message")));
                } else {
                    CobraPlugin.get().getConfig().getStringList("command.report.format").forEach(string -> PlayerUtil.getOnlinePlayers().forEach(target2 -> {
                        if(target2.hasPermission("cobra.staff")) {
                            target2.sendMessage(CC.translate(string
                                    .replace("%server%", CobraPlugin.get().getConfig().getString("server_name")))
                                    .replace("%target%", target.getName())
                                    .replace("%player%", player.getName())
                                    .replace("%message%", message));
                        }
                    }));
                    sender.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.report.sent")));
                    Cooldown cooldown = new Cooldown(player.getUniqueId(), "report", CobraPlugin.get().getConfig().getInt("command.report.cooldown.time"));
                    cooldown.start();
                }
            }
        }
    }
}
