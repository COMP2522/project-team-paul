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
   * PowerUp Manager.
   */
  private final PowerUpManager powerUpManager;

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

  /**
   * The lives of the player
   */
  private static int lives = 3;

  /**
   * Image of PowerUp
   */
  private PImage powerUpImg;

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
              int maxPowerUps,
              int maxPlatforms,
              Player player,
              int platformSpeed,
              int platformMoveableSpeed,
              MenuManager window,
              PImage powerUpImg
              ) {
    this.window = window;
    this.jumpHeight = jumpHeight;
    this.minDoubleJumpHeight = minDoubleJumpHeight;
    this.player = player;
    this.platformManager = PlatformManager.getInstance(maxPlatforms, window,
                                                      platformSpeed, platformMoveableSpeed);
    this.powerUpManager = PowerUpManager.getInstance(maxPowerUps, window, platformSpeed, player, powerUpImg);
    platformManager.generateStartPlatforms();
    powerUpManager.generateStartPowerUps();
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

    drawHearts(lives);

    if (!gameOver && lives > 0) {
      platformManager.checkCollision(player, minDoubleJumpHeight, jumpHeight);
      powerUpManager.checkCollision(player);

      score++;
      if (score > highscore) {
        highscore = score;
      }

      player.update();
      if (player.getYpos() >= window.height - player.getImgSize() / 2) {
        lives--;
        if (lives == 0) {
          endGame();
        } else {
          restartGame();
        }
      }
      platformManager.updateAndDrawPlatforms();
      powerUpManager.updateAndDrawPowerUps();
      platformManager.generatePlatforms();
      powerUpManager.generatePowerUps();
      platformManager.checkCollision(player, minDoubleJumpHeight, jumpHeight);
      powerUpManager.checkCollision(player);
      player.draw();
    }
  }

  /**
   * Draws appropriate number of hearts based on number of lives remaining.
   *
   * @param lives as an int
   */
  public void drawHearts(int lives) {
    if (lives == 3) {
      drawHeart(400);
      drawHeart(337);
      drawHeart(275);
    } else if (lives == 2) {
      drawHeart(400);
      drawHeart(337);
    } else if (lives == 1) {
      drawHeart(400);
    }
  }

  /**
   * Draws a heart shape to window.
   *
   * @param x as an int
   */
  public void drawHeart(int x) {
    window.fill(255, 0, 0);
    window.beginShape();
    window.vertex(width / 2 + x, height / 4);
    window.bezierVertex(width / 4 + x, 0, 0 + x, height / 3, width / 2 + x, height / 2);
    window.bezierVertex(width + x, height / 3, 3 * width / 4  + x, 0, width / 2 + x, height / 4);
    window.endShape();
  }

  /**
   * restart the game.
   */
  public void restartGame() {
    player.reset(width / 2, 0, 0, 0);
    platformManager.getPlatforms().clear();
    powerUpManager.getPowerups().clear();
    platformManager.generateStartPlatforms();
    platformManager.generatePlatforms();
    gameOver = false;
  }

  /**
   * end the game.
   * called when the player goes above or below the screen limits.
   */
  public void endGame() {
    gameOver = true;
    lives = 3;
  }

  public static void startGame() {
    score = 0;
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
      } else if (MenuManager.getCurrentScreen() == 6) {
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

  /**
   * Getter for Player object.
   *
   * @return player as a Player object
   */
  public Player getPlayer() {
    return player;
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
   * Retrieves the player's current lives in game.
   *
   * @return player's life count while in game.
   */
  public static int getLives() {
    return lives;
  }

  /**
   * Changes Player's lives to a specific amount.
   *
   * @param lives increased
   */
  public static void setLives(int lives) {
    Game.lives = lives;
  }
}
