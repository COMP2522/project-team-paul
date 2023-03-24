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

  /**
   * Constructor for the projectile class.
   *
   * @param xpos the x position of the projectile
   * @param ypos the y position of the projectile
   * @param vx the x velocity of the projectile
   * @param vy the y velocity of the projectile
   * @param sketch the sketch for the projectile
   * @param damage the damage the projectile does
   */
  public Projectile(float xpos, float ypos, int vx, int vy, PApplet sketch, int damage) {
    super(xpos, ypos, vx, vy, sketch);
    this.damage = damage;
    this.color = color;
  }

  /**
   * Draw method for the projectile.
   */
  public void draw() {
    getSketch().fill(color.getRGB());
    getSketch().ellipse(getXpos(), getYpos(), 10, 10);
  }

  /**
   * Update method for the projectile.
   */
  public void update() {
    setXpos(getXpos() + getVx());
    setYpos(getYpos() + getVy());
  }

  /**
   * Method to end the game.
   */
  public void endGame() {}

  /**
   * Method to remove Sprite when projectile hits it.
   */
  public void removeSprite() {}

  /**
   * Method to damage Sprite when projectile hits it.
   */
  public void damageSprite() {}

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

}
