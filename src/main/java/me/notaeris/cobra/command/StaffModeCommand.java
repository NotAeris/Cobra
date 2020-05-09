package me.notaeris.cobra.command;

import me.notaeris.cobra.CobraPlugin;
import me.notaeris.cobra.util.CC;
import me.notaeris.cobra.util.ItemBuilder;
import me.notaeris.cobra.util.command.Command;
import me.notaeris.cobra.util.command.CommandArgs;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class StaffModeCommand {

    public static ArrayList<Player> staffmode = new ArrayList<>();

    @Command(name = "staffmode", aliases = { "staff", "mod", "modmode", "h" }, permission = "cobra.command.staffmode", playerOnly = true)
    public void execute(CommandArgs args) {
        Player player = args.getPlayer();

        if(staffmode.contains(player)) {
            staffmode.remove(player);
            player.getInventory().clear();
            player.setGameMode(GameMode.SURVIVAL);
            player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.staffmode.disabled")));
        } else {
            staffmode.add(player);
            player.getInventory().clear();
            player.getActivePotionEffects().forEach(effect -> player.removePotionEffect(effect.getType()));
            player.setGameMode(GameMode.CREATIVE);

            ItemStack teleporter = new ItemBuilder(Material.getMaterial(CobraPlugin.get().getConfig().getString("command.staffmode.item.teleporter.material")))
                    .setName(CC.translate(CobraPlugin.get().getConfig().getString("command.staffmode.item.teleporter.name")))
                    .create();
            player.getInventory().setItem(CobraPlugin.get().getConfig().getInt("command.staffmode.item.teleporter.slot"), teleporter);

            ItemStack inspector = new ItemBuilder(Material.getMaterial(CobraPlugin.get().getConfig().getString("command.staffmode.item.inspector.material")))
                    .setName(CC.translate(CobraPlugin.get().getConfig().getString("command.staffmode.item.inspector.name")))
                    .create();
            player.getInventory().setItem(CobraPlugin.get().getConfig().getInt("command.staffmode.item.inspector.slot"), inspector);

            ItemStack freezer = new ItemBuilder(Material.getMaterial(CobraPlugin.get().getConfig().getString("command.staffmode.item.freezer.material")))
                    .setName(CC.translate(CobraPlugin.get().getConfig().getString("command.staffmode.item.freezer.name")))
                    .create();
            player.getInventory().setItem(CobraPlugin.get().getConfig().getInt("command.staffmode.item.freezer.slot"), freezer);

            ItemStack vanish = new ItemBuilder(Material.getMaterial(CobraPlugin.get().getConfig().getString("command.staffmode.item.vanish.material")), CobraPlugin.get().getConfig().getInt("command.staffmode.item.vanish.amount"), (short) CobraPlugin.get().getConfig().getInt("command.staffmode.item.vanish.value"))
                    .setName(CC.translate(CobraPlugin.get().getConfig().getString("command.staffmode.item.vanish.name")))
                    .create();
            player.getInventory().setItem(CobraPlugin.get().getConfig().getInt("command.staffmode.item.vanish.slot"), vanish);

            player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.staffmode.enabled")));
        }
    }
}
