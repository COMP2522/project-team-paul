package org.bcit.comp2522.JaydenJump;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * Enemy class.
 *
 * @author Ravdeep, Aulakh
 * @version 1.0
 */
public class Enemy extends Sprite {

  /**
   * The health of the enemy.
   */
  private int health;

  /**
   * The speed of the enemy.
   */
  private int speed;

  /**
   * The width of the enemy.
   */
  private int width;

  /**
   * The height of the enemy.
   */
  private int height;

  /**
   * The image for the enemy.
   */
  private PImage image;

  /**
   * Boolean to check whether the enemy is to be removed or not.
   */
  private boolean toBeRemoved;

  /**
   * Constructor for the enemy class.
   *
   * @param xpos the x position of the enemy
   * @param ypos the y position of the enemy
   * @param vx the x velocity of the enemy
   * @param vy the y velocity of the enemy
   * @param health the health of the enemy
   * @param speed the speed of the enemy
   * @param width the width of the enemy
   * @param height the height of the enemy
   * @param image the image for the enemy
   * @param sketch the sketch of the enemy
   */
  public Enemy(float xpos, float ypos, float vx, float vy, int health, int speed, int width,
               int height, PImage image, PApplet sketch) {
    super(xpos, ypos, vx, vy, sketch);
    this.health = health;
    this.speed = speed;
    this.width = width;
    this.height = height;
    this.image = image;
    toBeRemoved = false;

  }

  /**
   * Update method for enemy.
   */
  @Override
  public void update() {
    setYpos(getYpos() + speed);
    if (getYpos() > getSketch().height) {
      setToBeRemoved(true);
    }
  }

  /**
   * Draw method for the enemy.
   */
  @Override
  public void draw() {
    getSketch().image(image, getXpos(), getYpos(), width, height);
  }

  /**
   * getter for if the enemy is to be removed.
   *
   * @return if the enemy is to be removed or not
   */
  public boolean isToBeRemoved() {
    return toBeRemoved;
  }

  /**
   * setter for if the enemy is to be removed or not.
   *
   * @param b the value you want to set the enemies remove state too
   */
  public void setToBeRemoved(boolean b) {
    toBeRemoved = b;
  }

  /**
   * Collides method for the enemy to see if collided with player or projectile.
   *
   * @param o The object to check for collision
   *
   * @return if it collided or not
   */
  @Override
  public boolean collides(Object o) {
    if (o instanceof Player player) {
      float distance = PApplet.dist(player.getXpos(), player.getYpos(), getXpos(), getYpos());
      return distance < (player.getPlayerSize() + getWidth()) / 2f;
    } else if (o instanceof Projectile projectile) {
      float distance = PApplet.dist(projectile.getXpos(), projectile.getYpos(),
              getXpos(), getYpos());
      if (distance < (10 + getWidth()) / 2f) {
        setToBeRemoved(true);
        return true;
      }
    }
    return false;
  }

  /**
   * getter for the width of the enemy.
   *
   * @return the width of the enemy
   */
  public int getWidth() {
    return width;
  }

  /**
   * setter for the width of the enemy.
   *
   * @param width the value you want to set the width too
   */
  public void setWidth(int width) {
    this.width = width;
  }

  /**
   * getter for the height of the enemy.
   *
   * @return the value of the height
   */
  public int getHeight() {
    return height;
  }

  /**
   * setter for the height of the enemy.
   *
   * @param height the value you want to set the height of the enemy too
   */
  public void setHeight(int height) {
    this.height = height;
  }

  /**
   * getter for the image of the enemy.
   *
   * @return the image for the enemy
   */
  public PImage getImage() {
    return image;
  }

  /**
   * setter for the image of the enemy.
   *
   * @param image the path to the image you want to set
   */
  public void setImage(PImage image) {
    this.image = image;
  }
}
