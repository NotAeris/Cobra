package me.notaeris.cobra.command;

import me.notaeris.cobra.CobraPlugin;
import me.notaeris.cobra.util.CC;
import me.notaeris.cobra.util.command.Command;
import me.notaeris.cobra.util.command.CommandArgs;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class InvseeCommand {

    @Command(name = "invsee", aliases = { "inv" }, permission = "cobra.command.invsee", playerOnly = true)
    public void execute(CommandArgs args) {
        Player player = args.getPlayer();

        if(args.length() == 0) {
            player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.invsee.usage"))
                    .replace("%command%", args.getLabel()));
        } else {
            Player target = Bukkit.getPlayer(args.getArgs(0));
            if(target == null) {
                player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("player_offline")));
            } else {
                Inventory inventory = Bukkit.createInventory(null, CobraPlugin.get().getConfig().getInt("invsee.size"), CC.translate(CobraPlugin.get().getConfig().getString("invsee.name"))
                        .replace("%target%", target.getName()));
                player.openInventory(inventory);

                if(target.getInventory().getHelmet() != null) {
                    inventory.setItem(0, target.getInventory().getHelmet());
                }
                if(target.getInventory().getChestplate() != null) {
                    inventory.setItem(1, target.getInventory().getChestplate());
                }
                if(target.getInventory().getLeggings() != null) {
                    inventory.setItem(2, target.getInventory().getLeggings());
                }
                if(target.getInventory().getBoots() != null) {
                    inventory.setItem(3, target.getInventory().getBoots());
                }
                for(int i = 9; i < target.getInventory().getSize() + 9; i++) {
                    inventory.setItem(i, target.getInventory().getItem(i - 9));
                }
                player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.invsee.message"))
                        .replace("%target%", target.getName()));
            }
        }
    }
}
