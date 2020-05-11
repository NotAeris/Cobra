package me.notaeris.cobra.profile;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

@Getter
public class Profile {

    private final UUID uuid;

    public Profile(UUID uuid) {
        this.uuid = uuid;
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(this.uuid);
    }
}
