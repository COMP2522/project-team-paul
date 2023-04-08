package org.bcit.comp2522.JaydenJump.interfaces;

import org.bcit.comp2522.JaydenJump.gameUI.MenuManager;

/**
 * Interface for Game UI.
 *
 * @author Brian Kwon
 * @version 1.0
 */
public interface GameUI {

  /**
   * Initializes the user interface component on the screen.
   */
  void init(MenuManager window);

  /**
   * Draws the user interface component on the screen.
   */
  void draw();
}
