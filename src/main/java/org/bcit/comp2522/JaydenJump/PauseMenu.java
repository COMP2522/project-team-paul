package org.bcit.comp2522.JaydenJump;

import processing.core.PApplet;

import java.util.ArrayList;

/**
 * Pause menu.
 *
 * @author Brian Kwon
 * @version 1.0
 */
public class PauseMenu extends PApplet {

  /**
   * Window that contains menu screen.
   */
  private MenuManager window;

  /**
   * Button used to resume the game.
   */
  Button resume;

  /******************************************************/

  /**
   * Initializes PauseMenu object.
   *
   * @param window as a Menu object
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
    resume = new Button(250, 350, 150, 100, 30, "Resume", window);
    resume.draw();
    window.textSize(15);
    window.textAlign(CENTER);
    window.text("Current score: " + Game.getScore(), width / 2 + 20, height / 2);
  }
}
