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

    public MongoDB() {
        MongoClient client = new MongoClient(CobraPlugin.get().getConfig().getString("mongo.host"), CobraPlugin.get().getConfig().getInt("mongo.port"));
        MongoDatabase database = client.getDatabase(CobraPlugin.get().getConfig().getString("mongo.database.name"));

        this.profiles = database.getCollection(CobraPlugin.get().getConfig().getString("mongo.database.collection.profiles"));
    }

    /**
     * Get the profiles
     *
     * @return the profiles
     */
    public MongoCollection<Document> getProfiles() {
        return this.profiles;
    }

    /**
     * Save profiles
     *
     * @param uuid the uuid
     * @param document the document
     */
    public void saveProfiles(UUID uuid, Document document) {
        this.profiles.replaceOne(Filters.eq("uuid", uuid.toString()), document, new UpdateOptions().upsert(true));
    }
}
