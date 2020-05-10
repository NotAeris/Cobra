package me.notaeris.cobra.profile;

import lombok.Getter;
import me.notaeris.cobra.CobraPlugin;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

@Getter
public class Profile {

    private final UUID uuid;

    public Profile(UUID uuid) {
        this.uuid = uuid;

        this.load();
    }

    private void load() {
        Document document = CobraPlugin.get().getMongoDB().getProfiles(uuid);

        if(document == null) {
            this.save();
        }
    }

    private void save() {
        Document document = CobraPlugin.get().getMongoDB().getProfiles(uuid);

        if(document == null) {
            document = new Document();
        }

        document.put("uuid", this.uuid.toString());
        document.put("name", this.getPlayer().getName());

        CobraPlugin.get().getMongoDB().replaceOne(uuid, document);
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(this.uuid);
    }
}
