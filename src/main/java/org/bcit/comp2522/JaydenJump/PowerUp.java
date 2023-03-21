package org.bcit.comp2522.JaydenJump;

import java.awt.Color;
import processing.core.PImage;

public abstract class PowerUp extends Sprite{

  private int width;
  private int height;
  private boolean isActive;
  private Color color;

  private PImage image;

  public PowerUp(int xpos, int ypos, int vx, int vy, int width, int height, boolean isActive, Color color, PImage image) {
    super(xpos, ypos, vx, vy);
    this.width = width;
    this.height = height;
    this.isActive = isActive;
    this.color = color;
    this.image = image;
  }

  public void activate() {}
  public void deactivate() {}

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

  public boolean isActive() {
    return isActive;
  }

  public void setActive(boolean active) {
    isActive = active;
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
