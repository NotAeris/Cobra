package me.notaeris.cobra;

import me.notaeris.cobra.command.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CobraAPI {

    private final ArrayList<Player> staffmode = new ArrayList<>();
    private final ArrayList<Entity> freeze = new ArrayList<>();
    private final ArrayList<Player> vanish = new ArrayList<>();
    private final ArrayList<Player> togglepm = new ArrayList<>();
    private final ArrayList<Player> sounds = new ArrayList<>();
    private final ArrayList<Player> fly = new ArrayList<>();

    private boolean chatToggled;

    public boolean getStaffMode(Player player) {
        return this.staffmode.contains(player);
    }

    public void setStaffModeEnabled(Player player) {
        this.staffmode.add(player);
    }

    public void setStaffModeDisabled(Player player) {
        this.staffmode.remove(player);
    }

    public boolean getFreeze(Entity entity) {
        return this.freeze.contains(entity);
    }

    public void setFreezeEnabled(Player player) {
        this.freeze.add(player);
    }

    public void setFreezeDisabled(Player player) {
        this.freeze.remove(player);
    }

    public boolean getChat() {
        return !this.chatToggled;
    }

    public void setChatEnabled() {
        this.chatToggled = true;
    }

    public void setChatDisabled() {
        this.chatToggled = false;
    }

    public boolean getTogglePm(Player player) {
        return this.togglepm.contains(player);
    }

    public void setTogglepmEnabled(Player player) {
        this.togglepm.add(player);
    }

    public void setTogglepmDisabled(Player player) {
        this.togglepm.remove(player);
    }

    public boolean getSounds(Player player) {
        return this.sounds.contains(player);
    }

    public void setSoundsEnabled(Player player) {
        this.sounds.add(player);
    }

    public void setSoundsDisabled(Player player) {
        this.sounds.remove(player);
    }

    public boolean getVanish(Player player) {
        return this.vanish.contains(player);
    }

    public void setVanishEnabled(Player player) {
        this.vanish.add(player);
    }

    public void setVanishDisabled(Player player) {
        this.vanish.remove(player);
    }

    public void setVanished(Player player, boolean isVanished) {
        if(isVanished) {
            this.vanish.add(player);
            for(Player players : Bukkit.getOnlinePlayers()) {
                if(players.hasPermission("cobra.command.vanish")) {
                    players.showPlayer(player);
                } else {
                    players.hidePlayer(player);
                }
            }
        }
        if(!isVanished) {
            this.vanish.remove(player);
            for(Player players : Bukkit.getOnlinePlayers()) {
                players.showPlayer(player);
            }
        }
    }

    public boolean getFly(Player player) {
        return this.fly.contains(player);
    }

    public void setFlyEnabled(Player player) {
        this.fly.add(player);
    }

    public void setFlyDisabled(Player player) {
        this.fly.remove(player);
    }
}