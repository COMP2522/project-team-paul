package org.bcit.comp2522.JaydenJump;

import processing.core.PApplet;

public class Button extends PApplet {

  /**
   * Instance variables.
   */
  private int x;
  private int y;
  private int width;
  private int height;
  private int fontSize;
  private String label;
  private Menu window;

  /**
   * Constructor.
   *
   * @param x as an int
   * @param y as an int
   * @param width as an int
   * @param height as an int
   * @param fontSize as an int
   * @param label as a string
   * @param window as a Menu object
   */
  public Button(int x, int y, int width, int height, int fontSize, String label, Menu window) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.fontSize = fontSize;
    this.label = label;
    this.window = window;
  }

  /**
   * Draws to window.
   */
  public void draw() {
    window.ellipseMode(CENTER);
    window.stroke(0);
    window.fill(35, 150, 250);
    window.ellipse(x, y, width, height);
    window.fill(0);
    window.textSize(fontSize);
    window.text(label, x, y);
    window.textAlign(CENTER, CENTER);
  }

  /**
   * Determines if a button has been clicked.
   *
   * @param x as an int
   * @param y as an int
   *
   * @return boolean value
   */
  public boolean isClicked(int x, int y) {
    return (x >= this.x - width/2 && x <= this.x + width/2 && y >= this.y - height/2 && y <= this.y + height/2);
  }

  /**
   * Getter for x.
   *
   * @return x as an int
   */
  public int getX() {
    return x;
  }

  /**
   * Setter for x.
   *
   * @param x as an int
   */
  public void setX(int x) {
    this.x = x;
  }

  /**
   * Getter for y.
   *
   * @return y as an int
   */
  public int getY() {
    return y;
  }

  /**
   * Setter for y.
   *
   * @param y as an int
   */
  public void setY(int y) {
    this.y = y;
  }

  /**
   * Getter for width.
   *
   * @return width as an int
   */
  public int getWidth() {
    return width;
  }

  /**
   * Setter for width.
   *
   * @param width as an int
   */
  public void setWidth(int width) {
    this.width = width;
  }

  /**
   * Getter for height.
   *
   * @return height as an int
   */
  public int getHeight() {
    return height;
  }

  /**
   * Setter for height.
   *
   * @param height as an int
   */
  public void setHeight(int height) {
    this.height = height;
  }

  /**
   * Getter for font size.
   *
   * @return font size as an int
   */
  public int getFontSize() {
    return fontSize;
  }

  /**
   * Setter for font size.
   *
   * @param fontSize as an int
   */
  public void setFontSize(int fontSize) {
    this.fontSize = fontSize;
  }

  /**
   * Getter for label.
   *
   * @return label as a string
   */
  public String getLabel() {
    return label;
  }

  /**
   * Setter for label.
   *
   * @param label as a string
   */
  public void setLabel(String label) {
    this.label = label;
  }
}
