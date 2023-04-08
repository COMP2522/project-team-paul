package org.bcit.comp2522.JaydenJump.gameUI;

import processing.core.PApplet;

/**
 * Leaderboards menu.
 *
 * @author Brian Kwon
 * @version 1.1
 */
public class LeaderboardsMenu extends PApplet {

  /**
   * Window that contains menu screen.
   */
  private MenuManager window;

  /**
   * Button used to return to the main menu.
   */
  Button back;

  /**
   * Array of player names, score, and difficulties from database.
   */
  String[] str;

  /**
   * Constants.
   */
  private static final int BUTTON = 50;
  private static final int BUTTON_WIDTH_HEIGHT = 75;
  private static final int FONT_SIZE = 20;
  private static final int FONT_SIZE_BIG = 30;
  private static final int FONT_SIZE_SMALL = 15;
  private static final int TOP = 5;
  private static final int GAP = 20;
  private static final int HARD = 600;

  /***************************************************/

  /**
   * Initializes LeaderboardsMenu object.
   *
   * @param window as a MenuManager object
   */
  public void init(MenuManager window, String[] str) {
    this.window = window;
    this.str = str;
    draw();
  }

  /**
   * Draws to window.
   */
  public void draw() {
    window.background(35, 150, 170);
    back = new Button(BUTTON, BUTTON, BUTTON_WIDTH_HEIGHT, BUTTON_WIDTH_HEIGHT, FONT_SIZE, "Back", window);
    back.draw();

    window.textAlign(CENTER);
    window.textSize(FONT_SIZE_BIG);
    window.text("Leaderboards", window.width / 2, window.height / 8);
    window.textSize(FONT_SIZE);
    window.text("PLAYER", window.width / 4, window.height / 5);
    window.text("SCORE", window.width / 2, window.height / 5);
    window.text("DIFFICULTY", window.width - window.width / 4, window.height / 5);
    window.textSize(FONT_SIZE_SMALL);

    int count = 1;
    for (int i = 0; i < str.length && count <= TOP; i++) {
        String[] arr = str[i].split(":");
        if (arr[2].equals("1")) {
          window.text(count, window.width / 8, window.height / 4 + count * GAP);
          window.text(arr[0], window.width / 4, window.height / 4 + count * GAP);
          window.text(arr[1], window.width / 2, window.height / 4 + count * GAP);
          window.text("Easy", window.width - window.width / 4, window.height / 4 + count * GAP);
          count++;
        }
    }

    count = 1;
    for (int i = 0; i < str.length && count <= TOP; i++) {
      String[] arr = str[i].split(":");
      if (arr[2].equals("2")) {
        window.text(count, window.width / 8, window.height / 2 + count * GAP);
        window.text(arr[0], window.width / 4, window.height / 2 + count * GAP);
        window.text(arr[1], window.width / 2, window.height / 2 + count * GAP);
        window.text("Medium", window.width - window.width / 4, window.height / 2 + count * GAP);
        count++;
      }
    }

    count = 1;
    for (int i = 0; i < str.length && count <= TOP; i++) {
      String[] arr = str[i].split(":");
      if (arr[2].equals("3")) {
        window.text(count, window.width / 8, HARD + count * GAP);
        window.text(arr[0], window.width / 4, HARD + count * GAP);
        window.text(arr[1], window.width / 2, HARD + count * GAP);
        window.text("Hard", window.width - window.width / 4, HARD + count * GAP);
        count++;
      }
    }
  }
}
