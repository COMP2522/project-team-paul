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
  private static int height;

  /** the width of the platform. */
  private static int width;

  /** color for the platform. */
  private Color color;

  /** speed for moving platforms. */
  private int speed;

  private boolean breakable;

  /** sketch to make the platform appear on screen. */
  private PApplet sketch;

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
   * getter for the speed of the platform.
   *
   * @return the speed of the platform
   */
  public int getSpeed() {
    return speed;
  }

  /**
   * setter for the speed of the platform.
   *
   * @param speed the value you want to set the speed too
   */
  public void setSpeed(int speed) {
    this.speed = speed;
  }

  /**
   * constructor for the platform class.
   *
   * @param sketch the sketch
   * @param x the x position
   * @param y the y position
   * @param width the width of platform
   * @param height the height of the platform
   * @param color the color of the platform
   * @param speed the speed of the platform
   * @param vx the x velocity of the platform
   * @param vy the y velocity of the platform
   */
  public Platform(PApplet sketch, float x, float y, int width, int height,
                  Color color, int speed, float vx, float vy, boolean breakable) {
    super(x, y, vx, vy);
    this.sketch = sketch;
    this.width = width;
    this.height = height;
    this.color = color;
    this.speed = speed;
    this.breakable = breakable;

  }

  /** draw method to make the platforms appear in the game. */
  @Override
  public void draw() {

    sketch.fill(color.getRGB());
    sketch.rect(getXpos(), getYpos(), width, height);

  }

  /**
   * Move the platform down.
   */
  public void moveDown() {
    setYpos(getYpos() + speed);
    speed += 0.01;

  }

  /**
   * check if the platform is still on the screen.
   *
   * @return true or false if the platform still on screen
   */
  public boolean isOnScreen() {
    return getXpos() >= 0 && getXpos() + width <= sketch.width
            && getYpos() >= 0 && getYpos() + height <= sketch.height;
  }


  public void breakPlatform() {
  }

  public boolean isBreakable() {
    return breakable;
  }

  public void setBreakable(boolean breakable) {
    this.breakable = breakable;
  }
}