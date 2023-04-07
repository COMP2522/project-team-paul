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

  /**
   * Constant.
   */
  private static final int FONT_SIZE = 30;

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
    window.textSize(FONT_SIZE);
    window.textAlign(CENTER);

    userInput = JOptionPane.showInputDialog("Please enter your name:");

    Load.updateLeaderboard(userInput, Game.getHighscore(), MenuManager.getDifficulty());
    Game.restartGame();
    Game.startGame();
    Game.resetHighscore();
    MenuManager.setCurrentScreen(1);
  }
}
