package org.bcit.comp2522.JaydenJump;

import java.util.Iterator;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;


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
  private final PlatformManager platformManager;

  /**
   * PowerUp Manager.
   */
  private final PowerUpManager powerUpManager;
  private final CoinManager coinManager;

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

  private static int maxCoins = 3;


  /**
   * Image of PowerUp.
   */
  private PImage powerUpImg;

  /**
   * Manager for the enemies.
   */
  private EnemyManager enemyManager;

  /**
   * the boss.
   */
  private Boss boss;

  /**
   * the background image.
   */
  private PImage backgroundImage;

  /**
   * the position of the background image.
   */
  private PVector backgroundPos;

  /**
   * the speed of the background.
   */
  private int scrollSpeed;

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
              PImage powerUpImg,
              EnemyManager enemy,
              Boss boss,
              PImage[] coinImg,
              PImage backgroundImage) {

    this.window = window;
    this.jumpHeight = jumpHeight;
    this.minDoubleJumpHeight = minDoubleJumpHeight;
    this.player = player;
    this.platformManager = PlatformManager.getInstance(maxPlatforms, window,
                                                      platformSpeed, platformMoveableSpeed);
    this.powerUpManager = PowerUpManager.getInstance(maxPowerUps, window, platformSpeed, player, powerUpImg);
    this.coinManager = CoinManager.getInstance(maxCoins, window, platformSpeed, player, coinImg);
    platformManager.generateStartPlatforms();
    powerUpManager.generateStartPowerUps();
    coinManager.generateStartCoins();
    this.gameOver = false;
    this.enemyManager = enemy;
    this.boss = boss;
    this.backgroundImage = backgroundImage;
    this.backgroundPos = new PVector(0, 0);
    this.scrollSpeed = 1;
  }


  /**
   * draw the game, called at every frame.
   */
  public void draw() {
    drawBackground();
    window.textSize(30);
    window.fill(0);
    window.text("Score: " + Game.getScore(), 50, 50);

    drawHearts(lives);


    if (!gameOver && lives > 0) {
      platformManager.checkCollision(player, minDoubleJumpHeight, jumpHeight);
      powerUpManager.checkCollision(player);
      coinManager.checkCollision(player);

      score++;
      if (score > highscore) {
        highscore = score;
      }

      player.update();
      if (player.getYpos() >= window.height - player.getImgSize() / 2) {
        lives--;
        if (lives == 0) {
          bossReset();
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
            bossReset();
            endGame();
          } else {
            restartGame();
          }
          enemyIterator.remove();
        }
      }

      if (boss.collides(player)) {
        lives--;
        if (lives == 0) {
          bossReset();
          endGame();
        } else {
          restartGame();
        }
      }

      if (boss.collides(player.getProjectile())) {
        boss.setHealth(boss.getHealth() - player.getProjectile().getDamage());
        player.setShooting(false);
        if (boss.getHealth() == 0) {
          boss.getBosses().clear();
        }
      }

      if (score >= 1000) {
        for (Boss boss : boss.getBosses()) {
          boss.update();
          boss.draw();
        }
      }


      platformManager.updateAndDrawPlatforms();
      powerUpManager.updateAndDrawPowerUps();
      coinManager.updateAndDrawCoins();
      platformManager.generatePlatforms();
      powerUpManager.generatePowerUps();
      coinManager.generateCoins();
      platformManager.checkCollision(player, minDoubleJumpHeight, jumpHeight);
      powerUpManager.checkCollision(player);
      coinManager.checkCollision(player);
      player.draw();
      enemyManager.draw();
      enemyManager.update();

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
    player.reset(width / 2, 200, 0, 0);
    platformManager.getPlatforms().clear();
    powerUpManager.getPowerups().clear();
    coinManager.getCoins().clear();
    platformManager.generateStartPlatforms();
    powerUpManager.generateStartPowerUps();
    coinManager.generateStartCoins();
    gameOver = false;
  }

  /**
   * end the game.
   * called when the player goes above or below the screen limits.
   */
  public static void endGame() {
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
    } else if (key == 81) {
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

  /**
   * Reset the boss after the player dies.
   */
  public void bossReset() {
    if (boss.getBosses().isEmpty()) {
      boss = new Boss(170, 10, 5, 0, 150, 150, MenuManager.getBossImg(), boss.getSketch(),
              player, 5);
      boss.getBosses().add(boss);
    }
  }
}
