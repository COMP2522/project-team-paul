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
  private Player(float xpos, float ypos, float vx, float vy, PApplet sketch, PImage image) {
    super(xpos, ypos, vx, vy);
    this.sketch = sketch;
    this.image = image;
    size = 80;
    gravity = 0.6f;
    speed = 3.5f;
  }

  /**
   * since this is singleton design this is to get a instance of the player.
   *
   * @param sketch the sketch
   * @param image the image for the player
   * @return a instance of the player
   */
  public static Player getInstance(float xpos,
                                   float ypos,
                                   float vx,
                                   float vy,
                                   PApplet sketch,
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
    sketch.image(image, getXpos(), getYpos(), size, size);
  }

  /**
   * update the players position after he jumps and moves side to side.
   */
  @Override
  public void update() {

    float temp = getVy() + gravity;
    if (temp > 15f) {
      setVy(15f);
    } else {
      setVy(getVy() + gravity);
    }

    setXpos(getXpos() + getVx());
    setYpos(getYpos() + getVy());


    setXpos(sketch.constrain(getXpos(), size / 10, sketch.width - size / 2));
    setYpos(sketch.constrain(getYpos(), size / 2, sketch.height - size / 2));


    if (getYpos() >= sketch.height - size / 2) {
      setYpos(sketch.height - size / 2);
      setVy(-15);
    }
  }




  /**
   * move the player to the left.
   */
  public void moveLeft() {

    setVx(getVx() - speed);
  }

  /**
   * move the player to the right.
   */
  public void moveRight() {
    setVx(speed);
  }

  /**
   * just in case we need to stop the player from moving at all at ony time.
   */
  public void stopMoving() {
    setVx(0);
  }

  @Override
  public boolean collides(Object o) {
    if (o instanceof Platform) {
      Platform platform = (Platform) o;

      float playerLeft = getXpos() - size / 2;
      float playerRight = getXpos() + size / 2;
      float playerTop = getYpos() - size / 2;
      float playerBottom = getYpos() + size / 2;

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

  public int getSize() {
    return this.size;
  }
  public void setSize(int size) {
    this.size = size;
  }
}