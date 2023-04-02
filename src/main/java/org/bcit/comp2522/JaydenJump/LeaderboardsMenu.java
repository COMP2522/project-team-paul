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
   * Button used to return back to the main menu.
   */
  Button back;

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
    window.textAlign(CENTER);
    back = new Button(50, 50, 75, 75, 20, "Back", window);
    back.draw();
    window.textSize(30);
    window.text("Leaderboards", width * 2 + 50, height);
    window.textSize(15);
    window.textAlign(LEFT);
    window.text("Player", 100, 175);
    window.textAlign(RIGHT);
    window.text("Score", 400, 175);

    window.textAlign(CENTER);
    window.textSize(20);
    for (int i = 0; i < str.length; i++) {
      String[] arr = str[i].split(":");
      window.text(arr[0], window.width / 4, 220 + i * 50);
      window.text(arr[1], window.width - 100, 220 + i * 50);
    }
  }
}
