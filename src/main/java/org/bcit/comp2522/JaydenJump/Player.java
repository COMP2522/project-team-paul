package org.bcit.comp2522.JaydenJump;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * The Doodle guy or the player class.
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
   * Check to see if the player is shooting.
   */
  private boolean isShooting = false;

  /**
   * the projectile the player will shoot.
   */
  private Projectile projectile;

  /**
   * constructor for the player class.
   *
   * @param xpos the x position of the player
   * @param ypos the y position of the player
   * @param vx the x velocity of the player
   * @param vy the y velocity of the player
   * @param sketch the sketch for the player
   * @param image the image to set the player to
   * @param size the size of the player
   * @param speed the speed of the player
   * @param gravity the gravity on the player
   */
  private Player(float xpos, float ypos, float vx, float vy, PApplet sketch, PImage image,
                 int size, float speed, float gravity) {
    super(xpos, ypos, vx, vy, sketch);
    this.sketch = sketch;
    this.image = image;
    this.size = size;
    this.speed = speed;
    this.gravity = gravity;
    projectile = new Projectile(xpos, ypos, 0, -2, super.getSketch(), 1);
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
                                   PImage image,
                                   int size,
                                   float speed,
                                   float gravity) {
    if (instance == null) {
      instance = new Player(xpos, ypos, vx, vy, sketch, image, size, speed, gravity);
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

    if (isShooting) {
      projectile.draw();
    }
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

    if (isShooting) {
      projectile.update();
      if (projectile.getYpos() < 0) {
        stopShooting();
      }
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
   * Method to shoot projectiles.
   */
  public void shootProjectile() {
    if (!isShooting) {
      isShooting = true;
      projectile.setXpos(getXpos());
      projectile.setYpos(getYpos());
      projectile.setVy(-10);
    }
  }

  /**
   * Method to stop shooting projectiles.
   */
  public void stopShooting() {
    isShooting = false;
  }


  /**
   * move the player to the left.
   */
  public void moveLeft() {

    setVx(-speed);
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

  /**
   * getter for the size of the player.
   *
   * @return the size of the player
   */
  public int getSize() {
    return this.size;
  }

  /**
   * setter for the size of the player.
   *
   * @param size the value you want to set the player size too
   */
  public void setSize(int size) {
    this.size = size;
  }

  /**
   * getter for the sketch of the player.
   *
   * @return the sketch of the player
   */
  public PApplet getSketch() {
    return sketch;
  }

  /**
   * setter for the sketch of the player.
   *
   * @param sketch the value you want to set the sketch too
   */
  public void setSketch(PApplet sketch) {
    this.sketch = sketch;
  }

  /**
   * getter for the image of the player.
   *
   * @return the image of the player
   */
  public PImage getImage() {
    return image;
  }

  /**
   * setter for the image of the player.
   *
   * @param image the image you want to set the player too
   */
  public void setImage(PImage image) {
    this.image = image;
  }

  /**
   * getter for the gravity of the player.
   *
   * @return the gravity of the player
   */
  public float getGravity() {
    return gravity;
  }

  /**
   * setter for the gravity of the player.
   *
   * @param gravity the value you want to set the gravity
   */
  public void setGravity(float gravity) {
    this.gravity = gravity;
  }
}