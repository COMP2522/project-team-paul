package org.bcit.comp2522.JaydenJump;

import processing.core.PImage;

import java.awt.Color;

public class Projectile extends Sprite{

  private int speed;
  private int damage;

  private Color color;

  private PImage image;

  public Projectile(int xpos, int ypos, int vx, int vy, int speed, int damage, Color color, PImage image) {
    super(xpos, ypos, vx, vy);
    this.speed = speed;
    this.damage = damage;
    this.color = color;
    this.image = image;
  }

  public void endGame() {}
  public void removeSprite() {}

  public void damageSprite() {}



  public int getSpeed() {
    return speed;
  }

  public void setSpeed(int speed) {
    this.speed = speed;
  }

  public int getDamage() {
    return damage;
  }

  public void setDamage(int damage) {
    this.damage = damage;
  }

  public Color getColor() {
    return color;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  public PImage getImage() {
    return image;
  }

  public void setImage(PImage image) {
    this.image = image;
  }

}
