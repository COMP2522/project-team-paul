package org.bcit.comp2522.JaydenJump;

import java.awt.Color;

import processing.core.PImage;

public class SpringShoes extends PowerUp {

  private int boostValue;
  private int duration;

  public SpringShoes(int xpos, int ypos, int vx, int vy, int width, int height, boolean isActive, Color color, PImage image, int duration, int boostValue) {
    super(xpos, ypos, vx, vy, width, height, isActive, color, image);
    this.boostValue = boostValue;
    this.duration = duration;
  }
}
