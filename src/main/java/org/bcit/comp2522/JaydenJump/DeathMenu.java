package org.bcit.comp2522.JaydenJump;

import java.util.ArrayList;

public class DeathMenu extends Menu {

  private ArrayList<Button> buttons;
  private Button playAgain;
  private Button quit;
  private Menu window;
  private int score = 0;
  private int highscore = 69;

  public void settings() {
    size(480, 480);
  }

  public void init(Menu window) {
    this.window = window;
    draw();
  }

  public void draw() {
    window.background(35, 150, 170);
    window.textSize(30);
    window.textAlign(CENTER);
    window.text("Game Over!", width * 2 + 50, height);
    window.textSize(25);
    //score = getCurrentScore();
    //highscore = getHighscore();
    window.text("Score: " + score, width * 2 + 40, height * 2 + 25);
    window.text("Highscore: " + highscore, width * 2 + 40, height * 2 + 50);
    buttons = new ArrayList<Button>();
    playAgain = new Button(250, 325, 125, 75, 25, "Play again", window);
    quit = new Button(250, 425, 125, 75, 25,"Quit", window);
    buttons.add(playAgain);
    buttons.add(quit);
    for (Button button : buttons) {
      button.draw();
    }
  }
}