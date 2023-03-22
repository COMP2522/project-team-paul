package org.bcit.comp2522.JaydenJump;

import processing.core.PImage;

import java.awt.*;

/**
 * A type of PowerUp that gives the player an extra life in the game.
 *
 * @version 1.0
 *
 * @author Shawn Birring
 *
 * @since 2023-03-22
 */

public class ExtraLife extends PowerUp{

  /**
   * Creates an instance of ExtraLife powerup in the game.
   *
   * @param xpos The x position of ExtraLife
   * @param ypos The y position of ExtraLife
   * @param vx The x velocity of ExtraLife
   * @param vy The y velocity of ExtraLife
   * @param width The width of ExtraLife
   * @param height The height of ExtraLife
   * @param isActive The boolean state that determines whether ExtraLife is active or not
   * @param color The color of ExtraLife
   * @param image The image of ExtraLife
   */
  public ExtraLife(int xpos, int ypos, int vx, int vy, int width, int height,
                   boolean isActive, Color color, PImage image) {
    super(xpos, ypos, vx, vy, width, height, isActive, color, image);
  }

  /**
   * Increases the life of the Player by one.
   */
  public void increaseLife() {

  }
}
