package org.bcit.comp2522.JaydenJump;

import java.awt.Color;
import processing.core.PApplet;

/**
 * platform class.
 * represents the platforms in the game
 * @author Shawn Birring, Rav
 * @version 1.0
 */
public class Platform extends Sprite {

  /** the height of the platform. */
  private static final int PLATFORM_HEIGHT = 20;

  /** the width of the platform. */
  private static final int PLATFORM_WIDTH = 100;

  /** color for the platform. */
  private final Color color;

  /** variable to check if it is normal or breakable platform. */
  private final boolean breakable;

  /**
   * constructor for the platform class.
   *
   * @param sketch the sketch
   * @param x the x position
   * @param y the y position
   * @param color the color of the platform
   * @param vx the x velocity of the platform
   * @param vy the y velocity of the platform
   */
  public Platform(PApplet sketch, float x, float y,
                  Color color, float vx, float vy,
                  boolean breakable) {
    super(x, y, vx, vy, sketch);
    this.color = color;
    this.breakable = breakable;
  }

  /**
   * update method to update the position of the platform.
   * moving platform will bounce off the left and right sides of the game
   */
  @Override
  public void update() {
    super.setXpos(super.getXpos() + super.getVx());
    super.setYpos(super.getYpos() + super.getVy());

    // Make the platform bounce off the left and right sides of the game if it's moving platform
    if (super.getVx() != 0) {
      if (super.getXpos() <= 0) {
        super.setXpos(0);
        super.setVx(Math.abs(super.getVx()));
      } else if (super.getXpos() + PLATFORM_WIDTH >= getSketch().width) {
        super.setXpos(getSketch().width - PLATFORM_WIDTH);
        super.setVx(-Math.abs(super.getVx()));
      }
    }
  }

  /**
   * draw method to make the platforms appear in the game.
   */
  @Override
  public void draw() {
    super.getSketch().fill(color.getRGB());
    super.getSketch().rect(getXpos(), getYpos(), PLATFORM_WIDTH, PLATFORM_HEIGHT);
  }

  /**
   * check if the platform is still on the screen.
   *
   * @return true or false if the platform still on screen
   */
  public boolean isOnScreen() {
    return getXpos() >= 0 && getXpos() + PLATFORM_WIDTH <= super.getSketch().width
        && getYpos() >= 0 && getYpos() + PLATFORM_HEIGHT <= super.getSketch().height;
  }

  /**
   * getter for the width of the platform.
   *
   * @return the width of the platform
   */
  public static int getPlatformWidth() {
    return PLATFORM_WIDTH;
  }

  /**
   * getter for the height of the platform.
   *
   * @return the height of the platform
   */
  public static int getPlatformHeight() {
    return PLATFORM_HEIGHT;
  }

  /**
   * getter to check if the platform is breakable.
   *
   * @return if the platform is breakable or not
   */
  public boolean isBreakable() {
    return breakable;
  }

}