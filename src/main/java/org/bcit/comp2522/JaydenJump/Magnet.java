package org.bcit.comp2522.JaydenJump;

import java.awt.Color;
import processing.core.PImage;

public class Magnet extends PowerUp{

  private int duration;

  private int radius;

  public Magnet(int xpos, int ypos, int vx, int vy, int width, int height, boolean isActive, Color color, PImage image,  int duration, int radius) {
    super(xpos, ypos, vx, vy, width, height, isActive, color, image);
    this.duration = duration;
    this.radius = radius;
  }

  public void checkDistance() {}
  public void collectCoin(Coin coin) {}

}
