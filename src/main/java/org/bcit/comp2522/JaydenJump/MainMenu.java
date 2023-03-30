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
   * Game settings menu.
   */
  //private GameSettings gameSettings;

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
   * Button used to access game settings.
   */
  //Button settings;

  /****************************************************/

  /**
   * Initializes MainMenu object.
   *
   * @param window as a MenuManager object
   * @param logo as a PImage object
   * @param doodle as a PImage object
   * @param musicOn as a PImage object
   * @param musicOff as a PImage object
   */
  public void init(MenuManager window, PImage logo, PImage doodle,
                   PImage musicOn, PImage musicOff) {
    this.window = window;
    this.logo = logo;
    this.doodle = doodle;
    this.musicOn = musicOn;
    this.musicOff = musicOff;
    gameMenu     = new PauseMenu();
    //gameSettings = new GameSettings();
    death        = new DeathMenu();
    buttons      = new ArrayList<Button>();
    start        = new Button(150, 600, 150, 100, 30, "Start Game", window);
    leaderboards = new Button(350, 600, 150, 100, 25, "Leaderboards", window);
    //settings     = new Button(250, 660, 100, 50, 15, "Settings", window);
    buttons.add(start);
    buttons.add(leaderboards);
    //buttons.add(settings);
    draw();
  }

  /**
   * Draws to window.
   */
  public void draw() {
    window.background(35, 150, 170);
    doodle.resize(400, 400);
    musicOn.resize(50, 50);
    musicOff.resize(50, 50);
    window.image(logo, 0, 0);
    window.image(doodle, 0, 150);
    if (sound) {
      window.image(musicOn, 30, 90, 50, 50);
    } else {
      window.image(musicOff, 30, 90, 50, 50);
    }
    for (Button button : buttons) {
      button.draw();
    }
  }
}
