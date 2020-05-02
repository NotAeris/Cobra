package me.notaeris.cobra.command;

import me.notaeris.cobra.CobraPlugin;
import me.notaeris.cobra.util.CC;
import me.notaeris.cobra.util.command.Command;
import me.notaeris.cobra.util.command.CommandArgs;

public class SocialCommands {

    @Command(name = "discord")
    public void execute(CommandArgs args) {
        args.getPlayer().sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.discord")));
    }

    @Command(name = "twitter")
    public void execute2(CommandArgs args) {
        args.getPlayer().sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.twitter")));
    }

    @Command(name = "store")
    public void execute3(CommandArgs args) {
        args.getPlayer().sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.store")));
    }
}
