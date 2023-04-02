package org.bcit.comp2522.JaydenJump;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;


/**
 * Class to load and save game data.
 *
 * @author Maximillian Yong
 * @version 1.1
 */
public class Load {

  /** The database. */
  static MongoDatabase database;

  /**
   * Constructor for Load.
   */
  public Load() {
    //TODO: Replace with connection string.

    ConnectionString connectionString = new ConnectionString(
        "mongodb+srv://comp2522Lab12:6D3J5eKnlsz9Rvuw@comp2522-lab12.fgxnetw.mongodb.net"
            + "/?retryWrites=true&w=majority");
    MongoClientSettings settings = MongoClientSettings.builder()
        .applyConnectionString(connectionString)
        .serverApi(ServerApi.builder()
            .version(ServerApiVersion.V1)
            .build())
        .build();
    MongoClient mongoClient = MongoClients.create(settings);
    database = mongoClient.getDatabase("test");
  }

  /**
   * Loads the game data.
   *
   * @param saveId the save ID
   */
  public void load(String saveId) {
    Document find = database.getCollection("saves").find(eq("SaveID", saveId)).first();
    if (find != null) {
      int lives = find.getInteger("Lives");
      int score = find.getInteger("Score");
      int unlocked = find.getInteger("Unlocked");
      Player.setLives(lives);
      Player.setScore(score);
      Player.setUnlocked(unlocked);
    } else {
      Player.setLives(3);
      Player.setScore(0);
      Player.setUnlocked(1);
    }
  }

  /**
   * Saves the game data.
   *
   * @param saveId the save ID
   */
  public void save(String saveId) {
    int lives = Player.getLives();
    int score = Player.getScore();
    int unlocked = Player.getUnlocked();

    Document updateFields = new Document();
    updateFields.append("Lives", lives);
    updateFields.append("Score", score);
    updateFields.append("Unlocked", unlocked);

    Document updateQuery = new Document("$set", updateFields);

    new Thread(() -> database.getCollection("saves")
        .updateOne(eq("SaveID", saveId), updateQuery, new UpdateOptions().upsert(true))).start();
  }

  /**
   * Gets the leaderboard for the given difficulty.
   * 1 = easy, 2 = medium, 3 = hard
   * Returns an array of strings with the format:
   * "Name: Score"
   *
   * @return the leaderboard
   */
  public String[] getLeaderboard() {
    List<Document> leaderboard = database.getCollection("leaderboard")
        .find()
        .sort(new Document("Score", -1))
        .into(new ArrayList<>());

    // Format the leaderboard into an array of strings
    String[] result = new String[leaderboard.size()];
    for (int i = 0; i < leaderboard.size(); i++) {
      Document entry = leaderboard.get(i);
      result[i] = entry.getString("Name") + ":" + entry.getInteger("Score") + ":" + entry.getInteger("Difficulty");
    }
    return result;
  }

  /**
   * Checks the leaderboard and updates it if the score is higher than the lowest score.
   * Top 10 scores are kept.
   * 1 = easy, 2 = medium, 3 = hard
   *
   * @param name the name
   * @param score the score
   * @param difficulty the difficulty easy, medium, hard
   */
  public static void updateLeaderboard(String name, int score, int difficulty) {
    new Thread(() -> {
      Document lowestScoreEntry = database.getCollection("leaderboard")
          .find(eq("Difficulty", difficulty))
          .sort(new Document("Score", 1))
          .first();

      Document newEntry = new Document();
      newEntry.append("Name", name);
      newEntry.append("Score", score);
      newEntry.append("Difficulty", difficulty);

      // Insert the new score
      database.getCollection("leaderboard").insertOne(newEntry);

      // Remove the lowest score if there are more than 10 entries
      long count = database.getCollection("leaderboard")
          .countDocuments(eq("Difficulty", difficulty));
      if (count > 10) {
        database.getCollection("leaderboard")
            .deleteOne(eq("_id", lowestScoreEntry.getObjectId("_id")));
      }
    }).start();
  }

}
