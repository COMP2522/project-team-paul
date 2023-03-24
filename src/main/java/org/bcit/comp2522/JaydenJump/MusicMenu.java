package org.bcit.comp2522.JaydenJump;

import java.util.ArrayList;

public class MusicMenu extends Menu {

  /**
   * Instance variables.
   */
  private Menu window;
  private ArrayList<Button> buttons;
  Button boss;
  Button dino;

  /**
   * Initializes MusicMenu object.
   *
   * @param window as a Menu object
   */
  public void init(Menu window) {
    this.window = window;
    buttons = new ArrayList<Button>();
    boss = new Button(250, 150, 150, 100, 30,"?", window);
    dino = new Button(250, 300, 150, 100, 30, "dino", window);
    buttons.add(boss);
    buttons.add(dino);
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
