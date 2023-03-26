package org.bcit.comp2522.JaydenJump;

import processing.core.PApplet;

import java.util.ArrayList;

/**
 * Death menu.
 *
 * @author Brian Kwon
 * @version 1.0
 */
public class DeathMenu extends PApplet {

  /**
   * Window that contains menu screen.
   */
  private MenuManager window;

  /**
   * List of buttons displayed on menu screen.
   */
  private ArrayList<Button> buttons;

  /**
   * Player's score at the end of the game.
   */
  private int score = 0;

  /**
   * Player's highest score achieved in the game so far.
   */
  private int highscore = 0;

  /**
   * Button used to start a new game.
   */
  Button playAgain;

  /**
   * Button used to access leaderboards.
   */
  Button home;

  /********************************************************/

  /**
   * Initializes DeathMenu object.
   *
   * @param window as a Menu object
   */
  public void init(MenuManager window) {
    this.window = window;
    draw();
  }

  public void draw() {
    window.background(35, 150, 170);
    window.textSize(30);
    window.textAlign(CENTER);
    window.text("Game Over!", width * 2 + 50, height);
    window.textSize(25);
    window.text("Score: " + Game.getScore(), width * 2 + 40, height * 2 + 25);
    window.text("Highscore: " + Game.getHighscore(), width * 2 + 40, height * 2 + 50);
    buttons   = new ArrayList<Button>();
    playAgain = new Button(250, 325, 125, 75, 25, "Play again", window);
    home      = new Button(250, 425, 125, 75, 20,"Home", window);
    buttons.add(playAgain);
    buttons.add(home);
    for (Button button : buttons) {
      button.draw();
    }
  }
}
