package org.bcit.comp2522.JaydenJump;

import java.awt.Color;
import processing.core.PApplet;



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

  private Player player;

  private boolean toBeRemoved;

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
    this.toBeRemoved = false;
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

  public void remove() {
    this.toBeRemoved = true;
  }

  public boolean isToBeRemoved() {
    return toBeRemoved;
  }

  /**
   * getter for the damage the projectile does.
   *
   * @return the damage
   */
  public int getDamage() {
    return damage;
  }

  /**
   * setter for the damage the projectile does.
   *
   * @param damage the value you want to set the damage too
   */
  public void setDamage(int damage) {
    this.damage = damage;
  }

  /**
   * getter for the color of the projectile.
   *
   * @return the color of the projectile
   */
  public Color getColor() {
    return color;
  }

  /**
   * setter for the color of the projectile.
   *
   * @param color the color you want to set the projectile too
   */
  public void setColor(Color color) {
    this.color = color;
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
      float radiusSum = 5 + enemy.getWidth(); // Assuming the projectile radius is 5
      return distance < radiusSum;
    }
    return false;
  }

}