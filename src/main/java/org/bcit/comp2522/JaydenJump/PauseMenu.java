package org.bcit.comp2522.JaydenJump;

import java.util.ArrayList;

/**
 * Pause menu.
 *
 * @author Brian Kwon
 * @version 1.0
 */
public class PauseMenu extends Menu {

  /**
   * Window that contains menu screen.
   */
  private Menu window;

  /**
   * List of buttons displayed on menu screen.
   */
  private ArrayList<Button> buttons;

  /**
   * Player's score at the end of the game.
   */
  private int score = 0;

  /**
   * Button used to resume the game.
   */
  Button resume;

  /**
   * Initializes PauseMenu object.
   *
   * @param window as a Menu object
   */
  public void init(Menu window) {
    this.window = window;
    draw();
  }

  /**
   * Draws to window.
   */
  public void draw() {
    window.background(35, 150, 170);
    buttons = new ArrayList<Button>();
    resume = new Button(250, 350, 150, 100, 30, "Resume", window);
    buttons.add(resume);
    for (Button button : buttons) {
      button.draw();
    }
    window.textSize(15);
    window.textAlign(CENTER);
    //score = getCurrentScore();
    window.text("Current score: " + score,  width / 2 + 20, height / 2);
  }

  /**
   * Getter for score.
   *
   * @return score as an int
   */
  public int getScore() {
    return score;
  }

  /**
   * Setter for score.
   *
   * @param score as an int
   */
  public void setScore(int score) {
    this.score = score;
  }
}
