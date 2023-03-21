package org.bcit.comp2522.JaydenJump;

public class Coin {

  private int value;

  private int width;

  private int height;

  private String color;

  public Coin(int value, int width, int height, String color) {
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
