package org.bcit.comp2522.JaydenJump;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Represents a level in JaydenJump.
 *
 * @version 1.1
 * @author Maximillian Yong
 */
public class Level {

  /**
   * The instance of the level.
   */
  private static Level instance;

  /**
   * Milliseconds in a second.
   */
  private static final int Second = 1000;

  /**
   * Start time for the level.
   */
  private volatile int time;

  /**
   * The level number. */
  private int levelNumber;

  /**
   * The players score in the current level.
   */
  private int score;

  /**
   * Used for timer for the level.
   */
  private ScheduledExecutorService scheduledExecutorService
      = Executors.newSingleThreadScheduledExecutor();

  /**
   * Used for timer for the level.
   */
  private ScheduledFuture<?> scheduledFuture;

  /**
   * The player speed.
   */
  private float playerSpeed;

  /**
   * The gravity.
   */
  private float gravity;

  /**
   * The max platforms.
   */
  private int scrollSpeed;

  /**
   * The max platforms.
   */
  private int maxPlatform;

  /**
   * The platform speed.
   */
  private int platformSpeed;

  /**
   * The moveable speed.
   */
  private int moveableSpeed;

  /**
   * The jump through height.
   */
  private int jumpThroughHeight;

  /**
   * The player jump height.
   */
  private int playerJumpHeight;

  /**
   * The max power ups.
   */
  private int maxPowerUps;


  /**
   * The max coins.
   */
  private int maxCoins;

  /**
   * The spawn rate.
   */
  private float spawnRate;

  /**
   * The max bosses.
   */
  private int maxBosses;

  /**
   * The amount that increases the player's velocity.
   */
  private int tireBoostHeight;

  /**
   * The amount of time the jetpack lasts.
   */
  private int jetPackDuration;

  /**
   * The amount that the jetpack increases the player's velocity.
   */
  private int jetPackBoostVelocity;

  /**
   * The amount of power ups that exist in the game.
   */
  private int powerUpTypes;

  /**
   * The height that the power ups spawn at.
   */
  private int spawnHeight;

  /**
   * The horizontal speed of the Coin/PowerUp.
   */
  private int xSpeedCoinPowerUp;


  /**
   * The vertical speed of the Coin/PowerUp.
   */
  private int ySpeedCoinPowerUp;

  /**
   * Size of the Coin sprite.
   */
  private int coinSize;

  /**
   * Size of the PowerUp sprite.
   */
  private int powerUpSize;

  private int currentScore;

  private int localHighScore;

  /**
   * Level Constructor.
   *
   * @param lvl the level number
   */
  private Level(int lvl) {
    loadLevel(lvl);
  }

  /**
   * Gets the instance of the level.
   *
   * @param lvl the level number
   * @return the instance of the level
   */
  public static Level getInstance(int lvl) {
    if (instance == null) {
      instance = new Level(lvl);
    }
    instance.loadLevel(lvl);
    return instance;
  }

  /**
   * Loads the level details from the json file.
   *
   * @param lvl the level number
   */
  private void loadLevel(int lvl) {
    // reads the level details from LevelDetails.json
    String json = readFileAsString("LevelData/LevelDetails.json");

    JSONObject obj = new JSONObject(json);
    JSONArray levels = obj.getJSONArray("levels");
    JSONObject level = levels.getJSONObject(lvl - 1);

    this.levelNumber = lvl;
    this.time = 0;
    this.currentScore = 0;
    playerSpeed = level.getFloat("playerSpeed");
    gravity = level.getFloat("gravity");
    scrollSpeed = level.getInt("scrollSpeed");
    maxPlatform = level.getInt("maxPlatform");
    platformSpeed = level.getInt("platformSpeed");
    moveableSpeed = level.getInt("moveableSpeed");
    jumpThroughHeight = level.getInt("jumpThroughHeight");
    playerJumpHeight = level.getInt("playerJumpHeight");
    maxPowerUps = level.getInt("maxPowerUps");
    maxCoins = level.getInt("maxCoins");
    spawnRate = level.getFloat("spawnRate");
    maxBosses = level.getInt("maxBosses");
    tireBoostHeight = level.getInt("tireBoostHeight");
    jetPackDuration = level.getInt("jetPackDuration");
    jetPackBoostVelocity = level.getInt("jetPackBoostVelocity");
    powerUpTypes = level.getInt("powerUpTypes");
    spawnHeight = level.getInt("spawnHeight");
    xSpeedCoinPowerUp = level.getInt("xSpeedCoinPowerUp");
    ySpeedCoinPowerUp = level.getInt("ySpeedCoinPowerUp");
    coinSize = level.getInt("coinSize");
    powerUpSize = level.getInt("powerUpSize");
    localHighScore = level.optInt("localHighScore", 0);
  }

  /**
   * Reads the file as a string.
   * Doesn't seem to work without this.
   *
   * @param filePath the file path
   * @return the string
   */
  private String readFileAsString(String filePath) {
    StringBuilder contentBuilder = new StringBuilder();
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String line;
      while ((line = br.readLine()) != null) {
        contentBuilder.append(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return contentBuilder.toString();
  }

  /**
   *  Starts the timer for the level, updates time every second.
   *  TimerTask should be creating a new Thread to not hold up main.
   */
  public void startTime() {
    if (scheduledFuture == null || scheduledFuture.isCancelled()) {
      Runnable task = () -> {
        time++;
        this.currentScore = Game.highscore;
        if (currentScore > localHighScore) {
          localHighScore = currentScore;
          saveLocalHighScore();
        }
      };
      scheduledFuture = scheduledExecutorService
          .scheduleAtFixedRate(task, 0, Second, TimeUnit.MILLISECONDS);
    }
  }

  /**
   * Pauses the timer for the level.
   */
  public void stopTime() {
    if (scheduledFuture != null) {
      scheduledFuture.cancel(false);
    }
  }

  /**
   * Gets the time left int the level.
   *
   * @return The time left (int).
   */
  public int getTime() {
    return time;
  }

  /**
   * Sets the time left in the level.
   *
   * @param time The time left (int)
   */
  public void setTime(int time) {
    this.time = time;
  }

  /**
   * Updates the local high score to the JSON file.
   */
  public void saveLocalHighScore() {
    new Thread(() -> {
      String json = readFileAsString("LevelData/LevelDetails.json");

      JSONObject obj = new JSONObject(json);
      JSONArray levels = obj.getJSONArray("levels");
      JSONObject level = levels.getJSONObject(levelNumber - 1);

      level.put("localHighScore", localHighScore);

      try (FileWriter fileWriter = new FileWriter("LevelData/LevelDetails.json")) {
        fileWriter.write(obj.toString());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }).start();

  }

  /**
   * Gets the current level number.
   *
   * @return The level number (int)
   */
  public int getLevelNumber() {
    return levelNumber;
  }

  /**
   * Gets the current player score for the level.
   *
   * @return The score (int)
   */
  public int getScore() {
    return score;
  }

  /**
   * Sets the player score for the level.
   *
   * @param score The players score (int)
   */
  public void setScore(int score) {
    this.score = score;
  }

  /**
   * Gets the player speed.
   *
   * @return The player speed (float)
   */
  public float getPlayerSpeed() {
    return playerSpeed;
  }

  /**
   * Gets the gravity.
   *
   * @return The gravity (float)
   */
  public float getGravity() {
    return gravity;
  }

  /**
   * Gets the scroll speed.
   *
   * @return The scroll speed (int)
   */
  public int getScrollSpeed() {
    return scrollSpeed;
  }

  /**
   * Gets the max platforms.
   *
   * @return The max platforms (int)
   */
  public int getMaxPlatform() {
    return maxPlatform;
  }

  /**
   * Gets the platform speed.
   *
   * @return The platform speed (int)
   */
  public int getPlatformSpeed() {
    return platformSpeed;
  }

  /**
   * Gets the moveable speed.
   *
   * @return The moveable speed (int)
   */
  public int getMoveableSpeed() {
    return moveableSpeed;
  }

  /**
   * Gets the max moveables.
   *
   * @return The max moveables (int)
   */
  public int getJumpThroughHeight() {
    return jumpThroughHeight;
  }

  /**
   * Gets the jump through height.
   *
   * @return The jump through height (int)
   */
  public int getPlayerJumpHeight() {
    return playerJumpHeight;
  }

  /**
   * Gets the player jump height.
   *
   * @return The player jump height (int)
   */
  public int getMaxPowerUps() {
    return maxPowerUps;
  }

  /**
   * Gets the max coins.
   *
   * @return The max coins (int)
   */
  public int getMaxCoins() {
    return maxCoins;
  }

  /**
   * Gets the spawn rate.
   *
   * @return The spawn rate (float)
   */
  public float getSpawnRate() {
    return spawnRate;
  }

  /**
   * Gets the max bosses.
   *
   * @return The max bosses (int)
   */
  public int getMaxBosses() {
    return maxBosses;
  }

  public int getTireBoostHeight() {
    return tireBoostHeight;
  }

  public int getJetPackDuration() {
    return jetPackDuration;
  }

  /**
   * Gets the jetpack boost velocity.
   *
   * @return The jetpack boost velocity (int)
   */
  public int getJetPackBoostVelocity() {
    return jetPackBoostVelocity;
  }

  /**
   * Gets the power up types.
   *
   * @return The power up types (int)
   */
  public int getPowerUpTypes() {
    return powerUpTypes;
  }

  /**
   * Gets the spawn height.
   *
   * @return The spawn height (int)
   */
  public int getSpawnHeight() {
    return spawnHeight;
  }

  /**
   * Gets the x speed coin power up.
   *
   * @return The x speed coin power up (int)
   */
  public int getxSpeedCoinPowerUp() {
    return xSpeedCoinPowerUp;
  }

  /**
   * Gets the y speed coin power up.
   *
   * @return The y speed coin power up (int)
   */
  public int getySpeedCoinPowerUp() {
    return ySpeedCoinPowerUp;
  }

  /**
   * Gets the Coin size.
   *
   * @return Coin size
   */
  public int getCoinSize() {
    return coinSize;
  }

  /**
   * Gets the PowerUp size.
   *
   * @return PowerUp size
   */
  public int getPowerUpSize() {
    return powerUpSize;
  }
}
