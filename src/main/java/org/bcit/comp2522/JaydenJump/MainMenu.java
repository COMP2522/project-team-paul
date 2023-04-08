package org.bcit.comp2522.JaydenJump;

import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PImage;
import static org.bcit.comp2522.JaydenJump.MenuManager.sound;

/**
 * Main screen of game.
 *
 * @author Brian Kwon
 * @version 1.1
 */
public class MainMenu extends PApplet {

  /**
   * Window that contains menu screen.
   */
  private MenuManager window;

  /**
   * Pause menu that can be accessed during gameplay.
   */
  private PauseMenu gameMenu;

  /**
   * Death menu that is displayed when player loses the game.
   */
  private DeathMenu death;

  /**
   * Image of game title.
   */
  private PImage logo;

  /**
   * Image of player's avatar.
   */
  private PImage doodle;

  /**
   * List of buttons displayed on menu screen.
   */
  private ArrayList<Button> buttons;

  /**
   * Image displayed when music is turned on.
   */
  PImage musicOn;

  /**
   * Image displayed when music is turned off.
   */
  PImage musicOff;

  /**
   * Button used to start the game.
   */
  Button start;

  /**
   * Button used to access the leaderboards.
   */
  Button leaderboards;

  /**
   * Constants.
   */
  private static final int X_GAP = 100;
  private static final int Y_GAP = 150;
  private static final int X = 30;
  private static final int Y = 90;
  private static final int SIZE = 50;

  /****************************************************/

  /**
   * Initializes MainMenu object.
   *
   * @param logo as a PImage object
   * @param doodle as a PImage object
   * @param musicOn as a PImage object
   * @param musicOff as a PImage object
   */
  public void init(PImage logo, PImage doodle,
                   PImage musicOn, PImage musicOff) {
    this.window = MenuManager.getInstance();
    this.logo = logo;
    this.doodle = doodle;
    this.musicOn = musicOn;
    this.musicOff = musicOff;
    gameMenu     = new PauseMenu();
    death        = new DeathMenu();
    buttons      = new ArrayList<Button>();
    start        = new Button(window.width / 2 - X_GAP, window.height - Y_GAP, 150, 100, 30, "Start Game", window);
    leaderboards = new Button(window.width / 2 + X_GAP, window.height - Y_GAP, 150, 100, 25, "Leaderboards", window);
    buttons.add(start);
    buttons.add(leaderboards);
    draw();
  }

  /**
   * Draws to window.
   */
  public void draw() {
    window.background(35, 150, 170);
    window.image(logo, 0, 0);
    window.image(doodle, window.width / 2 - doodle.width / 2, window.height / 2 - doodle.height / 2);
    if (sound) {
      window.image(musicOn, X, Y, SIZE, SIZE);
    } else {
      window.image(musicOff, X, Y, SIZE, SIZE);
    }
    for (Button button : buttons) {
      button.draw();
    }
  }
}
