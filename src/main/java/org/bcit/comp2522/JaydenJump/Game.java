package org.bcit.comp2522.JaydenJump;

import java.awt.*;
import java.util.Iterator;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

/**
 * Game class.
 *
 * @author Shawn, Birring; Brian Kwon
 * @version 1.3
 */
public class Game extends PApplet {

  /**
   * Instance for the player.
   */
  private static Player player;

  /**
   * Flag for indicating if game is over.
   */
  static boolean gameOver;

  /**
   * Platform manager.
   */
  private static PlatformManager platformManager;

  /**
   * PowerUp Manager.
   */
  private static PowerUpManager powerUpManager;

  /**
   * Coin Manager.
   */
  private static CoinManager coinManager;

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
   * the boss manager.
   */
  private BossManager bossManager;

  /**
   * the background image.
   */
  private final PImage backgroundImage;

  /**
   * the position of the background image.
   */
  private final PVector backgroundPos;

  /**
   * the speed of the background.
   */
  private int scrollSpeed;

  /**
   * the level of the game.
   *
   * @param level the level of the game
   * @param window the window for the game
   * @param powerUpImage the image for the power up
   * @param backgroundImage the image for the background
   * @param enemyImage the image for the enemy
   * @param playerImage the image for the player
   * @param coinImages the images for the coins
   */
  public Game(int level, MenuManager window,
              PImage powerUpImage, PImage backgroundImage, PImage enemyImage,
              PImage playerImage, PImage[] coinImages) {
    this.window = window;
    this.backgroundImage = backgroundImage;
    this.backgroundPos = new PVector(0, 0);
    this.player = Player.getInstance(window, playerImage, 5, 0.5f);

    switch (level) {
      case 1 -> initializeLevel1(coinImages, powerUpImage, enemyImage);
      case 2 -> initializeLevel2(coinImages, powerUpImage, enemyImage);
      case 3 -> initializeLevel3(coinImages, powerUpImage, enemyImage);
    }

    platformManager.generateStartPlatforms();
    powerUpManager.generateStartPowerUps();
    coinManager.generateStartCoins();
    gameOver = false;
  }

  private void initializeLevel1(PImage[] coinImages, PImage powerUpImage, PImage enemyImage) {
    this.scrollSpeed = 4;
    this.platformManager = PlatformManager.getInstance(10, window, 5, 5, 10, 15, player);
    this.powerUpManager = PowerUpManager.getInstance(5, window, 5, player, powerUpImage);
    this.coinManager = CoinManager.getInstance(3, window, 5, player, coinImages);
    this.enemyManager = new EnemyManager(window, 0.5f, enemyImage);
    this.bossManager = new BossManager(MenuManager.getBossImg(), 150, 150, window, player, 1);
  }

  private void initializeLevel2(PImage[] coinImages, PImage powerUpImage,  PImage enemyImage) {
    this.scrollSpeed = 8;
    this.platformManager = PlatformManager.getInstance(10, window, 5, 5, 10, 15, player);
    this.powerUpManager = PowerUpManager.getInstance(5, window, 5, player, powerUpImage);
    this.coinManager = CoinManager.getInstance(3, window, 5, player, coinImages);
    this.enemyManager = new EnemyManager(window, 0.5f, enemyImage);
    this.bossManager = new BossManager(MenuManager.getBossImg(), 150, 150, window, player, 1);
  }

  private void initializeLevel3(PImage[] coinImages, PImage powerUpImage,  PImage enemyImage) {
    this.scrollSpeed = 12;
    this.platformManager = PlatformManager.getInstance(10, window, 5, 5, 10, 15, player);
    this.powerUpManager = PowerUpManager.getInstance(5, window, 5, player, powerUpImage);
    this.coinManager = CoinManager.getInstance(3, window, 5, player, coinImages);
    this.enemyManager = new EnemyManager(window, 0.5f, enemyImage);
    this.bossManager = new BossManager(MenuManager.getBossImg(), 150, 150, window, player, 1);
  }

  /**
   * Draws to window.
   */
  public void draw() {
    drawBackground();
    window.textSize(20);
    window.fill(0);
    window.text("Score: " + Game.getScore(), 50, 50);

    drawPlayerLives(lives);

    if (!gameOver && lives > 0) {
      platformManager.checkCollision();
      powerUpManager.checkCollision(player);
      coinManager.checkCollision(player);

      score++;
      if (score > highscore) {
        highscore = score;
      }

      player.update();
      if (player.getYpos() >= window.height - player.getPlayerSize() / 2) {
        lives--;
        if (lives == 0) {
          bossManager.setIsAlive(false);
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
            bossManager.setIsAlive(false);
            endGame();
          } else {
            restartGame();
          }
          enemyIterator.remove();
        }
      }

      platformManager.updateAndDrawPlatforms();
      powerUpManager.updateAndDrawPowerUps();
      coinManager.updateAndDrawCoins();
      platformManager.generatePlatforms();
      powerUpManager.generatePowerUps();
      coinManager.generateCoins();
      platformManager.checkCollision();
      powerUpManager.checkCollision(player);
      coinManager.checkCollision(player);
      player.draw();

      enemyManager.update();
      enemyManager.draw();

      if (score >= 2000) {
        bossManager.draw();
        bossManager.update();
      }
    }
  }

  /**
   * making the background scroll.
   */
  public void drawBackground() {
    backgroundPos.y += scrollSpeed;
    backgroundPos.x = 0;

    int offsetX = (int) (backgroundPos.x % backgroundImage.width - backgroundImage.width);
    int offsetY = (int) (backgroundPos.y % backgroundImage.height - backgroundImage.height);

    for (int x = offsetX; x < window.width; x += backgroundImage.width) {
      for (int y = offsetY; y < window.height; y += backgroundImage.height) {
        window.image(backgroundImage, x, y);
      }
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
    coinManager.getCoins().clear();
    platformManager.generateStartPlatforms();
    powerUpManager.generateStartPowerUps();
    coinManager.generateStartCoins();
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
  public static void keyPressedListener(int key) {
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
  public static void keyReleasedListener(int key) {
    if (key == LEFT || key == 'A') {
      player.setVx(player.getVx() - 2);
    } else if (key == RIGHT || key == 'D') {
      player.setVx(player.getVx() + 2);
    } else if (key == ' ') {
      player.shootProjectile();
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
   * Setter for score.
   *
   * @return score as an int
   */
  public static int increaseScore(int increase) {
    return score = score + increase;
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

