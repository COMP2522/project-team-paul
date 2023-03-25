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

  /**
   * Creates an instance of ExtraLife powerup in the game.
   *
   * @param xpos The x position of ExtraLife
   * @param ypos The y position of ExtraLife
   * @param vx The x velocity of ExtraLife
   * @param vy The y velocity of ExtraLife
   * @param isActive The boolean state that determines whether ExtraLife is active or not
   */
  public ExtraLife(int xpos,
                   int ypos,
                   int vx,
                   int vy,
                   boolean isActive,
                   PApplet sketch,
                   Player player) {
    super(xpos, ypos, vx, vy, isActive, sketch, player);
  }

  /**
   * Increases the life of the Player by one.
   */
  public void increaseLife() {
    //getPlayer().setLives(getPlayer().getLives() + 1);
  }

  /**
   * Activates the ExtraLife powerup.
   */
  @Override
  public void activate() {
    if (isActive()) {
      increaseLife();
    }
  }

  /**
   * Deactivates the ExtraLife powerup.
   */
  @Override
  public void deactivate() {
    if (isActive()) {
      setActive(false);
    }
  }
}