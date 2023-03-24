package org.bcit.comp2522.JaydenJump;

import java.util.ArrayList;

public class PauseMenu extends Menu {

  /**
   * Instance variables.
   */
  private Menu window;
  private ArrayList<Button> buttons;
  private int score = 0;
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
    resume = new Button(250, 225, 150, 100, 30, "Resume", window);
    buttons.add(resume);
    for (Button button : buttons) {
      button.draw();
    }
    window.textSize(15);
    window.textAlign(CENTER);
    //score = getCurrentScore();
    window.text("Current score: " + score,  width / 2 + 20, height / 2);
  }
}
