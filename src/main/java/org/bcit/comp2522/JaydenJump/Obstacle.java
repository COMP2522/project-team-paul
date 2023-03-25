package org.bcit.comp2522.JaydenJump;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * Represents an obstacle.
 *
 * @author Shawn Birring
 *
 * @version 1.0
 */
public class Obstacle extends Sprite {

  /**
   * The width of the obstacle.
   */
  private int width;

  /**
   * The height of the obstacle.
   */
  private int height;

  /**
   * The image of the obstacle.
   */
  private PImage image;

  /**
   * The sketch that the obstacle is drawn on.
   */
  private PApplet sketch;

  /**
   * The player.
   */
  private Player player;

  /**
   * Constructs an obstacle.
   *
   * @param xpos the x position of the obstacle
   *
   * @param ypos the y position of the obstacle
   *
   * @param vx the x velocity of the obstacle
   *
   * @param vy the y velocity of the obstacle
   *
   * @param width the width of the obstacle
   *
   * @param height the height of the obstacle
   *
   * @param image the image of the obstacle
   *
   * @param sketch the sketch that the obstacle is drawn on
   *
   */

  public Obstacle(int xpos, int ypos, int vx, int vy, int width, int height, PImage image,
                  PApplet sketch, Player player) {
    super(xpos, ypos, vx, vy, sketch);
    this.width = width;
    this.height = height;
    this.image = image;
    this.sketch = sketch;
  }

  /**
   * Draws the obstacle.
   */
  @Override
  public void draw() {
    sketch.image(image, getXpos(), getYpos(), width, height);
  }

  /**
   * Updates the obstacle.
   */
  @Override
  public void update() {
    setXpos(getXpos() + getVx());
    setYpos(getYpos() + getVy());
  }

  /**
    * Checks if the obstacle has collided with the player.
    *
    * @return true if the obstacle has collided with the player, false otherwise
    */

  public boolean hasCollided() {
    if (getXpos() < player.getXpos() + player.getImgSize()
        && getXpos() + width > player.getXpos()
        && getYpos() < player.getYpos() + player.getImgSize()
        && getYpos() + height > player.getYpos()) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Ends the game.
   */
  public void endGame() {
    sketch.textSize(50);
    sketch.text("Game Over", 200, 200);
  }

  /**
   * Checks if the obstacle is off the screen.
   *
   * @return width of the obstacle
   */
  public int getWidth() {
    return width;
  }

  /**
   * Sets the width of the obstacle.
   *
   * @param width the width of the obstacle
   */
  public void setWidth(int width) {
    this.width = width;
  }

  /**
   * Gets the height of the obstacle.
   *
   * @return height of the obstacle
   */
  public int getHeight() {
    return height;
  }

  /**
   * Sets the height of the obstacle.
   *
   * @param height the height of the obstacle
   */
  public void setHeight(int height) {
    this.height = height;
  }

  /**
   * Gets the image of the obstacle.
   *
   * @return image of the obstacle
   */
  public PImage getImage() {
    return image;
  }

  /**
   * Sets the image of the obstacle.
   *
   * @param image the image of the obstacle
   */
  public void setImage(PImage image) {
    this.image = image;
  }

  /**
   * Gets the sketch that the obstacle is drawn on.
   *
   * @return sketch that the obstacle is drawn on
   */
  public PApplet getSketch() {
    return sketch;
  }

  /**
   * Sets the sketch that the obstacle is drawn on.
   *
   * @param sketch the sketch that the obstacle is drawn on
   */
  public void setSketch(PApplet sketch) {
    this.sketch = sketch;
  }
}
