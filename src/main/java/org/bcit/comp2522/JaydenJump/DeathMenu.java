package org.bcit.comp2522.JaydenJump;

import java.util.ArrayList;

public class DeathMenu extends Menu {

  /**
   * Instance variables.
   */
  private Menu window;
  private ArrayList<Button> buttons;
  private int score = 0;
  private int highscore = 0;
  Button playAgain;
  Button leaderboards;

  /**
   * Initializes DeathMenu object.
   *
   * @param window as a Menu object
   */
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
    leaderboards = new Button(250, 425, 125, 75, 20,"Leaderboards", window);
    buttons.add(playAgain);
    buttons.add(leaderboards);
    for (Button button : buttons) {
      button.draw();
    }
  }
}
