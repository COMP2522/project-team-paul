package org.bcit.comp2522.JaydenJump;

import java.awt.Color;
import processing.core.PImage;

/**
 * The SpringShoes class is a type of PowerUp that increases the y velocity the player
 * for a certain length of time (allows Player to jump greater heights).
 *
 * @version 1.0
 *
 * @author Shawn Birring
 *
 * @since 2023-03-22
 */

public class SpringShoes extends PowerUp {

  /** The amount that affects the y velocity of the Player. */
  private int boostValue;

  /** The length of time the Player's x velocity is affected for. */
  private int duration;

  /**
   * Creates an instance of the SpringShoes class in the game.
   *
   * @param xpos The x position of SpringShoes
   *
   * @param ypos The y position of SpringShoes
   *
   * @param vx The x velocity of SpringShoes
   *
   * @param vy The y velocity of SpringShoes
   *
   * @param width The width of SpringShoes
   *
   * @param height The height of SpringShoes
   *
   * @param isActive The boolean state of SpringShoes that determines if it is active or not
   *
   * @param color The color of SpringShoes
   *
   * @param image The image of SpringShoes
   *
   * @param duration How long the SpringShoes last for
   *
   * @param boostValue The amount that affects the y velocity of SpringShoes
   */
  public SpringShoes(int xpos, int ypos, int vx, int vy, int width, int height,
                     boolean isActive, Color color, PImage image, int duration, int boostValue) {
    super(xpos, ypos, vx, vy, width, height, isActive, color, image);
    this.boostValue = boostValue;
    this.duration = duration;
  }
}
