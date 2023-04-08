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
 * @author Shawn, Birring; Brian Kwon
 * @version 1.3
 */
public class Game extends PApplet {

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
  private boolean gameOver;

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
  private int score;

  /**
   * Highest score achieved in the game so far.
   */
  private int highscore;

  /**
   * The lives of the player.
   */
  private int lives;

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
   * Constants.
   */
  private static final int BOSS_SPAWN = 2000;
  private static final int FONT_SIZE = 20;
  private static final int X_OFFSET = 75;
  private static final int Y_OFFSET = 50;
  private static final int HEART1 = 400;
  private static final int HEART2 = 337;
  private static final int HEART3 = 275;
  private static final int PLAYER_LIVES = 3;

  /**
   * the level of the game.
   *
   * @param diff            the level of the game
   * @param powerUpImage    the image for the power up
   * @param backgroundImage the image for the background
   * @param enemyImage      the image for the enemy
   * @param playerImage     the image for the player
   * @param coinImages      the images for the coins
   */
  public Game(int diff, PImage[] powerUpImage, PImage backgroundImage,
              PImage enemyImage, PImage[] playerImage, PImage[] coinImages) {
    window               = MenuManager.getInstance();
    this.backgroundImage = backgroundImage;
    this.backgroundPos   = new PVector(0, 0);
    Level level = Level.getInstance(diff);
    initializeLevel(level, coinImages, powerUpImage, enemyImage, playerImage);
    platformManager.generateStartPlatforms();
    powerUpManager.generateStartPowerUps();
    coinManager.generateStartCoins();
    gameOver = false;
  }

  /**
   * Initializes the game required objects with values needed.
   *
   * @param level          the level object with all the information
   * @param coinImages     the images for the coins
   * @param powerUpImage   the image for the power up
   * @param enemyImage     the image for the enemy
   * @param playerImage    the image for the player
   */
  private void initializeLevel(Level level, PImage[] coinImages, PImage[] powerUpImage,
                               PImage enemyImage, PImage[] playerImage) {
    player          = Player.getInstance(playerImage, level);
    scrollSpeed     = level.getScrollSpeed();
    platformManager = PlatformManager.getInstance(level);
    powerUpManager  = PowerUpManager.getInstance(level.getMaxPowerUps(), level.getPowerUpSpeed(), powerUpImage, this);
    coinManager     = CoinManager.getInstance(level.getMaxCoins(), level.getCoinSpeed(), coinImages, this);
    enemyManager    = EnemyManager.getInstance(level, enemyImage, this);
    bossManager     = BossManager.getInstance(MenuManager.getBossImg(), level, this);
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
    window.textSize(FONT_SIZE);
    window.fill(0);
    window.text("Score: " + score, X_OFFSET, Y_OFFSET);
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
  }

  public void resetHighscore() {
    highscore = 0;
  }

  /**
   * Event listener for key presses.
   */
  public void keyPressedListener(int key) {
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
  public void keyReleasedListener(int key) {
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
   * Getter for highscore.
   *
   * @return highscore as an int
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
   * @param lives increased
   */
  public void setLives(int lives) {
    this.lives = lives;
  }

  /**
   * getter for game state.
   */
  public boolean isGameOver() {
    return gameOver;
  }
}

