package org.bcit.comp2522.JaydenJump;

import java.util.ArrayList;
import java.util.Iterator;
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
   * Maximum amount of PowerUps permitted in the game.
   */
  private final int maxPowerUps;

  /**
   * The game screen.
   */
  private final PApplet sketch;

  /**
   * Speed of power up as it moves down on game screen.
   */
  private final int powerUpSpeed;

  /**
   * The image of power up.
   */
  private PImage[] image;

  /**
   * The player in the game.
   */
  Player player;

  /**
   * Number of PowerUps that spawn in at the start of the game.
   */
  private static final int POWERUPSTARTING = 1;

  /**
   * Constructor of the PowerUpManager class.
   *
   * @param maxPowerUps the maximum amount of PowerUps permitted in the game
   *
   * @param powerUpSpeed the speed of the PowerUp as it moves down the game screen
   */
  private PowerUpManager(int maxPowerUps, int powerUpSpeed,
                         PImage[] powerUpImg) {
    this.sketch = MenuManager.getInstance();
    this.maxPowerUps = maxPowerUps;
    this.powerUpSpeed = powerUpSpeed;
    powerups = new ArrayList<>();
    this.player = Player.getInstance();
    this.image = powerUpImg;
  }

  /**
   * Retrieves an instance of the PowerUpManager class and enforces the
   * singleton design pattern.
   *
   * @param maxPowerUps the maximum amount of PowerUps permitted in the game
   *
   * @param powerUpSpeed the speed of the PowerUps as they move down the screen
   *
   * @return PowerUpManager instance in the game
   */
  public static PowerUpManager getInstance(int maxPowerUps, int powerUpSpeed, PImage[] powerUpImg) {
    if (instance == null) {
      instance = new PowerUpManager(maxPowerUps, powerUpSpeed, powerUpImg);
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
    float y = sketch.random(sketch.height - PowerUp.getPowerupSize());
    for (int i = 0; i < POWERUPSTARTING; i++) {
      float x = sketch.random(sketch.width - PowerUp.getPowerupSize());
      powerups.add(createRandomPowerUp(x, y, 0, powerUpSpeed, image));
      y += 150;
    }
  }

  /**
   * Generates PowerUps in the game screen continuously throughout the game.
   */
  public void generatePowerUps() {
    float y = 0;
    while (powerups.size() < maxPowerUps) {
      float x = sketch.random(sketch.width - PowerUp.getPowerupSize());
      PowerUp newPowerUp = createRandomPowerUp(x, y, 0, powerUpSpeed, image);
      powerups.add(newPowerUp);
      y += 150;
    }
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
    int randomInt = (int) (Math.random() * 3);

    // Create and return an instance of a randomly selected subclass
    if (randomInt == 0) {
      return new Tire(xpos, ypos, vx, vy, 2, true, image[2]);
    } else if (randomInt == 1) {
      return new JetPack(xpos, ypos, vx, vy, true, 2, -10, image[1]);
    } else {
      return new ExtraLife(xpos, ypos, vx, vy, true, image[0]);
    }
  }

}
