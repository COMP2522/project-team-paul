package org.bcit.comp2522.JaydenJump;

import processing.core.PApplet;

import java.util.ArrayList;

public class PauseMenu extends Menu {

  private ArrayList<Button> buttons;
  private Button resume;
  private Menu window;
  private int score = 0;

//  public GameMenu(MainMenu mainMenu) {
//    this.mainMenu = mainMenu;
//  }

  public void settings() {
    size(480, 480);
  }

  public void setup(PApplet parent) {
    //frameRate(60);
    //this.parent = parent;
    //this.init();
  }

  public void init(Menu window) {
    this.window = window;
    draw();
  }

  public void draw() {
    window.background(35, 150, 170);
    buttons = new ArrayList<Button>();
    resume = new Button(250, 225, 150, 100, 30, "Resume", window);
    buttons.add(resume);
    for (Button button : buttons) {
      button.draw();
    }
    window.textSize(15);
    window.textAlign(CENTER);
    //score = getCurrentScore();
    window.text("Current score: " + score,  width / 2 + 20, height / 2);
  }

  //@Override
  public void mousePressed() {
    if (resume.isClicked(mouseX, mouseY)) {
      System.out.println("Back to game...");
    }
  }

  public void run() {
//    setup(parent);
//    //init();
//    draw(parent);
//    String[] appletArgs = new String[]{"GameMenu"};
//    GameMenu gameMenu = new GameMenu();
//    PApplet.runSketch(appletArgs, gameMenu);
  }

//  public static void main(String[] args) {
//    String[] appletArgs = new String[]{"Jayden Jump"};
//    GameMenu gameMenu = new GameMenu();
//    PApplet.runSketch(appletArgs, gameMenu);
//  }
}
