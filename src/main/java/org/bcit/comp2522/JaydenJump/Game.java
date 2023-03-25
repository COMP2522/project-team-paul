package org.bcit.comp2522.JaydenJump;

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

  PImage playerImg;

  private boolean gameOver;

  private final PlatformManager platformManager;


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
              Player player, int platformSpeed, int platformMoveableSpeed) {
    this.gameWidth = gameWidth;
    this.gameHeight = gameHeight;
    this.frameRate = frameRate;
    this.jumpHeight = jumpHeight;
    this.minDoubleJumpHeight = minDoubleJumpHeight;
    this.player = player;
    this.player.setSketch(this);
    this.platformManager = PlatformManager.getInstance(maxPlatforms, this, platformSpeed, platformMoveableSpeed);
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
    platformManager.generateStartPlatforms();
  }

  /**
   * draw the game, called at every frame.
   */
  public void draw() {
    background(255);
    if (!gameOver) {
      platformManager.checkCollision(player, minDoubleJumpHeight, jumpHeight);
      player.update();
      if (player.getYpos() >= height - player.getImgSize() / 2) {
        endGame();
      }
      platformManager.updateAndDrawPlatforms();
      platformManager.generatePlatforms();
      platformManager.checkCollision(player, minDoubleJumpHeight, jumpHeight);

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
      player.setVx(player.getVx() + 2);
    }
  }

  /**
   * restart the game.
   */
  public void restartGame() {
    player.reset(width / 2, 0, 0, 0);
    platformManager.getPlatforms().clear();
    platformManager.generateStartPlatforms();
    gameOver = false;
  }


  /**
   * end the game.
   * called when the player goes above or below the screen limits.
   */
  public void endGame() {
    gameOver = true;
  }

  //maybe you pass new Window, maybe "this" idfk
  /**
   * start the game.
   */
  public static void main(String[] args, Game game) {
    PApplet.runSketch(new String[]{"Window"}, game);
  }

}