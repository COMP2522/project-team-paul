package org.bcit.comp2522.JaydenJump;

import processing.core.PImage;

import java.awt.Color;


public class Platform extends Sprite{

  private int width;
  private int height;
  private Color color;
  private PImage image;

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

  public Platform(int x, int y, int vx, int vy, int width, int height, Color color, PImage image) {
    super(x, y, vx, vy);
    this.width = width;
    this.height = height;
    this.color = color;
    this.image = image;
  }

  public void breakPlatform() {
  }


}
