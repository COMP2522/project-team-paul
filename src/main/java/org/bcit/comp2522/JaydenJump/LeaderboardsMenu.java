package org.bcit.comp2522.JaydenJump;

import processing.core.PApplet;

/**
 * Leaderboards menu.
 *
 * @author Brian Kwon
 * @version 1.0
 */
public class LeaderboardsMenu extends PApplet implements GameUI {

  /**
   * Window that contains menu screen.
   */
  private MenuManager window;

  /**
   * Button used to return back to the main menu.
   */
  Button back;

  /*********************************************/

  /**
   * Initializes LeaderboardsMenu.
   *
   * @param window as a MenuManager object
   */
  public void init(MenuManager window) {
    this.window = window;
    draw();
  }

  /**
   * Draws to window.
   */
  public void draw() {
    window.background(25, 150, 170);
    window.textSize(30);
    window.textAlign(CENTER);
    window.text("Leaderboards", width * 2 + 50, height);
    back = new Button(50, 50, 75, 75, 20, "Back", window);
    back.draw();
  }

  /**
   * Event listener for mouse clicks.
   *
   * @param x as an int
   * @param y as an int
   */
  public void handleMouseClick(int x, int y) {
    if (back.isClicked(x, y)) {
      System.out.println("clicked");
      MenuManager.setCurrentScreen(0);
    }
  }
}
