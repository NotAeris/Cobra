package me.notaeris.cobra.profile;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import lombok.Getter;
import me.notaeris.cobra.util.MongoDB;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

@Getter
public class Profile {

    private final UUID uuid;
    protected ProfileState state;

    public Profile(UUID uuid) {
        this.uuid = uuid;

        this.load();
    }

    private void load() {
        Document document = MongoDB.profiles.find(Filters.eq("uuid", this.uuid.toString())).first();

        if(document == null) {
            this.save();
        }
    }

    private void save() {
        Document document = MongoDB.profiles.find(Filters.eq("uuid", this.uuid.toString())).first();

        if(document == null) {
            document = new Document();
        }

        document.put("uuid", this.uuid.toString());
        document.put("name", this.getPlayer().getName());

        MongoDB.profiles.replaceOne(Filters.eq("uuid", this.uuid.toString()), document, new UpdateOptions().upsert(true));
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(this.uuid);
    }

    public void setState(ProfileState state) {
        this.state = state;
    }
}
