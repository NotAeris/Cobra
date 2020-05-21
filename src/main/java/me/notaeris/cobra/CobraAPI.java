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
    private final Set<Player> staffchat = new HashSet<>();

    private boolean chat;
    private boolean requests;
    private boolean reports;

    /**
     * check if staffmode is toggled
     *
     * @param player the player
     * @return arraylist
     */
    public boolean getStaffMode(Player player) {
        return this.staffmode.contains(player);
    }

    /**
     * set staffmode enabled
     *
     * @param player the player
     */
    public void setStaffModeEnabled(Player player) {
        this.staffmode.add(player);
    }

    /**
     * set staffmode disabled
     *
     * @param player the player
     */
    public void setStaffModeDisabled(Player player) {
        this.staffmode.remove(player);
    }

    /**
     * check if frozen is toggled
     *
     * @param entity the entity
     * @return arraylist
     */
    public boolean getFreeze(Entity entity) {
        return this.freeze.contains(entity);
    }

    /**
     * set the player frozen
     *
     * @param player the player
     */
    public void setFreezeEnabled(Player player) {
        this.freeze.add(player);
    }

    /**
     * set the player unfrozen
     *
     * @param player the player
     */
    public void setFreezeDisabled(Player player) {
        this.freeze.remove(player);
    }

    /**
     * check if togglepm is toggled
     *
     * @param player the player
     * @return arraylist
     */
    public boolean getTogglePm(Player player) {
        return this.togglepm.contains(player);
    }

    /**
     * set togglepm enabled
     *
     * @param player the player
     */
    public void setTogglepmEnabled(Player player) {
        this.togglepm.add(player);
    }

    /**
     * set togglepm disabled
     *
     * @param player the player
     */
    public void setTogglepmDisabled(Player player) {
        this.togglepm.remove(player);
    }

    /**
     * check if sounds are toggled
     *
     * @param player the player
     * @return arraylist
     */
    public boolean getSounds(Player player) {
        return this.sounds.contains(player);
    }

    /**
     * set sounds enabled
     *
     * @param player the player
     */
    public void setSoundsEnabled(Player player) {
        this.sounds.add(player);
    }

    /**
     * set sounds disabled
     *
     * @param player the player
     */
    public void setSoundsDisabled(Player player) {
        this.sounds.remove(player);
    }

    /**
     * check if vanished is toggled
     *
     * @param player the player
     * @return arraylist
     */
    public boolean getVanish(Player player) {
        return this.vanish.contains(player);
    }

    /**
     * set vanish enabled
     *
     * @param player the player
     */
    public void setVanishEnabled(Player player) {
        this.vanish.add(player);
    }

    /**
     * set vanish disabled
     *
     * @param player the player
     */
    public void setVanishDisabled(Player player) {
        this.vanish.remove(player);
    }

    /**
     * set the player vanished
     *
     * @param player the player
     * @param isVanished boolean
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
     * check if fly is toggled
     *
     * @param player the player
     * @return arraylist
     */
    public boolean getFly(Player player) {
        return this.fly.contains(player);
    }

    /**
     * set fly enabled
     *
     * @param player the player
     */
    public void setFlyEnabled(Player player) {
        this.fly.add(player);
    }

    /**
     * set fly disabled
     *
     * @param player the player
     */
    public void setFlyDisabled(Player player) {
        this.fly.remove(player);
    }

    /**
     * get the staffchat
     *
     * @param player the player
     * @return the staffchat
     */
    public boolean getStaffChat(Player player) {
        return this.staffchat.contains(player);
    }

    /**
     * set staffchat enabled
     *
     * @param player the player
     */
    public void setStaffChatEnabled(Player player) {
        this.staffchat.add(player);
    }

    /**
     * set staffchat disabled
     *
     * @param player the player
     */
    public void setStaffChatDisabled(Player player) {
        this.staffchat.remove(player);
    }

    /**
     * check if the chat is toggled
     *
     * @return boolean
     */
    public boolean getChat() {
        return !this.chat;
    }

    /**
     * set the chat true/false
     *
     * @param chat the chat
     */
    public void setChat(boolean chat) {
        this.chat = chat;
    }

    /**
     * check if the requests is toggled
     *
     * @return the requests
     */
    public boolean isRequests() {
        return !this.requests;
    }

    /**
     * set the requests true/false
     *
     * @param requests the requests
     */
    public void setRequests(boolean requests) {
        this.requests = requests;
    }

    /**
     * get the reports
     *
     * @return the reports
     */
    public boolean getReports() {
        return !this.reports;
    }

    /**
     * set the reports true/false
     *
     * @param reports the reports
     */
    public void setReports(boolean reports) {
        this.reports = reports;
    }
}