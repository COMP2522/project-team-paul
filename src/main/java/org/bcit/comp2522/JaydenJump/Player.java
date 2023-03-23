package org.bcit.comp2522.JaydenJump;

import processing.core.PApplet;
import processing.core.PImage;

/**
 *
 * <P>the Doodle guy or the player class.</P>T
 *
 * @author Ravdeep, Aulakh
 * @version 1.0
 */
public class Player extends Sprite {


  /**
   * instance for the player instance.
   */
  private static Player instance = null;

  /**
   * sketch variable.
   */
  private PApplet sketch;

  /**
   * for the image for the player.
   */
  private PImage image;

  /**
   * x position.
   */
  private float xpos;

  /**
   * y position.
   */
  private float ypos;

  /**
   * x velocity.
   */
  private float vx;

  /**
   * y velocity.
   */
  private float vy;

  /**
   * gravity.
   */
  private float gravity;

  /**
   * speed.
   */
  private float speed;

  /**
   * size of the player.
   */
  private int size;

  /**
   * constructor for the player.
   *
   * @param sketch sketch for the player
   * @param image the image for the player
   */
  private Player(int xpos, int ypos, int vx, int vy, PApplet sketch, PImage image) {
    super(xpos, ypos, vx, vy);
    super.setXpos(sketch.width / 2);
    super.setYpos(sketch.height - size);
    super.setVx(0);
    super.setVy(0);
    this.sketch = sketch;
    this.image = image;
    size = 80;
    gravity = 0.5f;
    speed = 5;
  }

  /**
   * since this is singleton design this is to get a instance of the player.
   *
   * @param sketch the sketch
   * @param image the image for the player
   * @return a instance of the player
   */
  public static Player getInstance(int xpos, int ypos, int vx, int vy, PApplet sketch,
                                   PImage image) {
    if (instance == null) {
      instance = new Player(xpos, ypos, vx, vy, sketch, image);
    } else {
      instance.sketch = sketch;
    }
    return instance;
  }

  /**
   * get instance when player already has a instance.
   *
   * @return player instance
   */
  public static Player getInstance() {
    if (instance == null) {
      throw new NullPointerException("player is null");
    }
    return instance;
  }

  /**
   * draw the player onto the screen.
   */
  @Override
  public void draw() {

    sketch.image(image, xpos, ypos, size, size);
  }

  /**
   * update the players position after he jumps and moves side to side.
   */
  @Override
  public void update() {

    vy += gravity;


    xpos += vx;
    ypos += vy;


    xpos = sketch.constrain(xpos, size / 10, sketch.width - size / 2);
    ypos = sketch.constrain(ypos, size / 2, sketch.height - size / 2);


    if (ypos >= sketch.height - size / 2) {
      ypos = sketch.height - size / 2;
      vy = -15;
    }


  }


  /**
   * move the player to the left.
   */
  public void moveLeft() {
    vx = -speed;
  }

  /**
   * move the player to the right.
   */
  public void moveRight() {
    vx = speed;
  }

  /**
   * just in case we need to stop the player from moving at all at ony time.
   */
  public void stopMoving() {
    vx = 0;
  }

  @Override
  public boolean collides(Object o) {
    if (o instanceof Platform) {
      Platform platform = (Platform) o;

      float playerLeft = xpos - size / 2;
      float playerRight = xpos + size / 2;
      float playerTop = ypos - size / 2;
      float playerBottom = ypos + size / 2;

      float platformLeft = platform.getXpos() - platform.getWidth() / 2;
      float platformRight = platform.getXpos() + platform.getWidth() / 2;
      float platformTop = platform.getYpos() - platform.getHeight() / 2;
      float platformBottom = platform.getYpos() + platform.getHeight() / 2;

      return playerLeft < platformRight && playerRight > platformLeft
              && playerTop < platformBottom && playerBottom > platformTop;
    }

    return false;
  }

  /**
   * getter for y position.
   *
   * @return the y position
   */
  public float getYpos() {
    return ypos;
  }

  /**
   * setter for the y position.
   *
   * @param ypos the value you want to set the y position too
   */
  public void setYpos(float ypos) {
    this.ypos = ypos;
  }

  /**
   * getter for the speed.
   *
   * @return speed of the player
   */
  public float getSpeed() {
    return speed;
  }

  /**
   * setter for the speed.
   *
   * @param speed the value you want to set the speed too
   */
  public void setSpeed(float speed) {
    this.speed = speed;
  }

  /**
   * getter for the y velocity.
   *
   * @return the player y velocity
   */
  public float getVy() {
    return vy;
  }

  /**
   * setter for the y velocity.
   *
   * @param vy the value you want to set the y velocity too
   */
  public void setVy(float vy) {
    this.vy = vy;
  }
}