package org.bcit.comp2522.JaydenJump;

import java.util.ArrayList;
import processing.core.PApplet;

/**
 * Menu where the player chooses their desired difficulty.
 *
 * @author Brian Kwon
 * @version 1.0
 */
public class DifficultyMenu extends PApplet {

  /**
   * Window that contains menu screen.
   */
  private MenuManager window;

  /**
   * List of buttons displayed on menu screen.
   */
  private ArrayList<Button> buttons;

  /**
   * Button used to select easy difficulty.
   */
  Button easy;

  /**
   * Button used to select medium difficulty.
   */
  Button medium;

  /**
   * Button used to select hard difficulty.
   */
  Button hard;

  /******************************************************/

  /**
   * Intiializes Difficulty object.
   *
   * @param window as an MenuManager object
   */
  public void init(MenuManager window) {
    this.window = window;
    buttons = new ArrayList<Button>();
    easy = new Button(window.width / 2, 200, 150, 100, 20, "Easy", window);
    medium = new Button(window.width / 2, 400, 150, 100, 20, "Medium", window);
    hard = new Button(window.width / 2, 600, 150, 100, 20, "Hard", window);
    buttons.add(easy);
    buttons.add(medium);
    buttons.add(hard);
    draw();
  }

  /**
   * Draws to window.
   */
  public void draw() {
    window.background(35, 150, 170);
    for (Button button : buttons) {
      button.draw();
    }
  }
}
