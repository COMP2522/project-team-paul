package org.JaydenJump;

public class Tire Extends PowerUp {

  public Tire(int width, int height, boolean isActive, Color color, PImage image) {
    super(width, height, isActive, color, image);
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
