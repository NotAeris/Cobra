package me.notaeris.cobra.profile;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

@Getter @Setter
public class Profile {

    private final UUID uuid;

    /**
     * main constructor
     *
     * @param uuid the uuid of the player
     */
    public Profile(UUID uuid) {
        this.uuid = uuid;
    }

    /**
     * get a player
     * @return the player
     */
    public Player getPlayer() {
        return Bukkit.getPlayer(this.uuid);
    }
}
