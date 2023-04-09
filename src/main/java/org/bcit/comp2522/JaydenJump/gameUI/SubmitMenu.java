package org.bcit.comp2522.JaydenJump.gameUI;

import javax.swing.JOptionPane;

import org.bcit.comp2522.JaydenJump.Game;
import org.bcit.comp2522.JaydenJump.Load;
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

  private Game game;

  /**
   * Constant.
   */
  private static final int FONT_SIZE = 30;

  /***************************************************/

  /**
   * Initializes SubmitMenu object.
   */
  public void init() {
    this.window = MenuManager.getInstance();
    this.game = window.getGame();
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

    Load.updateLeaderboard(userInput, game.getHighscore(), MenuManager.getDifficulty());
    game.restartGame();
    game.startGame();
    game.resetHighscore();
    MenuManager.setCurrentScreen(1);
  }
}
