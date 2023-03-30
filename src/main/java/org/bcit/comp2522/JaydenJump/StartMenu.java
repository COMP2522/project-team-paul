package org.bcit.comp2522.JaydenJump;

import processing.core.PApplet;

/**
 * Start menu of game that shows game controls.
 *
 * @author Brian Kwon
 * @version 1.0
 */
public class StartMenu extends PApplet {

  /**
   * Window that contains menu screen.
   */
  private MenuManager window;

  /******************************************************/

  /**
   * Initializes StartMenu object.
   *
   * @param window as a MenuManager
   */
  public void init(MenuManager window) {
    this.window = window;
    draw();
  }

  /**
   * Draws to window.
   */
  public void draw() {
    window.background(35, 150, 170);
    window.textSize(20);
    window.fill(0);
    window.text("no one likes shawn", window.width / 2, window.height / 2);
  }
}
