package org.bcit.comp2522.JaydenJump;

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

  public Sprite(float xpos, float ypos, float vx, float vy) {
    this.xpos = xpos;
    this.ypos = ypos;
    this.vx = vx;
    this.vy = vy;
  }

  @Override
  public int compareTo(Object o) {
    return 0;
  }

  @Override
  public void draw() {

  }

  @Override
  public void update() {

  }

  @Override
  public boolean collides(Object o) {
    return false;
  }
}
