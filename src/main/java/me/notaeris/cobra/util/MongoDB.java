package me.notaeris.cobra.util;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import lombok.Getter;
import me.notaeris.cobra.CobraPlugin;
import org.bson.Document;

import java.util.UUID;

@Getter
public class MongoDB {

    private final MongoCollection<Document> profiles;

    /**
     * main constructor
     */
    public MongoDB() {
        MongoClient client = new MongoClient(CobraPlugin.get().getConfig().getString("mongo.host"), CobraPlugin.get().getConfig().getInt("mongo.port"));
        MongoDatabase database = client.getDatabase(CobraPlugin.get().getConfig().getString("mongo.database.name"));

        this.profiles = database.getCollection(CobraPlugin.get().getConfig().getString("mongo.database.collection.profiles"));
    }

    /**
     * get the profiles
     *
     * @param uuid uuid of profiles
     * @return the profiles
     */
    public Document getProfiles(UUID uuid) {
        return this.profiles.find(Filters.eq("uuid", uuid.toString())).first();
    }

    /**
     * save profiles
     *
     * @param uuid uuid of profiles
     * @param document document of profiles
     */
    public void saveProfiles(UUID uuid, Document document) {
        this.profiles.replaceOne(Filters.eq("uuid", uuid.toString()), document, new UpdateOptions().upsert(true));
    }
}
