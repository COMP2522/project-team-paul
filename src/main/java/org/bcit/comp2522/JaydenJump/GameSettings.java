package org.bcit.comp2522.JaydenJump;

import java.util.ArrayList;

public class GameSettings extends Menu {

  /**
   * Instance variables.
   */
  private Menu window;
  private ArrayList<Button> buttons;
  Button level;
  Button music;
  Button customize;
  Button back;

  /**
   * Initializes GameSettings object.
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
    level = new Button(250, 100, 150, 100, 30, "Levels", window);
    music = new Button(250, 250, 150, 100, 30, "Music", window);
    customize = new Button(250, 400, 150, 100, 30, "Customize", window);
    back = new Button(50, 50, 75, 75, 15, "Back", window);
    buttons.add(level);
    buttons.add(music);
    buttons.add(customize);
    buttons.add(back);
    for (Button button : buttons) {
      button.draw();
    }
  }
}
