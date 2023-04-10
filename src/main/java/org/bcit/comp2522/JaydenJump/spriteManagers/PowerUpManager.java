package org.bcit.comp2522.JaydenJump.spriteManagers;

import java.util.ArrayList;
import java.util.Iterator;

import org.bcit.comp2522.JaydenJump.Game;
import org.bcit.comp2522.JaydenJump.Level;
import org.bcit.comp2522.JaydenJump.gameUI.MenuManager;
import org.bcit.comp2522.JaydenJump.sprites.*;
import processing.core.PApplet;
import processing.core.PImage;


/**
 * The PowerUpManager class manages all the PowerUps that appear in the game screen.
 * This includes how many PowerUps spawn, which specific PowerUps are spawned.
 * As well as activating the PowerUps when they collide with the player.
 *
 * @version 1.0
 *
 * @author Hyuk Park
 *
 * @since 2023-03-20
 */

public class PowerUpManager {
  /**
   * Instance of PowerUpManager class.
   */
  private static PowerUpManager instance;

  /**
   * ArrayList of PowerUps that appear in the game.
   */
  private final ArrayList<PowerUp> powerups;

  /**
   * The game screen.
   */
  private final PApplet sketch;

  /**
   * The image of power up.
   */
  private PImage[] image;

  /**
   * The player in the game.
   */
  Player player;

  /**
   * The level of the game.
   */
  private Level level;

  /**
   * Number of PowerUps that spawn in at the start of the game.
   */
  private static final int POWERUPSTARTING = 1;

  /**
   * The game.
   */
  private Game game;

  /**
   * the current game level.
   */
  private int levelNum;

  /**
   * Constructor of the PowerUpManager class.
   *
   * @param level of the game
   *
   * @param powerUpImg the images of all the power ups
   *
   * @param game the game screen
   */
  private PowerUpManager(Level level, PImage[] powerUpImg, Game game) {
    this.sketch = MenuManager.getInstance();
    powerups = new ArrayList<>();
    this.player = Player.getInstance();
    this.image = powerUpImg;
    this.game = game;
    this.level = level;
    this.levelNum = level.getLevelNumber();
  }

  /**
   * Retrieves an instance of the PowerUpManager class and enforces the
   * singleton design pattern.
   *
   * @param level of the game
   *
   * @param powerUpImg the images of the power ups
   *
   * @param game the game screen
   *
   * @return PowerUpManager instance in the game
   */
  public static PowerUpManager getInstance(Level level, PImage[] powerUpImg, Game game) {
    if (instance == null || instance.levelNum != level.getLevelNumber()) {
      instance = new PowerUpManager(level, powerUpImg, game);
    }
    return instance;
  }

  /**
   * Reassigns the image associated with the PowerUpManger class.
   *
   * @param image that replaces the current image
   *
   */
  public void setImage(PImage[] image) {
    this.image = image;
  }

  /**
   * Returns the array that contains all the PowerUps in the game.
   *
   * @return ArrayList containing all PowerUps in the game
   */
  public ArrayList<PowerUp> getPowerups() {
    return powerups;
  }

  /**
   * Generates PowerUps in the game screen at the start of the game.
   */
  public void generateStartPowerUps() {
    float y = generateRandomPosition();
    for (int i = 0; i < POWERUPSTARTING; i++) {
      float x = generateRandomPosition();
      powerups.add(createRandomPowerUp(x, y, level.getxSpeedCoinPowerUp(), level.getySpeedCoinPowerUp(), image));
      y += level.getSpawnHeight();
    }
  }

  /**
   * Generates PowerUps in the game screen continuously throughout the game.
   */
  public void generatePowerUps() {
    float y = 0;
    while (powerups.size() < level.getMaxPowerUps()) {
      float x = generateRandomPosition();
      PowerUp newPowerUp = createRandomPowerUp(x, y, level.getxSpeedCoinPowerUp(), level.getySpeedCoinPowerUp(), image);
      powerups.add(newPowerUp);
      y += level.getSpawnHeight();
    }
  }

  /**
   * Creates random coordinates for the x and y values of the PowerUps
   * within the boundary of the game screen.
   *
   * @return random coordinate within game borders
   */
  public float generateRandomPosition() {
    return sketch.random(sketch.width - level.getPowerUpSize());
  }

  /**
   * Updates the position of the PowerUps and removes PowerUps that are no longer
   * on the game screen.
   */
  public void updateAndDrawPowerUps() {
    for (int i = powerups.size() - 1; i >= 0; i--) {
      PowerUp powerup = powerups.get(i);
      if (!powerup.isOnScreen()) {
        powerups.remove(i);
      }
    }

    for (PowerUp powerup : powerups) {
      powerup.update();
      powerup.draw();
    }
  }

  /**
   * Checks if the PowerUps collides with player and activates the PowerUp
   * on collision. PowerUps that collide with player are also removed from
   * the ArrayList of PowerUps in the game.
   *
   */
  public void checkCollision() {
    Iterator<PowerUp> powerUpIterator = powerups.iterator();
    while (powerUpIterator.hasNext()) {
      PowerUp powerup = powerUpIterator.next();
      if (powerup.collides(this.player)) {
        powerup.activate();
        powerUpIterator.remove();
      }
    }
  }

  /**
   * Creates random PowerUps in the game, including the Tire,
   * JetPack, and ExtraLife PowerUp.
   *
   * @param xpos the X-position of the PowerUp
   *
   * @param ypos the Y-position of the PowerUp
   *
   * @param vx the x-velocity of the PowerUp
   *
   * @param vy the y-velocity of the PowerUp
   *
   * @param image the image of the PowerUp
   *
   * @return PowerUp that is randomly generated (Tire, ExtraLife, or JetPack)
   */
  public PowerUp createRandomPowerUp(float xpos, float ypos, float vx, float vy,
                                      PImage[] image) {
    int randomInt = (int) (Math.random() * level.getPowerUpTypes());

    // Create and return an instance of a randomly selected subclass
    if (randomInt == 0) {
      return new Tire(level, xpos, ypos, vx, vy, image[2]);
    } else if (randomInt == 1) {
      return new JetPack(level, xpos, ypos, vx, vy, image[1]);
    } else {
      return new ExtraLife(level, xpos, ypos, vx, vy, image[0], game);
    }
  }
}
