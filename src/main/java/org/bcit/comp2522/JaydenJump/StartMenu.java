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
    window.textAlign(CENTER);
    window.textSize(50);
    window.fill(0);
    window.text("Game Controls", window.width / 2, window.height / 8);
    window.textSize(30);
    window.text("Left Arrow - Move Player Left", window.width / 2, window.height / 4);
    window.text("Right Arrow - Move Player Right", window.width / 2, window.height / 4 + 50);
    window.text("Space Bar - Shoot", window.width / 2, window.height / 4 + 100);
    window.text("P - Pause", window.width / 2, window.height / 4 + 150);
    window.text("Escape - Exit Game", window.width / 2, window.height / 4 + 200);
    window.text("Press [Space] to Continue...", window.width / 2, window.height - 200);
  }
}
