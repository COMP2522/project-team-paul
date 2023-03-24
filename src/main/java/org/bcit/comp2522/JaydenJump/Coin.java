package org.bcit.comp2522.JaydenJump;

import processing.core.PApplet;

public class Coin extends Sprite{

  private int value;

  private int width;

  private int height;

  private String color;

  public Coin(float xpos, float ypos, float vx, float vy, int value, int width, int height, String color, PApplet sketch) {
    super(xpos, ypos, vx, vy, sketch);
    this.value = value;
    this.width = width;
    this.height = height;
    this.color = color;
  }

  public void addToScore() {

  }

  public void playSound() {

  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
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

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }
}
