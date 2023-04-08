package org.bcit.comp2522.JaydenJump.gameUI;

import org.bcit.comp2522.JaydenJump.Game;
import org.bcit.comp2522.JaydenJump.interfaces.GameUI;
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

  /**
   * Game object.
   */
  private Game game;

  /**
   * Constants.
   */
  private static final int FONT_SIZE = 30;
  private static final int BUTTON = 350;
  private static final int BUTTON_WIDTH = 150;
  private static final int BUTTON_HEIGHT = 100;


  /******************************************************/

  /**
   * Initializes PauseMenu object.
   *
   * @param window as a MenuManager object
   */
  public void init(MenuManager window) {
    this.window = window;
    this.game = window.getGame();
    draw();
  }

  /**
   * Draws to window.
   */
  public void draw() {
    window.background(35, 150, 170);
    window.textAlign(CENTER);
    window.textSize(FONT_SIZE);
    window.text("Current score: " + game.getScore() + "\nHighscore: " + game.getHighscore(),
        window.width / 2, window.height / 4);
    buttons = new ArrayList<>();
    resume = new Button(window.width / 2, BUTTON, BUTTON_WIDTH, BUTTON_HEIGHT, FONT_SIZE, "Resume", window);
    home = new Button(window.width / 2, BUTTON + BUTTON_WIDTH, BUTTON_WIDTH, BUTTON_HEIGHT, FONT_SIZE, "Home", window);
    buttons.add(resume);
    buttons.add(home);
    for (Button button : buttons) {
      button.draw();
    }
  }
}
