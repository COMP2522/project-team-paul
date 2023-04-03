package org.bcit.comp2522.JaydenJump;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;

import processing.core.PImage;

/**
 * Represents a level in JaydenJump.
 *
 * @version 1.1
 * @author Maximillian Yong
 */
public class Level {

  /**
   * The time left in the level.
   */
  private volatile int time;

  /**
   * Unused at the moment.
   */
  private long start;

  /* Unused at the moment. */
  private long end;

  /**
   * The background image of the level.
   */
  private PImage background;

  /**
   * The level number. */
  private int levelNumber;

  /**
   * The speed of elements in the level.
   */
  private int speed;

  /**
   * The players score in the current level.
   */
  private int score;

  /**
   * Used for timer for the level.
   */
  private ScheduledExecutorService scheduledExecutorService;

  /**
   * Used for timer for the level.
   */
  private ScheduledFuture<?> scheduledFuture;

  /**
   *  Unused at the moment.
   */
  private String weather;

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
   * The power up speed.
   */
  private int powerUpSpeed;

  /**
   * The max coins.
   */
  private int maxCoins;

  /**
   * The coin speed.
   */
  private int coinSpeed;

  /**
   * The spawn rate.
   */
  private float spawnRate;

  /**
   * The max bosses.
   */
  private int maxBosses;

  /**
   * Level Constructor.
   * Reads the level details from a json file and sets the level details.
   *
   * playerSpeed : float
   * gravity : float
   * scrollSpeed : int
   * maxPlatform : int
   * platformSpeed : int
   * moveableSpeed : int
   * jumpThroughHeight : int
   * playerJumpHeight : int
   * maxPowerUps : int
   * powerUpSpeed : int
   * maxCoins : int
   * coinSpeed : int
   * spawnRate : float
   * maxBosses : int
   *
   * @param lvl the level number
   */
  public Level(int lvl) {
    // reads the level details from LevelDetails.json
    //System.out.println("Level " + lvl);
    String json = readFileAsString("LevelData/LevelDetails.json");
    //System.out.println(json);

    JSONObject obj = new JSONObject(json);
    JSONArray levels = obj.getJSONArray("levels");
    JSONObject level = levels.getJSONObject(lvl - 1);

    System.out.println(level.getInt("level"));
    playerSpeed = level.getFloat("playerSpeed");
    gravity = level.getFloat("gravity");
    scrollSpeed = level.getInt("scrollSpeed");
    maxPlatform = level.getInt("maxPlatform");
    platformSpeed = level.getInt("platformSpeed");
    moveableSpeed = level.getInt("moveableSpeed");
    jumpThroughHeight = level.getInt("jumpThroughHeight");
    playerJumpHeight = level.getInt("playerJumpHeight");
    maxPowerUps = level.getInt("maxPowerUps");
    powerUpSpeed = level.getInt("powerUpSpeed");
    maxCoins = level.getInt("maxCoins");
    coinSpeed = level.getInt("coinSpeed");
    spawnRate = level.getFloat("spawnRate");
    maxBosses = level.getInt("maxBosses");
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
   * Level Constructor.
   * Creates the level based on the level details.
   * TODO: loadLevel method that calls the constructor? details from json
   *
   * @param time        The time limit for the level
   * @param background  The background image for the level
   * @param levelNumber The level number
   * @param speed       The speed of the elements for the level
   * @param score       The player score for the level
   */
  public Level(int time, PImage background, int levelNumber, int speed, int score) {
    this.time = time;
    this.background = background;
    this.levelNumber = levelNumber;
    this.speed = speed;
    this.score = score;
    this.scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
  }

  /**
   *  Starts the timer for the level, updates time every second.
   *  TimerTask should be creating a new Thread to not hold up main.
   */
  public void startTime() {
    if (scheduledFuture == null || scheduledFuture.isCancelled()) {
      Runnable task = () -> {
        time--;
        if (time <= 0) {
          stopTime();
        }
      };
      scheduledFuture = scheduledExecutorService
          .scheduleAtFixedRate(task, 0, 1000, TimeUnit.MILLISECONDS);
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
   * Should be calling another class, unimplemented for now.
   */
  public void getWeather() {
    // TODO: 1 implement Weather.java to be called by this method
    // TODO: 2 modify the level based on weather details
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
   * Gets the levels background image.
   *
   * @return The background image (PImage)
   */
  public PImage getBackground() {
    return background;
  }

  /**
   * Sets the background image for the level.
   *
   * @param background The background image (PImage)
   */
  public void setBackground(PImage background) {
    this.background = background;
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
   * Sets the level number.
   *
   * @param levelNumber The level number (int)
   */
  public void setLevelNumber(int levelNumber) {
    this.levelNumber = levelNumber;
  }

  /**
   * Gets the current speed of the element for the level.
   *
   * @return The speed (int)
   */
  public int getSpeed() {
    return speed;
  }

  /**
   * Sets the speed of the elements in the level.
   *
   * @param speed The speed (int)
   */
  public void setSpeed(int speed) {
    this.speed = speed;
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
   * Gets the power up speed.
   *
   * @return The power up speed (int)
   */
  public int getPowerUpSpeed() {
    return powerUpSpeed;
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
   * Gets the coin speed.
   *
   * @return The coin speed (int)
   */
  public int getCoinSpeed() {
    return coinSpeed;
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
}
