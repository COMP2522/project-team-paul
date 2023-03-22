package org.bcit.comp2522.JaydenJump;

/**
 * The Sprite class represents a game sprite that can be drawn and updated on a game screen.
 * It implements the Comparable, Drawable, and Collideable interfaces.
 * @version 1.0
 * @author Shawn Birring
 * @since 2023-03-21
 */

public abstract class Sprite implements Comparable<Object>, Drawable, Collideable{

  /** The x position of the sprite on the game screen. */
  private float xpos;

  /** The y position of the sprite on the game screen. */
  private float ypos;

  /** The x velocity of the sprite. */
  private float vx;

  /** The y velocity of the sprite. */
  private float vy;

  /**
   * Creates a new Sprite with the given x and y positions, and x and y velocities.
   * @param xpos The x position of the sprite
   * @param ypos The y position of the sprite
   * @param vx The x velocity of the sprite
   * @param vy The y velocity of the sprite
   */
  public Sprite(float xpos, float ypos, float vx, float vy) {
    this.xpos = xpos;
    this.ypos = ypos;
    this.vx = vx;
    this.vy = vy;
  }

  /**
   * Compares this sprite to the specified object for order. Returns a zero if they are equal.
   * @param o The object to be compared
   * @return A zero if the objects are equal
   */
  @Override
  public int compareTo(Object o) {
    return 0;
  }

  /** Draws the sprite on the game screen. */
  @Override
  public void draw() {
  }

  /** Updates the position of the sprite based on its velocity. */
  @Override
  public void update() {
  }

  /**
   * Determines if this sprite collides with the specified object.
   * @param o The object to check for collision
   * @return True if the sprite collides with the object, false otherwise
   */
  @Override
  public boolean collides(Object o) {
    return false;
  }
}