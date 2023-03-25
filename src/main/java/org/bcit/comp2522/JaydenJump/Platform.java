package org.bcit.comp2522.JaydenJump;

import java.awt.Color;
import processing.core.PApplet;

/**
 * platform class.
 *
 * @author Ravdeep, Aulakh
 * @version 1.0
 */
public class Platform extends Sprite {

  /** the height of the platform. */
  private static int height = 20;

  /** the width of the platform. */
  private static int width = 100;

  /** color for the platform. */
  private Color color;

  /** variable to check if it is normal or breakable platform. */
  private boolean breakable;

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
                  Color color, float vx, float vy, boolean breakable) {
    super(x, y, vx, vy, sketch);
    this.color = color;
    this.breakable = breakable;

  }

  /**
   * update method to update the position of the platform.
   */
  @Override
  public void update() {
    super.setXpos(super.getXpos() + super.getVx());
    super.setYpos(super.getYpos() + super.getVy());

    // Make the platform bounce off the left and right sides of the game if it's moving
    if (super.getVx() != 0) {
      if (super.getXpos() <= 0) {
        super.setXpos(0); // Adjust position to be at the left edge
        super.setVx(Math.abs(super.getVx())); // Make sure the platform moves right
      } else if (super.getXpos() + width >= getSketch().width) {
        super.setXpos(getSketch().width - width); // Adjust position to be at the right edge
        super.setVx(-Math.abs(super.getVx())); // Make sure the platform moves left
      }
    }
  }

  /** draw method to make the platforms appear in the game. */
  @Override
  public void draw() {
    super.getSketch().fill(color.getRGB());
    super.getSketch().rect(getXpos(), getYpos(), width, height);
  }

  /**
   * check if the platform is still on the screen.
   *
   * @return true or false if the platform still on screen
   */
  public boolean isOnScreen() {
    return getXpos() >= 0 && getXpos() + width <= super.getSketch().width
            && getYpos() >= 0 && getYpos() + height <= super.getSketch().height;
  }


  public void breakPlatform() {
  }

  /**
   * getter for the width of the platform.
   *
   * @return the width of the platform
   */
  public static int getWidth() {
    return width;
  }

  /**
   * setter for the width of the platform.
   *
   * @param width the value you want to the set the width of the platform too
   */
  public void setWidth(int width) {
    this.width = width;
  }

  /**
   * getter for the height of the platform.
   *
   * @return the height of the platform
   */
  public static int getHeight() {
    return height;
  }

  /**
   * setter for the height of the platform.
   *
   * @param height the height you want to set the platform too
   */
  public void setHeight(int height) {
    this.height = height;
  }

  /**
   * getter for the color of the platform.
   *
   * @return the color of the platform
   */
  public Color getColor() {
    return color;
  }

  /**
   * setter for the color of the platform.
   *
   * @param color the value you want to set the color too
   */
  public void setColor(Color color) {
    this.color = color;
  }

  /**
   * getter to check if the platform is breakable.
   *
   * @return if the platform is breakable or not
   */
  public boolean isBreakable() {
    return breakable;
  }

  /**
   * setter for if the platform is breakable or not.
   *
   * @param breakable the value you want to set the platforms breakability too
   */
  public void setBreakable(boolean breakable) {
    this.breakable = breakable;
  }
}