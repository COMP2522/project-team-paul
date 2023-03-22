package org.bcit.comp2522.JaydenJump;

import processing.core.PImage;
import java.awt.Color;

/**
 * The JetPack class is a type of PowerUp that allows the Player to fly up
 * screen for a certain period of time.
 *
 * @version 1.0
 *
 * @author Shawn Birring
 *
 * @since 2023-03-23
 */

public class JetPack extends PowerUp{
  /** The amount of time the JetPack lasts. */
  private int duration;

  /** The amount the y velocity of the player is increased. */
  private int boostVelocity;

  /**
   * Creates an Instance of a JetPack in the game.
   *
   * @param xpos The x position of JetPack
   * @param ypos The y position of JetPack
   * @param vx The x velocity of JetPack
   * @param vy The y velocity of JetPack
   * @param width The width of JetPack
   * @param height The height of JetPack
   * @param isActive The boolean state that determines whether JetPack is active or not
   * @param color The color of JetPack
   * @param image The image of JetPack
   * @param duration The amount of time the JetPack lasts for
   * @param boostVelocity The amount the y direction of Player is affected by JetPack
   */
  public JetPack(int xpos, int ypos, int vx, int vy, int width, int height, boolean isActive, Color color, PImage image, int duration, int boostVelocity) {
    super(xpos, ypos, vx, vy, width, height, isActive, color, image);
    this.duration = duration;
    this.boostVelocity = boostVelocity;
  }

}
