package org.bcit.comp2522.JaydenJump;

import com.mongodb.*;
import processing.core.PApplet;

/**
 * Leaderboards menu.
 *
 * @author Brian Kwon
 * @version 1.0
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
    window.background(25, 150, 170);
    back = new Button(50, 50, 75, 75, 20, "Back", window);
    back.draw();

    window.textAlign(CENTER);
    window.textSize(30);
    window.text("Leaderboards", width * 2 + 50, height);
    window.textSize(20);
    window.text("PLAYER", window.width / 4, 175);
    window.text("SCORE", window.width / 2, 175);
    window.text("DIFFICULTY", window.width - window.width / 4, 175);
    window.textSize(15);

    final int top = 5;
    final int gap = 20;
    int count = 1;

    for (int i = 0; i < str.length && count <= top; i++) {
        String[] arr = str[i].split(":");
        if (arr[2].equals("1")) {
          window.text(count, window.width / 8, 200 + count * gap);
          window.text(arr[0], window.width / 4, 200 + count * gap);
          window.text(arr[1], window.width / 2, 200 + count * gap);
          window.text("Easy", window.width - window.width / 4, 200 + count * gap);
          count++;
        }
    }

    count = 1;
    for (int i = 0; i < str.length && count <= top; i++) {
      String[] arr = str[i].split(":");
      if (arr[2].equals("2")) {
        window.text(count, window.width / 8, 400 + count * gap);
        window.text(arr[0], window.width / 4, 400 + count * gap);
        window.text(arr[1], window.width / 2, 400 + count * gap);
        window.text("Medium", window.width - window.width / 4, 400 + count * gap);
        count++;
      }
    }

    count = 1;
    for (int i = 0; i < str.length && count <= top; i++) {
      String[] arr = str[i].split(":");
      if (arr[2].equals("3")) {
        window.text(count, window.width / 8, 600 + count * gap);
        window.text(arr[0], window.width / 4, 600 + count * gap);
        window.text(arr[1], window.width / 2, 600 + count * gap);
        window.text("Hard", window.width - window.width / 4, 600 + count * gap);
        count++;
      }
    }
  }
}
