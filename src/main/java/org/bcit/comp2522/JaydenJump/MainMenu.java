package org.bcit.comp2522.JaydenJump;

import processing.core.PImage;
import java.util.ArrayList;

import static org.bcit.comp2522.JaydenJump.MenuManager.sound;

public class MainMenu extends Menu {

  /**
   * Instance variables.
   */
  private PImage logo;
  private PImage doodle;
  PImage musicOn;
  PImage musicOff;
  private ArrayList<Button> buttons;
  Button start;
  Button quit;
  Button settings;
  private PauseMenu gameMenu;
  private GameSettings gameSettings;
  private DeathMenu death;
  boolean clickable = true;
  //private static boolean sound;
  private static boolean clicked;
  private Menu window;

  public MainMenu() {

  }

//  public void settings() {
//    size(480, 480);
//  }

  public void setup() {
//    logo = loadImage("images/logo.png");
//    doodle = loadImage("images/doodle.png");
  }

  public void run(Menu window) {
    setup();
    //init(window);
    draw();
  }

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
    quit = new Button(350, 390, 150, 100, 30, "Quit Game", window);
    settings = new Button(250, 450, 100, 50, 15, "Settings", window);
    buttons.add(start);
    buttons.add(quit);
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

//    if (currentScreen == 0) {
//      window.background(35, 150, 170);
//      image(logo, (width - logo.width) / 2, (height - 500) / 2);
//      image(doodle, width / 4, height / 8);
//      for (Button button : buttons) {
//        button.draw();
//      }
//    } else if (currentScreen == 1) {
//      gameMenu.init(this);
//    } else if (currentScreen == 2) {
//      // open game settings
//      gameSettings.init(this);
//    } else {
//      death.init(this);
//    }
  }

  /**
   * Event listener for mouse presses.
   */
//  @Override
//  public void mousePressed() {
//    if (mouseX >= 30 && mouseX < 30 + musicOff.width && mouseY >= 70 && mouseY < 70 + musicOff.height) {
//      if (clicked == false) {
//        clicked = true;
//      } else {
//        clicked = false;
//      }
//    }
//  }

//  public static void main(String[] args) {
//    String[] appletArgs = new String[]{"MainMenu"};
//    MainMenu mainMenu = new MainMenu();
//    PApplet.runSketch(appletArgs, mainMenu);
//  }
}
