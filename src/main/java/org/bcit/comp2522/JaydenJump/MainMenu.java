package org.bcit.comp2522.JaydenJump;

import processing.core.PApplet;
import processing.core.PImage;
//import processing.sound.*;

import java.util.ArrayList;

public class MainMenu extends Menu {

  private PImage logo;
  private PImage doodle;
  private ArrayList<Button> buttons;
  private Button start;
  private Button quit;
  private Button settings;
  private PauseMenu gameMenu;
  private GameSettings gameSettings;
  private DeathMenu death;
  private static int currentScreen = 0;
  boolean clickable = true;
  //private SoundFile file;

  public void settings() {
    size(480, 480);
  }

  public void setup() {
    this.init();
  }

  public void init() {
    //file = new SoundFile(this, "music/music.mp3");
    //file.play();
    gameMenu = new PauseMenu();
    gameSettings = new GameSettings();
    death = new DeathMenu();
    logo = loadImage("images/logo.png");
    doodle = loadImage("images/doodle.png");
    buttons = new ArrayList<Button>();
    start = new Button(150, 390, 150, 100, 30, "Start Game", this);
    quit = new Button(350, 390, 150, 100, 30, "Quit Game", this);
    settings = new Button(250, 450, 100, 50, 15, "Settings", this);
    buttons.add(start);
    buttons.add(quit);
    buttons.add(settings);
  }

  public void draw() {
    if (currentScreen == 0) {
      background(35, 150, 170);
      image(logo, (width - logo.width) / 2, (height - 500) / 2);
      image(doodle, width / 4, height / 8);
      for (Button button : buttons) {
        button.draw();
      }
    } else if (currentScreen == 1) {
      gameMenu.init(this);
    } else if (currentScreen == 2) {
      // open game settings
      gameSettings.init(this);
    } else {
      death.init(this);
    }

//    if (currentScreen == 0) {
//      background(35, 150, 170);
//      image(logo, (width - logo.width) / 2, (height - 500) / 2);
//      image(doodle, width / 4, height / 8);
//      for (Button button : buttons) {
//        button.draw();
//      }
//    } else if (currentScreen == 1) {
//      // start game
//
//      // menu when game is paused
//      gameMenu.init(this);
//    } else if (currentScreen == 2) {
//      // open game settings
//      gameSettings.init(this);
//    }
  }

  public void mousePressed() {
//    super.mousePressed(); // call the parent's mousePressed method
//    for (Button button : buttons) {
//      if (button.isClicked(mouseX, mouseY)) {
//        if (button == start) {
//          // start the game
//        } else if (button == settings) {
//          // open the settings menu
//          currentScreen = 2; // set the current screen to 2 to draw the settings menu
//        }
//      }
//    }

    if (clickable) {
      if (start.isClicked(mouseX, mouseY)) {
        System.out.println("Starting game...");
        currentScreen = 1;
        clickable = false;
      } else if (quit.isClicked(mouseX, mouseY)) {
        System.out.println("Quitting game...");
        exit();
      } else if (settings.isClicked(mouseX, mouseY)) {
        System.out.println("Opening game settings...");
        currentScreen = 2;
        clickable = false;
        //gameSettings = new GameSettings();
        //gameSettings.init(this);
      } else {
        currentScreen = 3;
      }
    }
  }

  public static void main(String[] args) {
    String[] appletArgs = new String[]{"Jayden Jump"};
    MainMenu mainMenu = new MainMenu();
    PApplet.runSketch(appletArgs, mainMenu);
  }
}
