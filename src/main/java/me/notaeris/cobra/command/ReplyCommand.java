package me.notaeris.cobra.command;

import me.notaeris.cobra.CobraPlugin;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import me.notaeris.cobra.util.CC;
import me.notaeris.cobra.util.command.Command;
import me.notaeris.cobra.util.command.CommandArgs;

public class ReplyCommand {

    @Command(name = "reply", aliases = { "r" }, playerOnly = true)
    public void execute(CommandArgs args) {
        Player player = args.getPlayer();
        String message = StringUtils.join(args.getArgs(), ' ', 0, args.length());

        if(args.length() == 0) {
            player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.message.reply.usage"))
                    .replace("%command%", args.getLabel()));
        } else {
            if(TogglePmCommand.togglepm.contains(player) || TogglePmCommand.togglepm.contains(MessageCommand.target)) {
                player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.message.togglepm.messages_disabled")));
            } else {
                CobraPlugin.get().getConfig().getStringList("command.message.to").forEach(string -> player.sendMessage(CC.translate(string
                        .replace("%target%", MessageCommand.target.getName()))
                        .replace("%message%", message)));
                if(SoundsCommand.sounds.contains(player)) {
                    CobraPlugin.get().getConfig().getStringList("command.message.from").forEach(string -> MessageCommand.target.sendMessage(CC.translate(string
                            .replace("%player%", player.getName()))
                            .replace("%message%", message)));
                } else {
                    MessageCommand.target.playSound(MessageCommand.target.getLocation(), Sound.SUCCESSFUL_HIT, 100F, 0F);
                    CobraPlugin.get().getConfig().getStringList("command.message.from").forEach(string -> MessageCommand.target.sendMessage(CC.translate(string
                            .replace("%player%", player.getName()))
                            .replace("%message%", message)));
                }
            }
        }
    }
}
