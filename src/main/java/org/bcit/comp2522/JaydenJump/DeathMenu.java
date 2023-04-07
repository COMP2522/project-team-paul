package org.bcit.comp2522.JaydenJump;

import java.util.ArrayList;
import processing.core.PApplet;

/**
 * Death menu.
 *
 * @author Brian Kwon
 * @version 1.0
 */
public class DeathMenu extends PApplet implements GameUI {

  /**
   * Window that contains menu screen.
   */
  private MenuManager window;

  /**
   * List of buttons displayed on menu screen.
   */
  private ArrayList<Button> buttons;

  /**
   * Button used to start a new game.
   */
  Button playAgain;

  /**
   * Button used to access leaderboards.
   */
  Button changeDifficulty;

  /**
   * Button used to return to main menu.
   */
  Button home;

  /**
   * Button used to submit highscore to leaderboards.
   */
  Button submit;

  /**
   * Constants.
   */
  private static final int FONT_SIZE = 25;
  private static final int FONT_SIZE_BIG = 40;
  private static final int FONT_SIZE_SMALL = 15;
  private static final int SCORE_OFFSET = 40;
  private static final int BUTTON = 325;
  private static final int BUTTON_OFFSET = 100;
  private static final int BUTTON_WIDTH = 125;
  private static final int BUTTON_HEIGHT = 75;

  /********************************************************/

  /**
   * Initializes DeathMenu object.
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
    window.textAlign(CENTER);
    window.textSize(FONT_SIZE_BIG);
    window.text("Game Over!", window.width / 2, window.height / 8);
    window.textSize(FONT_SIZE);
    window.text("Score: " + Game.getScore(), window.width / 2, window.height / 4);
    window.text("Highscore: " + Game.getHighscore(), window.width / 2, window.height / 4 + SCORE_OFFSET);
    buttons          = new ArrayList<Button>();
    playAgain        = new Button(window.width / 2, BUTTON, BUTTON_WIDTH, BUTTON_HEIGHT, FONT_SIZE, "Play again", window);
    changeDifficulty = new Button(window.width / 2, BUTTON + BUTTON_OFFSET, BUTTON_WIDTH, BUTTON_HEIGHT, FONT_SIZE_SMALL, "Change difficulty", window);
    home             = new Button(window.width / 2, BUTTON + BUTTON_OFFSET * 2, BUTTON_WIDTH, BUTTON_HEIGHT, FONT_SIZE, "Home", window);
    submit           = new Button(window.width / 2, BUTTON + BUTTON_OFFSET * 3, BUTTON_WIDTH, BUTTON_HEIGHT, FONT_SIZE, "Submit", window);
    buttons.add(playAgain);
    buttons.add(changeDifficulty);
    buttons.add(home);
    buttons.add(submit);
    for (Button button : buttons) {
      button.draw();
    }
  }
}
