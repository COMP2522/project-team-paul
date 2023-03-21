package org.bcit.comp2522.JaydenJump;

import processing.core.PImage;

public class Obstacle extends Sprite{

  private int width;
  private int height;

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

  private PImage image;

  public Obstacle(int xpos, int ypos, int vx, int vy, int width, int height, PImage image) {
    super(xpos, ypos, vx, vy);
    this.width = width;
    this.height = height;
    this.image = image;
  }


}
