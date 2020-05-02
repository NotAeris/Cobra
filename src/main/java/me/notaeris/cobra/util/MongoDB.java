package me.notaeris.cobra.util;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import me.notaeris.cobra.CobraPlugin;
import org.bson.Document;

public class MongoDB {

    public static MongoCollection<Document> profiles;

    public MongoDB() {
        MongoClient client = new MongoClient(CobraPlugin.get().getConfig().getString("mongo.host"), CobraPlugin.get().getConfig().getInt("mongo.port"));
        MongoDatabase database = client.getDatabase(CobraPlugin.get().getConfig().getString("mongo.database.name"));

        profiles = database.getCollection(CobraPlugin.get().getConfig().getString("mongo.database.collection"));
    }
}

