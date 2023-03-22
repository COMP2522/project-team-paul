package org.bcit.comp2522.JaydenJump;

import java.util.Timer;
import java.util.TimerTask;
import processing.core.PImage;

/**
 * Represents a level in JaydenJump.
 *
 * @version 1.0
 * @author Maximillian Yong
 */
public class Level {

  /* The time left in the level. */
  private int time;

  /* Unused at the moment. */
  private long start;

  /* Unused at the moment. */
  private long end;

  /* The background image of the level. */
  private PImage background;

  /* The level number. */
  private int levelNumber;

  /* The speed of elements in the level. */
  private int speed;

  /* The players score in the current level. */
  private int score;

  /* The timer for the level. */
  private Timer timer;

  /* Unused at the moment. */
  private String weather;

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
    this.timer = new Timer();
  }

  /**
   *  Starts the timer for the level, updated the time every second.
   *  TimerTask should be creating a new Thread to not hold up main.
   */
  public void startTime() {
    TimerTask task = new TimerTask() {
      @Override
      public void run() {
        time--;
        if (time <= 0) {
          stopTime();
        }
      }
    };

    timer.scheduleAtFixedRate(task, 0, 1000);
  }

  /**
   * Pauses the timer for the level.
   */
  public void stopTime() {
    timer.cancel();
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
}
