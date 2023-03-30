//package org.bcit.comp2522.JaydenJump;
//
//import java.util.ArrayList;
//import processing.core.PApplet;
//
///**
// * Game settings menu.
// */
//public class GameSettings extends PApplet implements GameUI {
//
//  /**
//   * Window that contains menu screen.
//   */
//  private MenuManager window;
//
//  /**
//   * List of buttons displayed on menu screen.
//   */
//  private ArrayList<Button> buttons;
//
//  /**
//   * Button used to select level difficulty.
//   */
//  Button level;
//
//  /**
//   * Button used to access music menu.
//   */
//  Button music;
//
//  /**
//   * Button used to access customization menu.
//   */
//  Button customize;
//
//  /**
//   * Button used to return to the main menu.
//   */
//  Button back;
//
//  /*******************************************************/
//
//  /**
//   * Initializes GameSettings object.
//   *
//   * @param window as a Menu object
//   */
//  public void init(MenuManager window) {
//    this.window = window;
//    draw();
//  }
//
//  /**
//   * Draws to window.
//   */
//  public void draw() {
//    window.background(35, 150, 170);
//    buttons   = new ArrayList<Button>();
//    level     = new Button(250, 250, 150, 100, 30, "Levels", window);
//    music     = new Button(250, 400, 150, 100, 30, "Music", window);
//    customize = new Button(250, 550, 150, 100, 30, "Customize", window);
//    back      = new Button(50, 50, 75, 75, 15, "Back", window);
//    buttons.add(level);
//    buttons.add(music);
//    buttons.add(customize);
//    buttons.add(back);
//    for (Button button : buttons) {
//      button.draw();
//    }
//  }
//}
