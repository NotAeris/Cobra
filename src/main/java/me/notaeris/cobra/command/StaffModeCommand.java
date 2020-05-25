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

public class StaffModeCommand {

    @Command(name = "staffmode", aliases = { "staff", "mod", "modmode", "h" }, permission = "cobra.command.staffmode", playerOnly = true)
    public void execute(CommandArgs args) {
        Player player = args.getPlayer();

        if(CobraPlugin.get().getAPI().getStaffMode(player)) {
            CobraPlugin.get().getAPI().setStaffModeDisabled(player);
            player.getInventory().clear();
            player.setGameMode(GameMode.SURVIVAL);
            player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.staffmode.disabled")));
        } else {
            CobraPlugin.get().getAPI().setStaffModeEnabled(player);
            CobraPlugin.get().getAPI().setVanishEnabled(player);
            player.getInventory().clear();
            player.getActivePotionEffects().forEach(effect -> player.removePotionEffect(effect.getType()));
            player.setGameMode(GameMode.CREATIVE);

            ItemStack teleporter = new ItemBuilder(Material.getMaterial(CobraPlugin.get().getConfig().getString("staffmode.item.teleporter.material")))
                    .setName(CC.translate(CobraPlugin.get().getConfig().getString("staffmode.item.teleporter.name")))
                    .create();
            player.getInventory().setItem(CobraPlugin.get().getConfig().getInt("staffmode.item.teleporter.slot"), teleporter);

            ItemStack inspector = new ItemBuilder(Material.getMaterial(CobraPlugin.get().getConfig().getString("staffmode.item.inspector.material")))
                    .setName(CC.translate(CobraPlugin.get().getConfig().getString("staffmode.item.inspector.name")))
                    .create();
            player.getInventory().setItem(CobraPlugin.get().getConfig().getInt("staffmode.item.inspector.slot"), inspector);

            ItemStack freezer = new ItemBuilder(Material.getMaterial(CobraPlugin.get().getConfig().getString("staffmode.item.freezer.material")))
                    .setName(CC.translate(CobraPlugin.get().getConfig().getString("staffmode.item.freezer.name")))
                    .create();
            player.getInventory().setItem(CobraPlugin.get().getConfig().getInt("staffmode.item.freezer.slot"), freezer);

            ItemStack vanish = new ItemBuilder(Material.getMaterial(CobraPlugin.get().getConfig().getString("staffmode.item.vanish.material")), CobraPlugin.get().getConfig().getInt("staffmode.item.vanish.amount"), (short) CobraPlugin.get().getConfig().getInt("staffmode.item.vanish.value"))
                    .setName(CC.translate(CobraPlugin.get().getConfig().getString("staffmode.item.vanish.name")))
                    .create();
            player.getInventory().setItem(CobraPlugin.get().getConfig().getInt("staffmode.item.vanish.slot"), vanish);

            player.sendMessage(CC.translate(CobraPlugin.get().getConfig().getString("command.staffmode.enabled")));
        }
    }
}
