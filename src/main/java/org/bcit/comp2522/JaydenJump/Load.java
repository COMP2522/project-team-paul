package org.bcit.comp2522.JaydenJump;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;

public class Load {

  MongoDatabase database;

  public Load() {
    ConnectionString connectionString = new ConnectionString
        ("mongodb+srv://comp2522Lab12:6D3J5eKnlsz9Rvuw@comp2522-lab12.fgxnetw.mongodb.net/?retryWrites=true&w=majority");
    MongoClientSettings settings = MongoClientSettings.builder()
        .applyConnectionString(connectionString)
        .serverApi(ServerApi.builder()
            .version(ServerApiVersion.V1)
            .build())
        .build();
    MongoClient mongoClient = MongoClients.create(settings);
    database = mongoClient.getDatabase("test");
  }

  private ArrayList<String> saves;

  public void load(String saveID) {
    new Thread(() -> {
      Document find = database.getCollection("saves").find(eq("SaveID", saveID)).first();
      int lives = find.getInteger("Lives");
      int score = find.getInteger("Score");
      int unlocked = find.getInteger("Unlocked");
      Player.setLives(lives);
      Player.setScore(score);
      Player.setUnlocked(unlocked);
    }
    ).start();
  }

  public void save(String saveID) {
    int lives = Player.getLives();
    int score = Player.getScore();
    int unlocked = Player.getUnlocked();

    Document doc = new Document();
    doc.append("SaveID", saveID);
    doc.append("Lives", lives);
    doc.append("Score", score);
    doc.append("Unlocked", unlocked);
    new Thread(() -> database.getCollection("saves").insertOne(doc)).start();
  }


}
