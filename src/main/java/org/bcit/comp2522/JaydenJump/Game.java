package org.bcit.comp2522.JaydenJump;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

import java.util.Iterator;

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
  private static int scrollSpeed;

  /**
   * the level of the game.
   *
   * @param diff the level of the game
   * @param sketch the window for the game
   * @param powerUpImage the image for the power up
   * @param backgroundImage the image for the background
   * @param enemyImage the image for the enemy
   * @param playerImage the image for the player
   * @param coinImages the images for the coins
   */
  public Game(int diff, MenuManager sketch,
              PImage[] powerUpImage, PImage backgroundImage, PImage enemyImage,
              PImage[] playerImage, PImage[] coinImages) {
    window = sketch;
    this.backgroundImage = backgroundImage;
    this.backgroundPos = new PVector(0, 0);
    Level level = new Level(diff);
    initializeLevel(level, coinImages, powerUpImage, enemyImage, playerImage);
    platformManager.generateStartPlatforms();
    powerUpManager.generateStartPowerUps();
    coinManager.generateStartCoins();
    gameOver = false;
  }

  private void initializeLevel(Level level, PImage[] coinImages, PImage[] powerUpImage,
                               PImage enemyImage, PImage[] playerImage) {
    player = Player.getInstance(window, playerImage, level.getPlayerSpeed(), level.getGravity());
    scrollSpeed = level.getScrollSpeed();
    platformManager = PlatformManager.getInstance(level.getMaxPlatform(), window,
            level.getPlatformSpeed(), level.getMoveableSpeed(), level.getJumpThroughHeight(),
            level.getPlayerJumpHeight(), player);
    powerUpManager = PowerUpManager.getInstance(level.getMaxPowerUps(), window,
            level.getPowerUpSpeed(), player, powerUpImage);
    coinManager = CoinManager.getInstance(level.getMaxCoins(), window, level.getCoinSpeed(),
            player, coinImages);
    this.enemyManager = new EnemyManager(window, level, enemyImage);
    this.bossManager = new BossManager(MenuManager.getBossImg(), 150, 150, window,
            player, level);
  }

  /**
   * Draws to window.
   * called every frame.
   */
  public void draw() {
    drawBackground();
    displayScore();
    drawPlayerLives(lives);

    if (!gameOver && lives > 0) {
      checkCollisions();
      updateScoreAndHighscore();

      if (playerReachedGround()) {
        handlePlayerLanding();
      }

      handleEnemyCollisions();
      updateAndDrawGameElements();
      generateGameElements();

      if (score >= 2000) {
        drawAndUpdateBoss();
      }
    }
  }

  /**
   * draws the score on the screen.
   */
  private void displayScore() {
    window.textSize(20);
    window.fill(0);
    window.text("Score: " + Game.getScore(), 50, 50);
  }

  /**
   * checks for any collisions and handles them.
   */
  private void checkCollisions() {
    platformManager.checkCollision();
    powerUpManager.checkCollision(player);
    coinManager.checkCollision(player);
  }

  /**
   * updates the score of the game.
   */
  private void updateScoreAndHighscore() {
    score++;
    if (score > highscore) {
      highscore = score;
    }
  }

  /**
   * checks if the player has reached the ground.
   *
   * @return true if the player has reached the ground
   */
  private boolean playerReachedGround() {
    return player.getYpos() >= window.height - player.getPlayerSize() / 2f;
  }

  /**
   * checks if the player has landed on the ground.
   */
  private void handlePlayerLanding() {
    lives--;
    if (lives == 0) {
      bossManager.setIsAlive(false);
      endGame();
    }
  }

  /**
   * TODO: will need to put this in a separate class.
   */
  private void handleEnemyCollisions() {
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
  }

  /**
   * updates and draws the game elements.
   */
  private void updateAndDrawGameElements() {
    platformManager.updateAndDrawPlatforms();
    powerUpManager.updateAndDrawPowerUps();
    coinManager.updateAndDrawCoins();
    player.update();
    player.draw();
    enemyManager.update();
    enemyManager.draw();
  }

  /**
   * generates the game elements.
   */
  private void generateGameElements() {
    platformManager.generatePlatforms();
    powerUpManager.generatePowerUps();
    coinManager.generateCoins();
  }

  /**
   * draws and updates the boss.
   */
  private void drawAndUpdateBoss() {
    bossManager.draw();
    bossManager.update();
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
    window.vertex(width / 2f + x, height / 4f);
    window.bezierVertex(width / 4f + x, 0, x, height / 3f,
            width / 2f + x, height / 2f);
    window.bezierVertex(width + x, height / 3f, 3 * width / 4f  + x,
            0, width / 2f + x, height / 4f);
    window.endShape();
  }

  /**
   * Restarts the game when the player goes below
   * the screen or makes contact with an enemy.
   */
  public static void restartGame() {
    player.reset();
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
      player.moveLeft();
    } else if (key == RIGHT || key == 'D') {
      player.moveRight();
    } else if (key == 'P') {
      if (MenuManager.getCurrentScreen() == 7) {
        MenuManager.setCurrentScreen(5);
      } else if (MenuManager.getCurrentScreen() == 5) {
        MenuManager.setCurrentScreen(7);
      }
    } else if (key == ' ') {
      player.shootProjectile();
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

