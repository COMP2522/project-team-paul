package org.bcit.comp2522.JaydenJump;
import java.awt.Color;
import processing.core.PImage;

public class SpringShoes extends PowerUp{

  private int boostValue;
  private int duration;
  public SpringShoes(int width, int height, boolean isActive, Color color, PImage image, int duration, int boostValue) {
    super(width, height, isActive, color, image);
    this.boostValue = boostValue;
    this.duration = duration;
  }
}
