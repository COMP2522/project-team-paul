package org.bcit.comp2522.JaydenJump;
import java.awt.Color;
import processing.core.PImage;


public class Tire extends PowerUp{

  public Tire(int xpos, int ypos, int vx, int vy, int width, int height, boolean isActive, Color color, PImage image) {
    super(xpos, ypos, vx, vy, width, height, isActive, color, image);
  }
  private int boostHeight;

  public int getBoostHeight() {
    return boostHeight;
  }

  public void setBoostHeight(int boostHeight) {
    this.boostHeight = boostHeight;
  }

  public void boostPlayer() {}
}
