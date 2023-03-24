package org.bcit.comp2522.JaydenJump;

import java.util.ArrayList;

public class GameSettings extends Menu {

  /**
   * Instance variables.
   */
  private ArrayList<Button> buttons;
  private Button level;
  Button music;
  private Button customize;
  Button back;
  private Menu window;
  private MainMenu mainMenu;

  public void settings() {
    size(480, 480);
  }

  public void setup() {
    //this.init();
  }

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

  /**
   * Event listener for mouse presses.
   */
//  @Override
//  public void mousePressed() {
//    super.mousePressed();
//    for (Button button : buttons) {
//      if (button.isClicked(this.mouseX, this.mouseY)) {
//        if (button == level) {
//          System.out.println("Opening levels...");
//        } else if (button == music) {
//          System.out.println("Opening music...");
//        } else if (button == customize) {
//          System.out.println("Opening player customization...");
//        } else if (button == back) {
//          System.out.println("Back to main menu...");
//          mainMenu = new MainMenu();
//          mainMenu.init();
//        }
//      }
//    }
//  }
}
