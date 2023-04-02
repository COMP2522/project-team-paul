package org.bcit.comp2522.JaydenJump;

import java.util.Iterator;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * Game class.
 *
 * @author Shawn, Birring; Brian Kwon
 * @version 1.2
 */
public class Game extends PApplet {

  /**
   * Instance for the player.
   */
  private static Player player;

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
  static boolean gameOver;

  /**
   * Platform manager.
   */
  private static PlatformManager platformManager = null;

  /**
   * PowerUp Manager.
   */
  private static PowerUpManager powerUpManager = null;

  /**
   * Window for displaying game.
   */
  private static MenuManager window;

  /**
   * Current score.
   */
  private static int score = 0;

  /**
   * Highest score achieved in the game so far.
   */
  private static int highscore = 0;

  /**
   * The lives of the player.
   */
  private static int lives = 3;

  /**
   * Manager for the enemies.
   */
  private EnemyManager enemyManager;

  /**************************************************/

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
              PImage powerUpImg, EnemyManager enemy
              ) {
    this.window = window;
    this.jumpHeight = jumpHeight;
    this.minDoubleJumpHeight = minDoubleJumpHeight;
    this.player = player;
    this.platformManager = PlatformManager.getInstance(maxPlatforms, window,
                                                      platformSpeed, platformMoveableSpeed);
    this.powerUpManager = PowerUpManager.getInstance(maxPowerUps, window,
        platformSpeed, player, powerUpImg);
    platformManager.generateStartPlatforms();
    powerUpManager.generateStartPowerUps();
    this.gameOver = false;
    this.enemyManager = enemy;
  }

  /**
   * Draws to window.
   */
  public void draw() {
    window.background(255);
    window.textSize(30);
    window.fill(0);
    window.text("" + Game.getScore(), 50, 50);

    drawPlayerLives(lives);

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

      Iterator<Enemy> enemyIterator = enemyManager.getEnemies().iterator();
      while (enemyIterator.hasNext()) {
        Enemy enemy = enemyIterator.next();
        if (enemy.collides(player)) {
          lives--;
          if (lives == 0) {
            endGame();
          } else {
            restartGame();
          }
          enemyIterator.remove();
        }
      }

      platformManager.updateAndDrawPlatforms();
      powerUpManager.updateAndDrawPowerUps();
      platformManager.generatePlatforms();
      powerUpManager.generatePowerUps();
      platformManager.checkCollision(player, minDoubleJumpHeight, jumpHeight);
      powerUpManager.checkCollision(player);
      player.draw();
      enemyManager.draw();
      enemyManager.update();
    }
  }

  /**
   * Draws appropriate number of hearts based on number of lives remaining.
   *
   * @param lives as an int
   */
  public void drawPlayerLives(int lives) {
    int[] heartPositions = {400, 337, 275};

    for (int i = 0; i < lives; i++) {
      drawHeart(heartPositions[i]);
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
   * Restarts the game when the player goes below
   * the screen or makes contact with an enemy.
   */
  public static void restartGame() {
    player.reset(window.width / 2, 0, 0, 0);
    platformManager.getPlatforms().clear();
    powerUpManager.getPowerups().clear();
    platformManager.generateStartPlatforms();
    platformManager.generatePlatforms();
    gameOver = false;
  }

  /**
   * Ends the game when the player runs out of lives.
   */
  public static void endGame() {
    gameOver = true;
    lives = 3;
  }

  /**
   * Starts the game.
   */
  public static void startGame() {
    score = 0;
  }

  public static void resetHighscore() {
    highscore = 0;
  }

  /**
   * Event listener for key presses.
   */
  public void keyPressedListener(int key) {
    if (key == LEFT || key == 'A') {
      getPlayer().moveLeft();
    } else if (key == RIGHT || key == 'D') {
      getPlayer().moveRight();
    } else if (key == 'P') {
      if (MenuManager.getCurrentScreen() == 7) {
        MenuManager.setCurrentScreen(5);
      } else if (MenuManager.getCurrentScreen() == 5) {
        MenuManager.setCurrentScreen(7);
      }
    }
  }

  /**
   * Event listener for key releases.
   */
  public void keyReleasedListener(int key) {
    if (key == LEFT || key == 'A') {
      getPlayer().setVx(player.getVx() - 2);
    } else if (key == RIGHT || key == 'D') {
      getPlayer().setVx(player.getVx() + 2);
    } else if (key == ' ') {
      getPlayer().shootProjectile();
    }
  }

  /**
   * Getter for Player object.
   *
   * @return player as a Player object
   */
  public static Player getPlayer() {
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
