package org.bcit.comp2522.JaydenJump;

import org.bcit.comp2522.JaydenJump.gameUI.MenuManager;
import org.bcit.comp2522.JaydenJump.spriteManagers.*;
import org.bcit.comp2522.JaydenJump.sprites.Player;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

/**
 * Game class.
 *
 * @author Shawn, Birring; Brian Kwon; Maximillian Yong
 * @version 1.4
 */
public class Game extends PApplet {

  /**
   * Constants.
   */
  private static final int BOSS_SPAWN = 200;
  private static final int FONT_SIZE = 20;
  private static final int X_OFFSET = 120;
  private static final int Y_OFFSET = 30;
  private static final int HEART1 = 400;
  private static final int HEART2 = 337;
  private static final int HEART3 = 275;
  private static final int PLAYER_LIVES = 3;
  private static final int PAUSE_MENU = 5;
  private static final int GAME_PAGE = 7;

  /**
   * The number of lives the boss has.
   */
  private static final int BOSS_HEALTH = 3;
  /**
   * Instance for the player.
   */
  private Player player;

  /**
   * Flag for indicating if game is over.
   */
  private static boolean gameOver;

  /**
   * Platform manager.
   */
  private PlatformManager platformManager;

  /**
   * PowerUp Manager.
   */
  private PowerUpManager powerUpManager;

  /**
   * Coin Manager.
   */
  private CoinManager coinManager;

  /**
   * Window for displaying game.
   */
  private final MenuManager window;

  /**
   * Current score.
   */
  static int score;

  /**
   * Highest score achieved in the game so far.
   */
  static int highscore;

  /**
   * The lives of the player.
   */
  static int lives;

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
  private int scrollSpeed;

  /**
   * the difficulty of the game.
   */
  private int difficulty;

  /**
   * the level of the game.
   */
  private Level level;

  /**
   * the local highscore.
   */
  private int localHighscore;

  /**
   * the level of the game.
   *
   * @param diff            the level of the game
   * @param powerUpImage    the image for the power up
   * @param backgroundImage the image for the background
   * @param coinImages      the images for the coins
   */
  public Game(int diff, PImage[] powerUpImage, PImage backgroundImage,
              PImage[] coinImages) {
    this.difficulty      = diff;
    window               = MenuManager.getInstance();
    this.backgroundImage = backgroundImage;
    this.backgroundPos   = new PVector(0, 0);
    this.level          = Level.getInstance(diff);
    gameOver             = false;
    initializeLevel(level, coinImages, powerUpImage);
    level.startTime();
    platformManager.generateStartPlatforms();
    powerUpManager.generateStartPowerUps();
    coinManager.generateStartCoins();
  }

  /**
   * Initializes the game required objects with values needed.
   *
   * @param level          the level object with all the information
   * @param coinImages     the images for the coins
   * @param powerUpImage   the image for the power up
   */
  private void initializeLevel(Level level, PImage[] coinImages, PImage[] powerUpImage) {
    player          = Player.getInstance(level);
    scrollSpeed     = level.getScrollSpeed();
    platformManager = PlatformManager.getInstance(level);
    powerUpManager  = PowerUpManager.getInstance(level, powerUpImage, this);
    coinManager     = CoinManager.getInstance(level, coinImages, this);
    enemyManager    = EnemyManager.getInstance(level, this);
    bossManager     = BossManager.getInstance(level, this);
    score           = 0;
    highscore       = 0;
    lives           = PLAYER_LIVES;
  }

  /**
   * Draws to window.
   * called every frame.
   */
  public void draw() {
    drawBackground();
    displayScore();
    drawPlayerLives(lives);
    scrollSpeed = level.getScrollSpeed();

    if (!gameOver && lives > 0) {
      checkCollisions();
      updateScoreAndHighscore();

      if (playerReachedGround()) {
        handlePlayerLanding();
      }

      updateAndDrawGameElements();
      generateGameElements();

      if (score >= BOSS_SPAWN) {
        drawAndUpdateBoss();
      }
    }
  }

  /**
   * draws the score on the screen.
   */
  private void displayScore() {
    this.localHighscore = level.getScore();
    window.textSize(FONT_SIZE);
    window.fill(0);
    window.text("HighScore: " + localHighscore
            + "   Score: " + score, X_OFFSET, Y_OFFSET);
  }

  /**
   * checks for any collisions and handles them.
   */
  private void checkCollisions() {
    platformManager.checkCollision();
    powerUpManager.checkCollision();
    coinManager.checkCollision();
    enemyManager.checkCollision();
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
    restartGame();
    if (lives == 0) {
      bossManager.setIsAlive(false);
      bossManager.setBossHealth(BOSS_HEALTH);
      endGame();
    }
  }

  /**
   * updates and draws the game elements.
   */
  private void updateAndDrawGameElements() {
    platformManager.updateAndDrawPlatforms();
    powerUpManager.updateAndDrawPowerUps();
    coinManager.updateAndDrawCoins();
    enemyManager.update();
    enemyManager.draw();
    player.update();
    player.draw();
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
    int[] heartPositions = {HEART1, HEART2, HEART3};
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
    int heartColorRed = 255;
    float controlPoint1X = width / 4f + x;
    float controlPoint2X = 3 * width / 4f + x;
    float startPointX = width / 2f + x;
    float startPointY = height / 4f;
    float endPointY = height / 2f;
    float controlPointY = 0;
    float anchorPointY = height / 3f;

    window.fill(heartColorRed, 0, 0);
    window.beginShape();
    window.vertex(startPointX, startPointY);
    window.bezierVertex(controlPoint1X, controlPointY, x, anchorPointY, startPointX, endPointY);
    window.bezierVertex(width + x, anchorPointY, controlPoint2X, controlPointY, startPointX,
            startPointY);
    window.endShape();
  }


  /**
   * Restarts the game when the player goes below
   * the screen or makes contact with an enemy.
   */
  public void restartGame() {
    player.reset();
    platformManager.getPlatforms().clear();
    powerUpManager.getPowerups().clear();
    coinManager.getCoins().clear();
    platformManager.generateStartPlatforms();
    powerUpManager.generateStartPowerUps();
    coinManager.generateStartCoins();
    level.getInstance(difficulty);
    gameOver = false;
  }

  /**
   * Ends the game when the player runs out of lives.
   */
  public void endGame() {
    gameOver = true;
    lives = PLAYER_LIVES;
  }

  /**
   * Starts the game.
   */
  public void startGame() {
    score = 0;
    level.startTime();
  }

  public void resetHighscore() {
    highscore = 0;
  }

  /**
   * Event listener for key presses.
   */
  public void keyPressedListener(int key) {
    switch (key) {
      case LEFT, 'A' -> player.moveLeft();
      case RIGHT, 'D' -> player.moveRight();
      case 'P' -> toggleMenuScreen();
      case ' ' -> player.shootProjectile();
    }
  }

  /**
   * Toggles the menu screen.
   * the pause menu.
   */
  private void toggleMenuScreen() {
    int currentScreen = MenuManager.getCurrentScreen();
    switch (currentScreen) {
      case PAUSE_MENU -> MenuManager.setCurrentScreen(GAME_PAGE);
      case GAME_PAGE -> MenuManager.setCurrentScreen(PAUSE_MENU);
    }
  }


  /**
   * Event listener for key releases.
   */
  public void keyReleasedListener(int key) {
    switch (key) {
      case LEFT, 'A' -> player.setVx(player.getVx() - 2);
      case RIGHT, 'D' -> player.setVx(player.getVx() + 2);
      case ' ' -> player.shootProjectile();
    }
  }

  /**
   * Getter for score.
   *
   * @return score as an int
   */
  public int getScore() {
    return score;
  }

  /**
   * Setter for score.
   */
  public void increaseScore(int increase) {
    score = score + increase;
  }

  /**
   * Getter for high-score.
   *
   * @return high-score as an int
   */
  public int getHighscore() {
    return highscore;
  }

  /**
   * Retrieves the player's current lives in game.
   *
   * @return player's life count while in game.
   */
  public int getLives() {
    return lives;
  }

  /**
   * Changes Player's lives to a specific amount.
   *
   * @param l increased
   */
  public void setLives(int l) {
    lives = l;
  }

  /**
   * getter for game state.
   */
  public boolean isGameOver() {
    return gameOver;
  }
}

