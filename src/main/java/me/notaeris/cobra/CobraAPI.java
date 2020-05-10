package me.notaeris.cobra;

import me.notaeris.cobra.command.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class CobraAPI {

    public boolean getStaffMode(Player player) {
        return StaffModeCommand.staffmode.contains(player);
    }

    public boolean getFreeze(Entity entity) {
        return FreezeCommand.frozen.contains(entity);
    }

    public boolean getChat() {
        return !ChatCommands.chatToggled;
    }

    public boolean getTogglePm(Player player) {
        return TogglePmCommand.togglepm.contains(player);
    }

    public boolean getMessageSounds(Player player) {
        return SoundsCommand.sounds.contains(player);
    }

    public boolean getVanish(Player player) {
        return VanishCommand.vanish.contains(player);
    }

    public void setVanished(Player player, boolean isVanished) {
        if(isVanished) {
            VanishCommand.vanish.add(player);
            for(Player players : Bukkit.getOnlinePlayers()) {
                if(players.hasPermission("cobra.command.vanish")) {
                    players.showPlayer(player);
                } else {
                    players.hidePlayer(player);
                }
            }
        }
        if(!isVanished) {
            VanishCommand.vanish.remove(player);
            for(Player players : Bukkit.getOnlinePlayers()) {
                players.showPlayer(player);
            }
        }
    }
}
