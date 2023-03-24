package org.bcit.comp2522.JaydenJump;

import processing.core.PImage;
import java.util.ArrayList;
import static org.bcit.comp2522.JaydenJump.MenuManager.sound;

public class MainMenu extends Menu {

  /**
   * Instance variables.
   */
  private Menu window;
  private PauseMenu gameMenu;
  private GameSettings gameSettings;
  private DeathMenu death;
  private PImage logo;
  private PImage doodle;
  private ArrayList<Button> buttons;
  PImage musicOn;
  PImage musicOff;
  Button start;
  Button leaderboards;
  Button settings;

  /**
   * Initializes MainMenu object.
   *
   * @param window as a Menu object
   * @param logo as a PImage object
   * @param doodle as a PImage object
   * @param musicOn as a PImage object
   * @param musicOff as a PImage object
   */
  public void init(Menu window, PImage logo, PImage doodle, PImage musicOn, PImage musicOff) {
    this.window = window;
    this.logo = logo;
    this.doodle = doodle;
    this.musicOn = musicOn;
    this.musicOff = musicOff;
    gameMenu = new PauseMenu();
    gameSettings = new GameSettings();
    death = new DeathMenu();
    buttons = new ArrayList<Button>();
    start = new Button(150, 390, 150, 100, 30, "Start Game", window);
    leaderboards = new Button(350, 390, 150, 100, 25, "Leaderboards", window);
    settings = new Button(250, 450, 100, 50, 15, "Settings", window);
    buttons.add(start);
    buttons.add(leaderboards);
    buttons.add(settings);
    draw();
  }

  /**
   * Draws to window.
   */
  public void draw() {
    window.background(35, 150, 170);
    musicOn.resize(50, 50);
    musicOff.resize(50, 50);
    window.image(logo, (window.width - logo.width) / 2, (window.height - 500) / 2);
    window.image(doodle, window.width / 4, window.height / 8);
    if (sound) {
      window.image(musicOff, 30, 70, 50, 50);
    } else {
      window.image(musicOn, 30, 70, 50, 50);
    }
    for (Button button : buttons) {
      button.draw();
    }
  }
}
