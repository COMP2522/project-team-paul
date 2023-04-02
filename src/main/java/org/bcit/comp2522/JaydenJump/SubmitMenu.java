package org.bcit.comp2522.JaydenJump;

import javax.swing.JOptionPane;
import processing.core.PApplet;

/**
 * Highscore submission menu.
 */
public class SubmitMenu extends PApplet {

  /**
   * Window that contains menu screen.
   */
  private MenuManager window;

  /**
   * User input for player name.
   */
  String userInput;

  /***************************************************/

  public void init(MenuManager window) {
    this.window = window;
    draw();
  }

  /**
   * Draws to window.
   */
  public void draw() {
    window.background(35, 150, 170);
    window.textSize(30);
    window.textAlign(CENTER);

    userInput = JOptionPane.showInputDialog("Please enter your name:");

    //Load load = new Load();
    Load.updateLeaderboard(userInput, Game.getHighscore(), 1);
    Game.restartGame();
    Game.startGame();
    Game.resetHighscore();
    MenuManager.setCurrentScreen(1);

    //window.text(userInput, window.width / 2, window.height / 2);
  }
}
