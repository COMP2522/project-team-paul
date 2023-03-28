package org.bcit.comp2522.JaydenJump;

import processing.core.PApplet;
import processing.core.PImage;

public class Enemy extends Sprite{

  private int health;

  private int speed;

  private int width;

  private int height;

  private PImage image;

  private boolean toBeRemoved;

  public Enemy(float xpos, float ypos, float vx, float vy, int health, int speed, int width, int height, PImage image, PApplet sketch) {
    super(xpos, ypos, vx, vy, sketch);
    this.health = health;
    this.speed = speed;
    this.width = width;
    this.height = height;
    this.image = image;
    toBeRemoved = false;

  }

  @Override
  public void update() {
    setYpos(getYpos() + speed);
    if (getYpos() > getSketch().height) {
      setToBeRemoved(true);
    }
  }

  @Override
  public void draw() {
    getSketch().image(image, getXpos(), getYpos(), width, height);
  }

  public boolean isToBeRemoved() {
    return toBeRemoved;
  }

  public void setToBeRemoved(boolean b) {
    toBeRemoved = b;
  }

    public void endGame() {

  }

  public int getHealth() {
    return health;
  }

  public void setHealth(int health) {
    this.health = health;
  }

  public int getSpeed() {
    return speed;
  }

  public void setSpeed(int speed) {
    this.speed = speed;
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public PImage getImage() {
    return image;
  }

  public void setImage(PImage image) {
    this.image = image;
  }
}
