package me.notaeris.cobra.profile;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Profile {

    private final UUID uuid;

    /**
     * Constructor for Profile
     *
     * @param uuid uuid of player
     */
    public Profile(UUID uuid) {
        this.uuid = uuid;
    }

    /**
     * Get a player
     *
     * @return the player
     */
    public Player getPlayer() {
        return Bukkit.getPlayer(this.uuid);
    }
}
