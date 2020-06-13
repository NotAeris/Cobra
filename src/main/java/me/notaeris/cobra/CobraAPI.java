package me.notaeris.cobra;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

@Getter @Setter
public class CobraAPI {

    private final Set<Player> staffmode = new HashSet<>();
    private final Set<Player> freeze = new HashSet<>();
    private final Set<Player> vanish = new HashSet<>();
    private final Set<Player> togglepm = new HashSet<>();
    private final Set<Player> sounds = new HashSet<>();
    private final Set<Player> fly = new HashSet<>();
    public final Set<Player> staffchat = new HashSet<>();

    private boolean chat;
    private boolean requests;
    private boolean reports;

    /**
     * Check if staffmode is toggled
     *
     * @param player the player
     * @return the hashset
     */
    public boolean getStaffMode(Player player) {
        return this.staffmode.contains(player);
    }

    /**
     * Set staffmode enabled
     *
     * @param player the player
     */
    public void setStaffModeEnabled(Player player) {
        this.staffmode.add(player);
    }

    /**
     * Set staffmode disabled
     *
     * @param player the player
     */
    public void setStaffModeDisabled(Player player) {
        this.staffmode.remove(player);
    }

    /**
     * Check if frozen is toggled
     *
     * @param entity the entity
     * @return the hashset
     */
    public boolean getFreeze(Entity entity) {
        return this.freeze.contains(entity);
    }

    /**
     * Set the player frozen
     *
     * @param player the player
     */
    public void setFreezeEnabled(Player player) {
        this.freeze.add(player);
    }

    /**
     * Set the player unfrozen
     *
     * @param player the player
     */
    public void setFreezeDisabled(Player player) {
        this.freeze.remove(player);
    }

    /**
     * Check if togglepm is toggled
     *
     * @param player the player
     * @return the hashset
     */
    public boolean getTogglePm(Player player) {
        return this.togglepm.contains(player);
    }

    /**
     * Set togglepm enabled
     *
     * @param player the player
     */
    public void setTogglepmEnabled(Player player) {
        this.togglepm.add(player);
    }

    /**
     * Set togglepm disabled
     *
     * @param player the player
     */
    public void setTogglepmDisabled(Player player) {
        this.togglepm.remove(player);
    }

    /**
     * Check if sounds are toggled
     *
     * @param player the player
     * @return the hashset
     */
    public boolean getSounds(Player player) {
        return this.sounds.contains(player);
    }

    /**
     * Set sounds enabled
     *
     * @param player the player
     */
    public void setSoundsEnabled(Player player) {
        this.sounds.add(player);
    }

    /**
     * Set sounds disabled
     *
     * @param player the player
     */
    public void setSoundsDisabled(Player player) {
        this.sounds.remove(player);
    }

    /**
     * Check if vanish is toggled
     *
     * @param player the player
     * @return the hashset
     */
    public boolean getVanish(Player player) {
        return this.vanish.contains(player);
    }

    /**
     * Set vanish enabled
     *
     * @param player the player
     */
    public void setVanishEnabled(Player player) {
        this.vanish.add(player);
    }

    /**
     * Set vanish disabled
     *
     * @param player the player
     */
    public void setVanishDisabled(Player player) {
        this.vanish.remove(player);
    }

    /**
     * Set the player vanished
     *
     * @param player the player
     * @param isVanished the boolean
     */
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

    /**
     * Check if fly is toggled
     *
     * @param player the player
     * @return the hashset
     */
    public boolean getFly(Player player) {
        return this.fly.contains(player);
    }

    /**
     * Set fly enabled
     *
     * @param player the player
     */
    public void setFlyEnabled(Player player) {
        this.fly.add(player);
    }

    /**
     * Set fly disabled
     *
     * @param player the player
     */
    public void setFlyDisabled(Player player) {
        this.fly.remove(player);
    }

    /**
     * Get the staffchat
     *
     * @param player the player
     * @return the hashset
     */
    public boolean getStaffChat(Player player) {
        return this.staffchat.contains(player);
    }

    /**
     * Set staffchat enabled
     *
     * @param player the player
     */
    public void setStaffChatEnabled(Player player) {
        this.staffchat.add(player);
    }

    /**
     * Set staffchat disabled
     *
     * @param player the player
     */
    public void setStaffChatDisabled(Player player) {
        this.staffchat.remove(player);
    }

    /**
     * Check if the chat is toggled
     *
     * @return the boolean
     */
    public boolean getChat() {
        return !this.chat;
    }

    /**
     * Set the chat true/false
     *
     * @param chat the boolean
     */
    public void setChat(boolean chat) {
        this.chat = chat;
    }

    /**
     * Check if the requests is toggled
     *
     * @return the boolean
     */
    public boolean isRequests() {
        return !this.requests;
    }

    /**
     * Set the requests true/false
     *
     * @param requests the boolean
     */
    public void setRequests(boolean requests) {
        this.requests = requests;
    }

    /**
     * Get the reports
     *
     * @return the boolean
     */
    public boolean getReports() {
        return !this.reports;
    }

    /**
     * Set the reports true/false
     *
     * @param reports the boolean
     */
    public void setReports(boolean reports) {
        this.reports = reports;
    }
}