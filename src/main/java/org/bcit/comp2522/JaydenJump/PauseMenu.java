package org.bcit.comp2522.JaydenJump;

import processing.core.PApplet;

import java.util.ArrayList;

/**
 * Pause menu.
 *
 * @author Brian Kwon
 * @version 1.0
 */
public class PauseMenu extends PApplet implements GameUI {

  /**
   * Window that contains menu screen.
   */
  private MenuManager window;

  /**
   * List of buttons displayed on menu screen.
   */
  private ArrayList<Button> buttons;

  /**
   * Button used to resume the game.
   */
  Button resume;

  /**
   * Button used to return to main menu.
   */
  Button home;

  /******************************************************/

  /**
   * Initializes PauseMenu object.
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
    window.background(35, 150, 170);
    window.textSize(15);
    window.textAlign(CENTER);
    window.text("Current score: " + Game.getScore() + "\nHighscore: " + Game.getHighscore(),
        width / 2 + 20, height / 2);
    buttons = new ArrayList<>();
    resume = new Button(window.width / 2, 350, 150, 100, 30, "Resume", window);
    home = new Button(window.width / 2, 500, 150, 100, 30, "Home", window);
    buttons.add(resume);
    buttons.add(home);
    for (Button button : buttons) {
      button.draw();
    }
  }
}
