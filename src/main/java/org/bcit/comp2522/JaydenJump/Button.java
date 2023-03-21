package org.bcit.comp2522.JaydenJump;

public class Button {

  private int x;

  private int y;

  private int width;

  private int height;

  private String Label;

  public Button(int x, int y, int width, int height, String label) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.Label = label;
  }

  public void onClick() {

  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
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

  public String getLabel() {
    return Label;
  }

  public void setLabel(String label) {
    Label = label;
  }
}
