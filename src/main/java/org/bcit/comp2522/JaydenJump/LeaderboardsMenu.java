package org.bcit.comp2522.JaydenJump;

import processing.core.PApplet;

/**
 * Leaderboards menu.
 *
 * @author Brian Kwon
 * @version 1.2
 */
public class LeaderboardsMenu extends PApplet {

  /**
   * Window that contains menu screen.
   */
  private MenuManager window;

  /**
   * Button used to return to the main menu.
   */
  Button back;

  /**
   * Array of player names, score, and difficulties from database.
   */
  String[] playerData;

  /**
   * Constants.
   */
  private static final int BUTTON_X = 50;
  private static final int BUTTON_Y = 50;
  private static final int BUTTON_WIDTH_HEIGHT = 75;
  private static final int FONT_SIZE = 20;
  private static final int FONT_SIZE_BIG = 30;
  private static final int FONT_SIZE_SMALL = 15;
  private static final int TOP_PLAYERS = 5;
  private static final int GAP = 20;
  private static final int HARD_Y = 600;

  /***************************************************/

  /**
   * Initializes LeaderboardsMenu object.
   *
   * @param playerData as a string
   */
  public void init(String[] playerData) {
    this.window = MenuManager.getInstance();
    this.playerData = playerData;
    draw();
  }

  /**
   * Draws to window.
   */
  public void draw() {
    drawBackground();
    drawBackButton();
    drawTitles();
    displayLeaderboardData();
  }

  /**
   * Draws background colour.
   */
  private void drawBackground() {
    window.background(35, 150, 170);
  }

  /**
   * Draws button.
   */
  private void drawBackButton() {
    back = new Button(BUTTON_X, BUTTON_Y, BUTTON_WIDTH_HEIGHT, BUTTON_WIDTH_HEIGHT, FONT_SIZE, "Back", window);
    back.draw();
  }

  /**
   * Draws titles.
   */
  private void drawTitles() {
    window.textAlign(CENTER);
    window.textSize(FONT_SIZE_BIG);
    window.text("Leaderboards", window.width / 2, window.height / 8);
    window.textSize(FONT_SIZE);
    window.text("PLAYER", window.width / 4, window.height / 5);
    window.text("SCORE", window.width / 2, window.height / 5);
    window.text("DIFFICULTY", window.width - window.width / 4, window.height / 5);
    window.textSize(FONT_SIZE_SMALL);
  }

  /**
   * Displays leaderboard data.
   */
  private void displayLeaderboardData() {
    displayDataByDifficulty("1", "Easy", window.height / 4);
    displayDataByDifficulty("2", "Medium", window.height / 2);
    displayDataByDifficulty("3", "Hard", HARD_Y);
  }

  /**
   * Displays player data by difficulty.
   *
   * @param difficulty as a string
   * @param difficultyLabel as a stirng
   * @param baseY as an int
   */
  private void displayDataByDifficulty(String difficulty, String difficultyLabel, int baseY) {
    int count = 1;
    for (int i = 0; i < playerData.length && count <= TOP_PLAYERS; i++) {
      String[] arr = playerData[i].split(":");
      if (arr[2].equals(difficulty)) {
        window.text(count, window.width / 8, baseY + count * GAP);
        window.text(arr[0], window.width / 4, baseY + count * GAP);
        window.text(arr[1], window.width / 2, baseY + count * GAP);
        window.text(difficultyLabel, window.width - window.width / 4, baseY + count * GAP);
        count++;
      }
    }
  }
}
