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
   * Player's score at the end of the game.
   */
  private int score = 0;

  /**
   * Player's highest score achieved in the game so far.
   */
  private int highscore = 0;

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
    window.textSize(30);
    window.textAlign(CENTER);
    window.text("Game Over!", width * 2 + 50, height);
    window.textSize(25);
    window.text("Score: " + Game.getScore(), width * 2 + 40, height * 2 + 25);
    window.text("Highscore: " + Game.getHighscore(), width * 2 + 40, height * 2 + 50);
    buttons          = new ArrayList<Button>();
    playAgain        = new Button(window.width / 2, 325, 125, 75, 25, "Play again", window);
    changeDifficulty = new Button(window.width / 2, 425, 125, 75, 15, "Change difficulty", window);
    home             = new Button(window.width / 2, 525, 125, 75, 25, "Home", window);
    submit           = new Button(window.width / 2, 625, 125, 75, 25, "Submit", window);
    buttons.add(playAgain);
    buttons.add(changeDifficulty);
    buttons.add(home);
    buttons.add(submit);
    for (Button button : buttons) {
      button.draw();
    }
  }
}
