package org.bcit.comp2522.JaydenJump;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * A type of PowerUp that gives the player an extra life in the game.
 *
 * @version 1.0
 *
 * @author Shawn Birring
 *
 * @since 2023-03-22
 */

public class ExtraLife extends PowerUp {

  private static final int MAXIMUMLIVES = 3;

  private PImage image;

  /**
   * Creates an instance of ExtraLife PowerUp in the game.
   *
   * @param xpos The x position of ExtraLife
   *
   * @param ypos The y position of ExtraLife
   *
   * @param vx The x velocity of ExtraLife
   *
   * @param vy The y velocity of ExtraLife
   *
   * @param isActive The boolean state that determines whether ExtraLife is active or not
   */
  public ExtraLife(float xpos,
                   float ypos,
                   float vx,
                   float vy,
                   boolean isActive,
                   PApplet sketch,
                   Player player, PImage image) {
    super(xpos, ypos, vx, vy, isActive, sketch, player, image);
  }

  /**
   * Increases the life of the Player by one.
   */
  public void increaseLife() {
    if (Game.getLives() < MAXIMUMLIVES) {
      Game.setLives(Game.getLives() + 1);
    }
  }

  /**
   * Activates the ExtraLife PowerUp.
   */
  @Override
  public void activate() {
    if (isActive()) {
      increaseLife();
      super.getSketch().text("1 life", getSketch().CENTER, getSketch().CENTER);
    }
  }

  /**
   * Deactivates the ExtraLife PowerUp.
   */
  @Override
  public void deactivate() {
    if (isActive()) {
      setActive(false);
    }
  }
}