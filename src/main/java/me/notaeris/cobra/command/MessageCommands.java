package me.notaeris.cobra.command;

import me.notaeris.cobra.CobraPlugin;
import me.notaeris.cobra.util.CC;
import me.notaeris.cobra.util.command.Command;
import me.notaeris.cobra.util.command.CommandArgs;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class MessageCommands {

    private Player target;

    @Command(name = "message", aliases = { "msg", "m", "t"}, playerOnly = true)
    public void execute(CommandArgs args) {
        Player player = args.getPlayer();
        String message = StringUtils.join(args.getArgs(), ' ', 1, args.length());

        if(args.length() <= 1) {
            player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.message.usage"))
                    .replace("%command%", args.getLabel()));
        } else{
            target = Bukkit.getPlayer(args.getArgs(0));
            if(target == null) {
                player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("player_offline")));
            }
            if(CobraPlugin.get().getAPI().getTogglePm(player) || CobraPlugin.get().getAPI().getTogglePm(target)) {
                player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.message.togglepm.messages_disabled")));
            } else {
                CobraPlugin.get().getConfig().getStringList("command.message.to").forEach(string ->
                        player.sendMessage(CC.translate(string
                                .replace("%target%", target.getName()))
                                .replace("%message%", message)));
                if(CobraPlugin.get().getAPI().getSounds(player)) {
                    CobraPlugin.get().getConfig().getStringList("command.message.from").forEach(string ->
                            target.sendMessage(CC.translate(string
                                    .replace("%player%", player.getName()))
                                    .replace("%message%", message)));
                } else {
                    target.playSound(target.getLocation(), Sound.SUCCESSFUL_HIT, 100F, 0F);
                    CobraPlugin.get().getConfig().getStringList("command.message.from").forEach(string ->
                            target.sendMessage(CC.translate(string
                                    .replace("%player%", player.getName()))
                                    .replace("%message%", message)));
                }
            }
        }
    }

    @Command(name = "reply", aliases = { "r" }, playerOnly = true)
    public void execute2(CommandArgs args) {
        Player player = args.getPlayer();
        String message = StringUtils.join(args.getArgs(), ' ', 0, args.length());

        if(args.length() == 0) {
            player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.message.reply.usage"))
                    .replace("%command%", args.getLabel()));
        } else {
            if(CobraPlugin.get().getAPI().getTogglePm(player) || CobraPlugin.get().getAPI().getTogglePm(target)) {
                player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.message.togglepm.messages_disabled")));
            } else {
                CobraPlugin.get().getConfig().getStringList("command.message.to").forEach(string ->
                        player.sendMessage(CC.translate(string
                                .replace("%target%", this.target.getName()))
                                .replace("%message%", message)));
                if(CobraPlugin.get().getAPI().getSounds(player)) {
                    CobraPlugin.get().getConfig().getStringList("command.message.from").forEach(string ->
                            target.sendMessage(CC.translate(string
                                    .replace("%player%", player.getName()))
                                    .replace("%message%", message)));
                } else {
                    target.playSound(target.getLocation(), Sound.SUCCESSFUL_HIT, 100F, 0F);
                    CobraPlugin.get().getConfig().getStringList("command.message.from").forEach(string ->
                            target.sendMessage(CC.translate(string
                                    .replace("%player%", player.getName()))
                                    .replace("%message%", message)));
                }
            }
        }
    }
}
