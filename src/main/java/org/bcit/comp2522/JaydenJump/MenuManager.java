package org.bcit.comp2522.JaydenJump;

import processing.core.PApplet;
import processing.core.PImage;
import java.util.ArrayList;

public class MenuManager extends Menu {

  /**
   * Instance variables.
   */
  private PImage logo;
  private PImage doodle;
  private PImage musicOn;
  private PImage musicOff;
  private ArrayList<Button> buttons;
  private Button start;
  private Button quit;
  private Button settings;
  private MainMenu mainMenu;
  private PauseMenu pauseMenu;
  private GameSettings gameSettings;
  private DeathMenu death;
  private static int currentScreen = 0;
  boolean clickable = true;

  public void settings() {
    size(480, 480);
  }

  public void setup() {
    logo = loadImage("images/logo.png");
    doodle = loadImage("images/doodle.png");
    musicOn = loadImage("images/music_on.png");
    musicOff = loadImage("images/music_off.png");
    this.init();
  }

  public void init() {
    mainMenu = new MainMenu();
    pauseMenu = new PauseMenu();
    gameSettings = new GameSettings();
    death = new DeathMenu();
//    logo = loadImage("images/logo.png");
//    doodle = loadImage("images/doodle.png");
//    buttons = new ArrayList<Button>();
//    start = new Button(150, 390, 150, 100, 30, "Start Game", this);
//    quit = new Button(350, 390, 150, 100, 30, "Quit Game", this);
//    settings = new Button(250, 450, 100, 50, 15, "Settings", this);
//    buttons.add(start);
//    buttons.add(quit);
//    buttons.add(settings);
  }

  /**
   * Draws to window.
   */
  public void draw() {
    if (currentScreen == 0) {
      mainMenu.init(this, logo, doodle, musicOn, musicOff);
    } else if (currentScreen == 1) {
      pauseMenu.init(this);
    } else if (currentScreen == 2) {
      gameSettings.init(this);
    } else {
      death.init(this);
    }
  }

  public void mousePressed() {
    if (clickable) {
      if (mainMenu.start.isClicked(mouseX, mouseY)) {
        currentScreen = 1;
      } else if (currentScreen == 1 && pauseMenu.resume.isClicked(mouseX, mouseY)) {
        currentScreen = 0;
      } else if (currentScreen == 0 && mainMenu.quit.isClicked(mouseX, mouseY)) {
        exit();
      } else if (currentScreen == 0 && mainMenu.settings.isClicked(mouseX, mouseY)) {
        currentScreen = 2;
      } else if (currentScreen == 2 && gameSettings.back.isClicked(mouseX, mouseY)) {
        currentScreen = 0;
      } else if (currentScreen == 3 && death.playAgain.isClicked(mouseX, mouseY)) {
        currentScreen = 0;
      } else if (currentScreen == 3 && death.quit.isClicked(mouseX, mouseY)) {
        exit();
      } else if (currentScreen == 0) {
        if (mouseX >= 0 && mouseX < mainMenu.musicOff.width && mouseY >= 0 && mouseY < mainMenu.musicOff.height) {
          System.out.println("Click registered");
        }
      } else {
        currentScreen = 3;
      }
    }

    //clickable = true;
  }

  public static void main(String[] args) {
    String[] appletArgs = new String[]{"MenuManager"};
    MenuManager menuManager = new MenuManager();
    PApplet.runSketch(appletArgs, menuManager);
  }
}
