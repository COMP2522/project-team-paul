package org.bcit.comp2522.JaydenJump;

import processing.core.PApplet;
import java.awt.*;


/**
 * Class for the projectiles that the player is going to shoot.
 *
 * @author Ravdeep, Aulakh & Shawn Birring
 * @version 1.0
 */
public class Projectile extends Sprite {

  /** the damage the projectile does. */
  private int damage;

  /** the color of the projectile. */
  private Color color = Color.blue;

  /** instance of the player. */
  private Player player;

  /** check if a projectile has hit before. */
  private boolean hasHit;

  /**
   * Constructor for the projectile class.
   *
   * @param xpos the x position of the projectile
   * @param ypos the y position of the projectile
   * @param vx the x velocity of the projectile
   * @param vy the y velocity of the projectile
   * @param damage the damage the projectile does
   */
  public Projectile(float xpos, float ypos, int vx, int vy, int damage, Player player) {
    super(xpos, ypos, vx, vy, null);
    this.damage = damage;
    this.color = color;
    this.player = player;
    hasHit = false;
  }

  /**
   * Draw method for the projectile.
   */
  public void draw() {
    player.getSketch().fill(color.getRGB());
    player.getSketch().ellipse(getXpos(), getYpos(), 10, 10);
  }

  /**
   * Update method for the projectile.
   */
  public void update() {
    setXpos(getXpos() + getVx());
    setYpos(getYpos() + getVy());
  }


  /**
   * collide method to see if projectile collided with the enemy.
   *
   * @param o The object to check for collision
   *
   * @return if they have collided or not
   */
  @Override
  public boolean collides(Object o) {
    if (o instanceof Enemy) {
      Enemy enemy = (Enemy) o;
      float distance = PApplet.dist(getXpos(), getYpos(), enemy.getXpos(), enemy.getYpos());
      float radiusSum = 5 + enemy.getWidth();
      return distance < radiusSum;
    }
    return false;
  }

  /**
   * getter for the damage the projectile does.
   *
   * @return the damage the projectile
   */
  public int getDamage() {
    return damage;
  }

  public boolean isHasHit() {
    return hasHit;
  }

  public void setHasHit(boolean hasHit) {
    this.hasHit = hasHit;
  }

}