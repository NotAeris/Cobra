package me.notaeris.cobra.profile;

import com.mongodb.client.model.Filters;
import me.notaeris.cobra.CobraPlugin;
import org.bson.Document;
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

        this.load();
    }

    public void load() {
        Document document = CobraPlugin.get().getDb().getProfiles().find(Filters.eq("uuid", this.uuid.toString())).first();

        if(document == null) {
            this.save();
        } else {
            document.getString("uuid");
            document.getString("name");
        }
    }


    public void save() {
        Document document = new Document();

        document.put("uuid", this.uuid.toString());
        document.put("name", this.getPlayer().getName());

        CobraPlugin.get().getDb().saveProfiles(this.uuid, document);
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
