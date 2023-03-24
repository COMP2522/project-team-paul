package org.bcit.comp2522.JaydenJump;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import processing.core.PApplet;
import processing.core.PImage;


/**
 * Game class.
 *
 * @author Shawn, Birring
 * @version 1.0
 */
public class Game extends PApplet {

  /**
   * Instance for the player.
   */
  private final Player player;

  /**
   * the color of the platform that are not breakable.
   */
  private static final Color green = new Color(0, 255, 0);

  /**
   * the color of the platform that are breakable.
   */
  private static final Color red = new Color(255, 0, 0);

  private final int platformSpeed;

  /**
   * array list to store the platforms that we made for the game.
   */
  private final ArrayList<Platform> platforms;

  /**
   * the width of the game.
   */
  private final int gameWidth;

  /**
   * the height of the game.
   */
  private final int gameHeight;

  /**
   * the frame rate of the game.
   */
  private final int frameRate;

  /**
   * the height of the jump.
   */
  private final int jumpHeight;

  /**
   * the minimum height of the double jump.
   */
  private final int minDoubleJumpHeight;

  /**
   * the maximum number of platforms that can be on the screen at once.
   */
  private final int maxPlatforms;

  PImage playerImg;

  private boolean gameOver;


  /**
   * the constructor for the game class.
   *
   * @param gameWidth the width of the game
   *
   * @param gameHeight the height of the game
   *
   * @param frameRate the frame rate of the game
   *
   * @param jumpHeight the height of the jump
   *
   * @param minDoubleJumpHeight the minimum height of the double jump
   *
   * @param maxPlatforms the maximum number of platforms that can be on the screen at once
   */
  public Game(int gameWidth, int gameHeight, int frameRate,
              int jumpHeight, int minDoubleJumpHeight, int maxPlatforms,
              Player player, int platformSpeed) {
    this.gameWidth = gameWidth;
    this.gameHeight = gameHeight;
    this.frameRate = frameRate;
    this.jumpHeight = jumpHeight;
    this.minDoubleJumpHeight = minDoubleJumpHeight;
    this.maxPlatforms = maxPlatforms;
    this.player = player;
    this.player.setSketch(this);
    platforms = new ArrayList<>();
    this.platformSpeed = platformSpeed;
    this.gameOver = false;
  }

  /**
   * set up the window's size.
   */
  public void settings() {
    size(gameWidth, gameHeight);
  }

  /**
   * set up the window.
   */
  public void setup() {
    playerImg = loadImage("./Images/doodleguy.png");
    player.setImage(playerImg);
    frameRate(frameRate);
    generateStartPlatforms();
  }

  /**
   * generate the platforms that are on the screen at the start of the game.
   */
  public void generateStartPlatforms() {
    float y = 5;
    for (int i = 0; i < 10; i++) {
      float x = random(width - Platform.getWidth());
      boolean isBreakable = random(1.0f) < 0.1; // set 10% of platforms to be breakable
      Color platformColor = isBreakable ? red : green;
      platforms.add(new Platform(this, x, y, 100, 20,
              platformColor, 0, platformSpeed, isBreakable));
      y += 100;
    }
  }

  /**
   * draw the game, called at every frame.
   */
  public void draw() {
    background(255);
    if (!gameOver) {
      checkCollision();
      player.update();

      if (player.getYpos() >= height - player.getImgSize() / 2) {
        endGame();
      }
      //remove platforms that are off-screen
      for (int i = platforms.size() - 1; i >= 0; i--) {
        Platform platform = platforms.get(i);
        if (!platform.isOnScreen()) {
          platforms.remove(i);
        }
      }

      checkCollision();

      //draw the platforms and update
      for (Platform platform : platforms) {
        platform.update();
        platform.draw();
      }

      //generate new platforms if necessary
      generatePlatforms();

      //check for anymore collisions
      checkCollision();

      //draw the player
      player.draw();
    } else {
      textSize(32);
      fill(255, 0, 0); // set the fill color to red
      textAlign(CENTER, CENTER);
      text("Game Over!\n Press 'SPACE' to restart", width / 2, height / 2);
    }
  }

  /**
   * check for key presses and call the appropriate methods.
   */
  public void keyPressed() {
    int keyCode = keyEvent.getKeyCode();
    if (keyCode == LEFT || keyCode == 'A') {
      player.moveLeft();
    } else if (keyCode == RIGHT || keyCode == 'D') {
      player.moveRight();
    } else if (keyCode == 81) {
      player.shootProjectile();
    } else if (keyCode == ' ') {
      if (gameOver) {
        restartGame();
      }
    }
  }

  /**
   * test this method better, but it should.
   * reduce how fast the player can move left and right.
   * after letting go of left/right or a/d.
   */
  public void keyReleased() {
    int keyCode = keyEvent.getKeyCode();
    if (keyCode == LEFT || keyCode == 'A') {
      player.setVx(player.getVx() - 2);
    } else if (keyCode == RIGHT || keyCode == 'D') {
      player.setVx(player.getVx() - 2);
    }
  }


  /**
   * check for any collisions between the player and the platforms.
   * and take appropriate action.
   */
  public void checkCollision() {
    Iterator<Platform> platformIterator = platforms.iterator();
    while (platformIterator.hasNext()) {
      Platform platform = platformIterator.next();
      if (player.collides(platform) && player.getVy() > minDoubleJumpHeight) {
        if (platform.isBreakable()) {
          platformIterator.remove();
        }
        player.setVy(-jumpHeight);
        break;
      }
    }
  }

  /**
   * generate new platforms if there are less than the maximum number of platforms on the screen.
   * the platforms are generated at the top of the screen.
   * sometimes the platforms are spawning on top of each other.
   */
  public void generatePlatforms() {
    while (platforms.size() < maxPlatforms) {
      float x = random(width - Platform.getWidth());
      boolean isBreakable = random(1.0f) < 0.1; // set 10% of platforms to be breakable
      Color platformColor = isBreakable ? red : green;
      float lastPlatformY = platforms.get(platforms.size() - 1).getYpos();
      float newY = lastPlatformY - 100; // distance between platforms
      platforms.add(new Platform(this, x, newY, 100, 20, platformColor,
              0, platformSpeed, isBreakable));
    }
  }

  /**
   * restart the game.
   */
  public void restartGame() {
    player.reset(width / 2, 0, 0, 0);
    platforms.clear();
    generateStartPlatforms();
    gameOver = false;
  }


  /**
   * end the game.
   * called when the player goes above or below the screen limits.
   */
  public void endGame() {
    gameOver = true;
  }

  /**
   * Method to start the game.
   *
   * @param game object of the game
   */
  public static void startGame(Game game) {
    PApplet.runSketch(new String[]{"Jayden Jump"}, game);
  }

}