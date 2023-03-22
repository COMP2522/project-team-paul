package org.bcit.comp2522.JaydenJump;

import processing.core.PImage;
import java.awt.Color;

public class JetPack extends PowerUp{

  private int duration;

  private int boostVelocity;

  public JetPack(int xpos, int ypos, int vx, int vy, int width, int height, boolean isActive, Color color, PImage image, int duration, int boostVelocity) {
    super(xpos, ypos, vx, vy, width, height, isActive, color, image);
    this.duration = duration;
    this.boostVelocity = boostVelocity;
  }

}
