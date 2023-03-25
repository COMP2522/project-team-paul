package org.bcit.comp2522.JaydenJump;

import processing.core.PApplet;

/**
 * Abstract base class for all menu screens.
 *
 * @author Brian Kwon
 * @version 1.0
 */
public abstract class Menu extends PApplet implements GameUI {

  /**
   * Sets the size of menu screen.
   */
  public void settings() {
    size(480, 800);
  }
}