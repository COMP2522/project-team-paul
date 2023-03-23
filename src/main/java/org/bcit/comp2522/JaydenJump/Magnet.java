package org.bcit.comp2522.JaydenJump;

import java.awt.Color;
import processing.core.PImage;

/**
 * The Magnet class is a type of PowerUp that collects coins around the Player
 * with a certain radius, without the Player having to directly touch the coin.
 *
 * @version 1.0
 *
 * @author Shawn Birring
 *
 * @since 2023-03-22
 */

public class Magnet extends PowerUp{
  /** The amount of time the Magnet is active for. */
  private int duration;

  /** The radius of the magnet that can grab the coins. */
  private int radius;

  /**
   * Creates an instance of a Magnet in the game.
   *
   * @param xpos The x position of the Magnet
   *
   * @param ypos The y position of the Magnet
   *
   * @param vx The x velocity of the Magnet
   *
   * @param vy The y velocity of the Magnet
   *
   * @param width The width of the Magnet
   *
   * @param height The height of the Magnet
   *
   * @param isActive The boolean state that determines if the Magnet is active or not
   *
   * @param color The color of the Magnet
   *
   * @param image The image of the Magnet
   *
   * @param duration The amount of time the Magnet lasts for
   *
   * @param radius The radius that the Magnet affects
   */
  public Magnet(int xpos, int ypos, int vx, int vy, int width, int height, boolean isActive, Color color, PImage image,  int duration, int radius) {
    super(xpos, ypos, vx, vy, width, height, isActive, color, image);
    this.duration = duration;
    this.radius = radius;
  }

  /** Checks to see if a coin is within radius of the Player.  */
  public void checkDistance() {}

  /**
   * Adds a coin to the Player if it is within radius of Player
   * with the magnet powerup
   *
   * @param coin in the game
   */
  public void collectCoin(Coin coin) {}

}
