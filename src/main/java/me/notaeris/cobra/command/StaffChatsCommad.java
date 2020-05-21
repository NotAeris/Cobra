package me.notaeris.cobra.command;

import me.notaeris.cobra.CobraPlugin;
import me.notaeris.cobra.util.CC;
import me.notaeris.cobra.util.command.Command;
import me.notaeris.cobra.util.command.CommandArgs;
import org.bukkit.entity.Player;

public class StaffChatsCommad {

    @Command(name = "staffchats", aliases = { "scs" }, permission = "cobra.command.staffchat", playerOnly = true)
    public void execute(CommandArgs args) {
        Player player = args.getPlayer();

        if(CobraPlugin.get().getAPI().getStaffChat(player)) {
            CobraPlugin.get().getAPI().setStaffChatDisabled(player);
            player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.staffchats.enabled")));
        } else {
            CobraPlugin.get().getAPI().setStaffChatEnabled(player);
            player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.staffchats.disabled")));
        }
    }
}
