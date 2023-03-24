package org.bcit.comp2522.JaydenJump;

import processing.core.PApplet;

/**
 * Sprite class.
 *
 * @author Ravdeep, Aulakh & Shawn Birring.
 * @version 1.0
 */
public abstract class Sprite implements Comparable<Object>, Drawable, Collideable {

  /** x position of sprite. */
  private float xpos;

  /** y position of sprite. */
  private float ypos;

  /** x velocity of sprite. */
  private float vx;

  /** sketch object for sprites. */
  private PApplet sketch;

  /**
   * getter for the sketch.
   *
   * @return the sketch
   */
  public PApplet getSketch() {
    return sketch;
  }

  /**
   * setter for the sketch.
   *
   * @param sketch the value you want to set the sketch too
   */
  public void setSketch(PApplet sketch) {
    this.sketch = sketch;
  }

  /**
   * getter for x position.
   *
   * @return the x position
   */
  public float getXpos() {
    return xpos;
  }
  
  /**
   * setter for the x position.
   *
   * @param xpos value you want to set x pos too
   */
  public void setXpos(float xpos) {
    this.xpos = xpos;
  }

  /**
   * getter for the y position.
   *
   *
   * @return the y position
   */
  public float getYpos() {
    return ypos;
  }

  /**
   * setter for the y position.
   *
   *
   * @param ypos the value you want to set the y position too
   */
  public void setYpos(float ypos) {
    this.ypos = ypos;
  }

  /**
   * getter for the velocity x.
   *
   * @return the x velocity
   */
  public float getVx() {
    return vx;
  }

  /**
   * setter for the velocity x.
   *
   * @param vx what you want to set the velocity x too
   */
  public void setVx(float vx) {
    this.vx = vx;
  }

  /**
   * getter for the y velocity.
   *
   *
   * @return the y velocity
   */
  public float getVy() {
    return vy;
  }

  /**
   * setter for the y velocity.
   *
   *
   * @param vy value to set y velocity too
   */
  public void setVy(float vy) {
    this.vy = vy;
  }

  /** y velocity. */
  private float vy;

  /**
   * Creates a new Sprite with the given x and y positions, and x and y velocities.
   *
   * @param xpos The x position of the sprite
   *
   * @param ypos The y position of the sprite
   *
   * @param vx The x velocity of the sprite
   *
   * @param vy The y velocity of the sprite
   */
  public Sprite(float xpos, float ypos, float vx, float vy, PApplet sketch) {
    this.xpos = xpos;
    this.ypos = ypos;
    this.vx = vx;
    this.vy = vy;
    this.sketch = sketch;
  }

  /**
   * Compares this sprite to the specified object for order. Returns a zero if they are equal.
   *
   * @param o The object to be compared
   *
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
   *
   * @param o The object to check for collision
   *
   * @return True if the sprite collides with the object, false otherwise
   */
  @Override
  public boolean collides(Object o) {
    return false;
  }
}