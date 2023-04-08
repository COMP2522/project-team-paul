package org.bcit.comp2522.JaydenJump;

import java.util.ArrayList;
import processing.core.PApplet;

/**
 * Menu where the player chooses their desired difficulty.
 *
 * @author Brian Kwon
 * @version 1.1
 */
public class DifficultyMenu extends PApplet implements GameUI {

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

  /**
   * Constants.
   */
  private static final int BUTTON = 200;
  private static final int BUTTON_WIDTH = 150;
  private static final int BUTTON_HEIGHT = 100;
  private static final int FONT_SIZE = 20;

  /******************************************************/

  /**
   * Intiializes Difficulty object.
   */
  public void init() {
    this.window = MenuManager.getInstance();
    buttons = new ArrayList<Button>();
    easy = new Button(window.width / 2, BUTTON, BUTTON_WIDTH, BUTTON_HEIGHT, FONT_SIZE, "Easy", window);
    medium = new Button(window.width / 2, BUTTON * 2, BUTTON_WIDTH, BUTTON_HEIGHT, FONT_SIZE, "Medium", window);
    hard = new Button(window.width / 2, BUTTON * 3, BUTTON_WIDTH, BUTTON_HEIGHT, FONT_SIZE, "Hard", window);
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
