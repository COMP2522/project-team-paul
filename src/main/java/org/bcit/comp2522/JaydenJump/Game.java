package org.bcit.comp2522.JaydenJump;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * Game class.
 *
 * @author Shawn, Birring; Brian Kwon
 * @version 1.1
 */
public class Game extends PApplet {

  /**
   * Instance for the player.
   */
  private final Player player;

  /**
   * the height of the jump.
   */
  private final int jumpHeight;

  /**
   * the minimum height of the double jump.
   */
  private final int minDoubleJumpHeight;

  /**
   * Flag for indicating if game is over.
   */
  boolean gameOver;

  /**
   * Platform manager.
   */
  private final PlatformManager platformManager;

  /**
   * Window for displaying game.
   */
  private MenuManager window;

  /**
   * Current score.
   */
  private static int score = 0;

  /**
   * Highest score achieved in the game so far.
   */
  private static int highscore = 0;

  /*************************************************/

  /**
   * the constructor for the game class.
   *
   * @param jumpHeight the height of the jump
   *
   * @param minDoubleJumpHeight the minimum height of the double jump
   *
   * @param maxPlatforms the maximum number of platforms that can be on the screen at once
   */
  public Game(int jumpHeight,
              int minDoubleJumpHeight,
              int maxPlatforms,
              Player player,
              int platformSpeed,
              int platformMoveableSpeed,
              MenuManager window,
              PImage playerImg) {
    this.window = window;
    this.jumpHeight = jumpHeight;
    this.minDoubleJumpHeight = minDoubleJumpHeight;
    this.player = player;
    this.player.setSketch(window);
    this.player.setImage(playerImg);
    this.platformManager = PlatformManager.getInstance(maxPlatforms, window,
                                                      platformSpeed, platformMoveableSpeed);
    platformManager.generateStartPlatforms();
    this.gameOver = false;
  }

  /**
   * draw the game, called at every frame.
   */
  public void draw() {
    window.background(255);
    window.textSize(30);
    window.fill(0);
    window.text("" + Game.getScore(), 50, 50);

    if (!gameOver) {
      platformManager.checkCollision(player, minDoubleJumpHeight, jumpHeight);

      score++;
      if (score > highscore) {
        highscore = score;
      }

      player.update();
      if (player.getYpos() >= window.height - player.getImgSize() / 2) {
        endGame();
      }
      platformManager.updateAndDrawPlatforms();
      platformManager.generatePlatforms();
      platformManager.checkCollision(player, minDoubleJumpHeight, jumpHeight);
      player.draw();
    }
  }

  /**
   * Getter for score.
   *
   * @return score as an int
   */
  public static int getScore() {
    return score;
  }

  /**
   * Getter for highscore.
   *
   * @return highscore as an int
   */
  public static int getHighscore() {
    return highscore;
  }

  /**
   * restart the game.
   */
  public void restartGame() {
    player.reset(width / 2, 0, 0, 0);
    platformManager.getPlatforms().clear();
    platformManager.generateStartPlatforms();
    gameOver = false;
    score = 0;
  }

  /**
   * Getter for Player object.
   *
   * @return player as a Player object
   */
  public Player getPlayer() {
    return player;
  }

  /**
   * end the game.
   * called when the player goes above or below the screen limits.
   */
  public void endGame() {
    gameOver = true;
  }

  /**
   * check for key presses and call the appropriate methods.
   */
  public void keyPressedListener(int key) {
    if (key == LEFT || key == 'A') {
      getPlayer().moveLeft();
    } else if (key == RIGHT || key == 'D') {
      getPlayer().moveRight();
    } else if (key == 'P') {
      if (MenuManager.getCurrentScreen() == 1) {
        MenuManager.setCurrentScreen(6);
      } else if (MenuManager.getCurrentScreen() == 6){
        MenuManager.setCurrentScreen(1);
      }
    }
  }

  /**
   * test this method better, but it should.
   * reduce how fast the player can move left and right.
   * after letting go of left/right or a/d.
   */
  public void keyReleasedListener(int key) {
    if (key == LEFT || key == 'A') {
      getPlayer().setVx(player.getVx() - 2);
    } else if (key == RIGHT || key == 'D') {
      getPlayer().setVx(player.getVx() + 2);
    }
  }
}
