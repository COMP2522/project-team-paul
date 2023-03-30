package org.bcit.comp2522.JaydenJump;

import processing.core.PApplet;

import java.awt.*;

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

  }
}
