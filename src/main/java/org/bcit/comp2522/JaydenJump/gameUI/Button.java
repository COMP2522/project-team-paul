package org.bcit.comp2522.JaydenJump.gameUI;

/**
 * Button.
 *
 * @author Brian Kwon
 * @version 1.0
 */
public class Button {

  /**
   * X-coordinate of button.
   */
  private int x;

  /**
   * Y-coordinate of button.
   */
  private int y;

  /**
   * Width of button.
   */
  private int width;

  /**
   * Height of button.
   */
  private int height;

  /**
   * Font size of label text.
   */
  private int fontSize;

  /**
   * Text displayed on button.
   */
  private String label;

  /**
   * Window that contains the button.
   */
  private MenuManager window;

  /**
   * Constructor.
   *
   * @param x        as an int
   * @param y        as an int
   * @param width    as an int
   * @param height   as an int
   * @param fontSize as an int
   * @param label    as a string
   * @param window   as a Menu object
   */
  public Button(int x, int y, int width, int height,
                int fontSize, String label, MenuManager window) {
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
    window.ellipseMode(window.CENTER);
    window.stroke(0);
    window.fill(35, 150, 250);
    window.ellipse(x, y, width, height);
    window.fill(0);
    window.textSize(fontSize);
    window.text(label, x, y);
    window.textAlign(window.CENTER, window.CENTER);
  }

  /**
   * Determines if a button has been clicked.
   *
   * @param x as an int
   * @param y as an int
   * @return boolean value
   */
  public boolean isClicked(int x, int y) {
    return (x >= this.x - width / 2 && x <= this.x + width / 2
        && y >= this.y - height / 2 && y <= this.y + height / 2);
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
}
