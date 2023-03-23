package org.bcit.comp2522.JaydenJump;

import processing.core.PApplet;
import processing.core.PImage;


/**
 * The PowerUp class represents a type of consumable item that can be used by the player class.
 * This class extends the Sprite class.
 *
 * @version 1.0
 *
 * @author Hyuk Park
 *
 * @since 2023-03-21
 */

public abstract class PowerUp extends Sprite {

  /** The PApplet sketch for printing the image to the screen. */
  private final PApplet sketch;

  /** The width of the PowerUp. */
  private int width;

  /** The height of the PowerUp. */
  private int height;

  /** State that determines whether the PowerUp is active or not. */
  private boolean isActive;

  /**  The color of the PowerUp. */
  //private Color color;

  /** The image associated with the PowerUp. */
  private PImage image;

  /** The player that will recieve the powerup. */
  private Player player;

  /**
   * Constructs the PowerUp class.
   *
   * @param xpos the x position of PowerUp
   *
   * @param ypos the y position of PowerUp
   *
   * @param vx the x velocity of PowerUp
   *
   * @param vy the y velocity of PowerUp
   *
   * @param width the width of PowerUp
   *
   * @param height the height of PowerUp
   *
   * @param isActive the state of PowerUp
   *
   * @param image the image of PowerUp
   */
  public PowerUp(int xpos, int ypos, int vx, int vy, int width, int height,
                 boolean isActive, PImage image, PApplet sketch, Player player) {
    super(xpos, ypos, vx, vy);
    this.width = width;
    this.height = height;
    this.isActive = isActive;
    this.image = image;
    this.sketch = sketch;
    this.player = player;
  }

  public void draw() {
    sketch.image(image, super.getXpos(), super.getYpos());
  }

  /** Activates the PowerUp and affects the player class. */
  public void activate() {}

  /** Deactivates the PowerUp. */
  public void deactivate() {}


  /**
   * Retrieves the width of the PowerUp.
   *
   * @return width of PowerUp
   */
  public int getWidth() {
    return width;
  }

  /**
   * Reassigns the width of the PowerUp to a different size.
   *
   * @param width of PowerUp from user
   */
  public void setWidth(int width) {
    this.width = width;
  }

  /**
   * Retrieves the height of the PowerUp.
   *
   * @return height of PowerUp
   */
  public int getHeight() {
    return height;
  }

  /**
   * Reassigns height of the PowerUp to different size.
   *
   * @param height of PowerUp from user
   */
  public void setHeight(int height) {
    this.height = height;
  }

  /**
   * Retrieves the boolean state of the PowerUp and checks if it is active (true)
   * or not (false).
   *
   * @return boolean isActive of PowerUp
   */
  public boolean isActive() {
    return isActive;
  }

  /**
   * Reassigns the isActive boolean of the PowerUp to either
   * true (active) or false (in-active).
   *
   * @param active boolean from user
   */
  public void setActive(boolean active) {
    isActive = active;
  }

  public PApplet getSketch() {
    return sketch;
  }

  public Player getPlayer() {
    return player;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }

  /**
   * Retrieves the image of the PowerUp.
   *
   * @return image of PowerUp
   */
  public PImage getImage() {
    return image;
  }

  /**
   * Reassigns the image of the PowerUp to a new image.
   *
   * @param image from user
   */
  public void setImage(PImage image) {
    this.image = image;
  }

}
