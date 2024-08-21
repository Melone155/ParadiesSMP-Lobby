package de.melone.Lobby.ulti;

import com.mongodb.MongoException;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.internal.connection.Connection;
import de.melone.Lobby.LobbyMain;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import static com.mongodb.client.model.Filters.eq;

public class NoSQL {

    private static MongoCollection<Document> collection;
    private static MongoClient mongoClient;

    public static String uri = "mongodb://" + LobbyMain.Username + ":" + LobbyMain.Password + "@" + LobbyMain.Host + ":" + LobbyMain.Port + "/?authMechanism=SCRAM-SHA-1&authSource=" + LobbyMain.Database;


    public static void Connection() {

        mongoClient = MongoClients.create(uri);
        MongoDatabase database = mongoClient.getDatabase(LobbyMain.Database);
        collection = database.getCollection(LobbyMain.Collection);

    }

    public static boolean isMongoDBConnected(MongoClient mongoClient) {
        try {
            mongoClient.listDatabaseNames();

            return true;
        } catch (MongoException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static void Create(Player player){

        if (!isMongoDBConnected(mongoClient)) {
            Bukkit.getConsoleSender().sendMessage("§4Die Verbindung zur MongoDB-Datenbank ist fehlgeschlagen.");
            player.sendMessage(MiniMessage.miniMessage().deserialize(LobbyMain.prefix +  "Fehler: 425 Bitte Kontaktieren sie das Team"));
        } else {
            System.out.println("Erfolgreich mit der MongoDB-Datenbank verbunden.");

            Document doc = collection.find(eq("_id", player.getUniqueId().toString())).first();

            if (doc == null){
                InsertOneResult result = collection.insertOne(new Document()
                        .append("_id", player.getUniqueId().toString())
                        .append("name", player.getName().toString())
                        .append("Lobby", 0));
            }
        }
    }

    public static void Update(Player player){

        Document query = new Document().append("_id",  player.getUniqueId().toString());

        Bson updates = Updates.combine(
                Updates.set("Lobby", player.getPlayer().getStatistic(Statistic.PLAY_ONE_MINUTE)));

        UpdateOptions options = new UpdateOptions().upsert(true);

        UpdateResult result = collection.updateOne(query, updates, options);

    }

    public static String getPlayTime(Player player){
        Bson filter = Filters.eq("_id", player.getUniqueId().toString());
        FindIterable<Document> result = collection.find(filter);
        Integer output = null;
        String time = null;

        if (result.iterator().hasNext()) {
            Document document = result.first();
            int lobby = document.getInteger("Lobby");

            int ticks = lobby;
            int sec = ticks/20;
            int min = sec/60;

                int hours = min/60;
                output = hours;

        }
        return output.toString();
    }
}
